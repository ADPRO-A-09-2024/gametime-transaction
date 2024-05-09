package id.ac.ui.cs.advprog.gametime.transaction.controller;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import id.ac.ui.cs.advprog.gametime.transaction.dto.CartDTO;
import id.ac.ui.cs.advprog.gametime.transaction.dto.CartItemDTO;
import id.ac.ui.cs.advprog.gametime.transaction.service.CartService;
import id.ac.ui.cs.advprog.gametime.transaction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable UUID userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(convertToDto(cart));
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<CartDTO> addItemToCart(@PathVariable UUID userId, @RequestBody CartItemDTO itemDto) {
        Cart cart = cartService.addItemToCart(userId, convertToEntity(itemDto));
        return ResponseEntity.ok(convertToDto(cart));
    }

    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<CartDTO> removeItemFromCart(@PathVariable UUID userId, @PathVariable UUID itemId) {
        Cart cart = cartService.removeItemFromCart(userId, itemId);
        return ResponseEntity.ok(convertToDto(cart));
    }

    // Additional methods for updating items, clearing cart, etc.

    private CartDTO convertToDto(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setUserId(cart.getUser().getId());  // Assuming CartDTO has a userId field
        List<CartItemDTO> items = cart.getItems().stream()
                .map(this::convertCartItemToDto)
                .collect(Collectors.toList());
        dto.setItems(items);
        return dto;
    }

    private CartItemDTO convertCartItemToDto(CartItem cartItem) {
        CartItemDTO itemDto = new CartItemDTO();
        itemDto.setId(cartItem.getId());
        itemDto.setProductId(cartItem.getProduct().getId());
        itemDto.setQuantity(cartItem.getQuantity());
        return itemDto;
    }

    private CartItem convertToEntity(CartItemDTO cartItemDto) {
        CartItem item = new CartItem();
        item.setId(UUID.randomUUID());
        item.setProduct(productService.getProductById(cartItemDto.getProductId()));
        item.setQuantity(cartItemDto.getQuantity());
        return item;
    }
}
