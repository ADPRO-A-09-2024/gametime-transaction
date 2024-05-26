package id.ac.ui.cs.advprog.gametime.transaction.controller;

import id.ac.ui.cs.advprog.gametime.transaction.dto.CartDTO;
import id.ac.ui.cs.advprog.gametime.transaction.dto.CartItemDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import id.ac.ui.cs.advprog.gametime.transaction.service.CartService;
import id.ac.ui.cs.advprog.gametime.transaction.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CartControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartService cartService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private CartController cartController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void testGetCartByUserId_HappyPath() throws Exception {
        Cart cart = new Cart();
        cart.setUserId(1);
        List<CartItem> items = new ArrayList<>();
        CartItem item = new CartItem();
        item.setId(UUID.randomUUID());
        items.add(item);
        cart.setItems(items);

        when(cartService.getCartByUserId(1)).thenReturn(cart);

        mockMvc.perform(get("/api/cart/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.items.length()").value(1));
    }

    @Test
    public void testGetCartByUserId_UnhappyPath() throws Exception {
        when(cartService.getCartByUserId(1)).thenReturn(null);

        mockMvc.perform(get("/api/cart/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddItemToCart_HappyPath() throws Exception {
        CartItemDTO itemDTO = new CartItemDTO();
        itemDTO.setProductId(UUID.randomUUID());
        itemDTO.setId(UUID.randomUUID());

        CartItem item = new CartItem();
        item.setProduct(productService.getProductById(itemDTO.getProductId()));
        item.setId(itemDTO.getId());

        Cart cart = new Cart();
        cart.setUserId(1);
        List<CartItem> items = new ArrayList<>();
        items.add(item);
        cart.setItems(items);

        when(cartService.addItemToCart(any(Integer.class), any(CartItem.class))).thenReturn(CompletableFuture.completedFuture(cart));

        mockMvc.perform(post("/api/cart/1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.items.length()").value(1));
    }

    @Test
    public void testAddItemToCart_UnhappyPath() throws Exception {
        CartItemDTO itemDTO = new CartItemDTO();
        itemDTO.setProductId(UUID.randomUUID());
        itemDTO.setId(UUID.randomUUID());

        when(cartService.addItemToCart(any(Integer.class), any(CartItem.class))).thenReturn(CompletableFuture.completedFuture(null));

        mockMvc.perform(post("/api/cart/1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDTO)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testRemoveItemFromCart_HappyPath() throws Exception {
        UUID itemId = UUID.randomUUID();
        Cart cart = new Cart();
        cart.setUserId(1);
        cart.setItems(new ArrayList<>());

        when(cartService.removeItemFromCart(1, itemId)).thenReturn(CompletableFuture.completedFuture(cart));

        mockMvc.perform(delete("/api/cart/1/items/" + itemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.items.length()").value(0));
    }

    @Test
    public void testRemoveItemFromCart_UnhappyPath() throws Exception {
        UUID itemId = UUID.randomUUID();

        when(cartService.removeItemFromCart(1, itemId)).thenReturn(CompletableFuture.completedFuture(null));

        mockMvc.perform(delete("/api/cart/1/items/" + itemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testClearCart_HappyPath() throws Exception {
        Cart cart = new Cart();
        cart.setUserId(1);
        cart.setItems(new ArrayList<>());

        when(cartService.clearCart(1)).thenReturn(CompletableFuture.completedFuture(null));

        mockMvc.perform(delete("/api/cart/user/1/clear")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testClearCart_UnhappyPath() throws Exception {
        when(cartService.clearCart(1)).thenReturn(CompletableFuture.completedFuture(null));

        mockMvc.perform(delete("/api/cart/user/1/clear")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
