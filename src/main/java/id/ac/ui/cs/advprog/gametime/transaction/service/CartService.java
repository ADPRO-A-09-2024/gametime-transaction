package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface CartService {
    Cart getCartByUserId(UUID userId);
    CompletableFuture<Cart> addItemToCart(UUID userId, CartItem item);
    CompletableFuture<Cart> removeItemFromCart(UUID userId, UUID itemId);
    Cart updateItemQuantity(UUID userId, UUID itemId, int quantity);
    CompletableFuture<Void> clearCart(UUID userId);
}
