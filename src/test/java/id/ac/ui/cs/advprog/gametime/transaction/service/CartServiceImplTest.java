package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import id.ac.ui.cs.advprog.gametime.transaction.repository.CartRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.CartItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCartByUserId_HappyPath() {
        Integer userId = 1;
        Cart cart = new Cart();
        cart.setUserId(userId);
        when(cartRepository.findById(userId)).thenReturn(Optional.of(cart));

        Cart result = cartService.getCartByUserId(userId);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        verify(cartRepository, times(1)).findById(userId);
    }

    @Test
    void getCartByUserId_UnhappyPath() {
        Integer userId = 1;
        when(cartRepository.findById(userId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            cartService.getCartByUserId(userId);
        });

        assertEquals("Cart not found for user ID: " + userId, exception.getMessage());
        verify(cartRepository, times(1)).findById(userId);
    }

    @Test
    void addItemToCart_HappyPath() throws Exception {
        Integer userId = 1;
        Cart cart = new Cart();
        cart.setUserId(userId);
        CartItem item = new CartItem();
        item.setId(UUID.randomUUID());

        when(cartRepository.findById(userId)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(item);

        CompletableFuture<Cart> futureResult = cartService.addItemToCart(userId, item);
        Cart result = futureResult.get();

        assertNotNull(result);
        assertTrue(result.getItems().contains(item));
        verify(cartRepository, times(1)).findById(userId);
        verify(cartRepository, times(1)).save(cart);
        verify(cartItemRepository, times(1)).save(item);
    }

    @Test
    void addItemToCart_UnhappyPath() throws Exception {
        Integer userId = 1;
        CartItem item = new CartItem();
        item.setId(UUID.randomUUID());

        when(cartRepository.findById(userId)).thenReturn(Optional.empty());

        CompletableFuture<Cart> futureResult = cartService.addItemToCart(userId, item);
        Cart result = futureResult.get();

        assertNull(result);
        verify(cartRepository, times(1)).findById(userId);
        verify(cartRepository, never()).save(any(Cart.class));
        verify(cartItemRepository, never()).save(any(CartItem.class));
    }

    @Test
    void removeItemFromCart_HappyPath() throws Exception {
        Integer userId = 1;
        UUID itemId = UUID.randomUUID();
        Cart cart = new Cart();
        cart.setUserId(userId);
        CartItem item = new CartItem();
        item.setId(itemId);
        cart.setItems(List.of(item));

        when(cartRepository.findById(userId)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        doNothing().when(cartItemRepository).deleteById(itemId);

        CompletableFuture<Cart> futureResult = cartService.removeItemFromCart(userId, itemId);
        Cart result = futureResult.get();

        assertNotNull(result);
        assertFalse(result.getItems().contains(item));
        verify(cartRepository, times(1)).findById(userId);
        verify(cartRepository, times(1)).save(cart);
        verify(cartItemRepository, times(1)).deleteById(itemId);
    }

    @Test
    void removeItemFromCart_UnhappyPath() throws Exception {
        Integer userId = 1;
        UUID itemId = UUID.randomUUID();

        when(cartRepository.findById(userId)).thenReturn(Optional.empty());

        CompletableFuture<Cart> futureResult = cartService.removeItemFromCart(userId, itemId);
        Cart result = futureResult.get();

        assertNull(result);
        verify(cartRepository, times(1)).findById(userId);
        verify(cartRepository, never()).save(any(Cart.class));
        verify(cartItemRepository, never()).deleteById(any(UUID.class));
    }

    @Test
    void clearCart_HappyPath() throws Exception {
        Integer userId = 1;
        Cart cart = new Cart();
        cart.setUserId(userId);
        CartItem item1 = new CartItem();
        CartItem item2 = new CartItem();
        cart.setItems(List.of(item1, item2));

        when(cartRepository.findById(userId)).thenReturn(Optional.of(cart));
        doNothing().when(cartItemRepository).deleteAll(anyList());
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        CompletableFuture<Void> futureResult = cartService.clearCart(userId);
        futureResult.get();

        assertTrue(cart.getItems().isEmpty());
        verify(cartRepository, times(1)).findById(userId);
        verify(cartItemRepository, times(1)).deleteAll(anyList());
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void clearCart_UnhappyPath() throws Exception {
        Integer userId = 1;

        when(cartRepository.findById(userId)).thenReturn(Optional.empty());

        CompletableFuture<Void> futureResult = cartService.clearCart(userId);
        futureResult.get();

        verify(cartRepository, times(1)).findById(userId);
        verify(cartItemRepository, never()).deleteAll(anyList());
        verify(cartRepository, never()).save(any(Cart.class));
    }
}
