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
import java.util.concurrent.CompletableFuture;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable Integer userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(convertToDto(cart));
    }

    @PostMapping("/{userId}/items")
    public CompletableFuture<ResponseEntity<CartDTO>> addItemToCart(@PathVariable Integer userId, @RequestBody CartItemDTO itemDto) {
        // Convert DTO to entity if required, then call the service
        return cartService.addItemToCart(userId, convertToEntity(itemDto))
                .thenApply(cart -> ResponseEntity.ok(convertToDto(cart)))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{userId}/items/{itemId}")
    public CompletableFuture<ResponseEntity<CartDTO>> removeItemFromCart(@PathVariable Integer userId, @PathVariable UUID itemId) {
        return cartService.removeItemFromCart(userId, itemId)
                .thenApply(cart -> ResponseEntity.ok(convertToDto(cart)))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // New clear cart endpoint
    @DeleteMapping("/{userId}/clear")
    public CompletableFuture<ResponseEntity<Void>> clearCart(@PathVariable Integer userId) {
        return cartService.clearCart(userId)
                .thenApply(voidResult -> ResponseEntity.ok().<Void>build())
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

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
        return itemDto;
    }

    private CartItem convertToEntity(CartItemDTO cartItemDto) {
        CartItem item = new CartItem();
        item.setId(UUID.randomUUID());
        item.setProduct(productService.getProductById(cartItemDto.getProductId()));
        return item;
    }
}
