package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface CartService {
    Cart getCartByUserId(Integer userId);

    CompletableFuture<Cart> addItemToCart(Integer userId, CartItem item);
    CompletableFuture<Cart> removeItemFromCart(Integer userId, UUID itemId);

    @Async
    CompletableFuture<Void> clearCart(Integer userId);
}
