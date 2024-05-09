package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import java.util.UUID;

public interface CartService {
    Cart getCartByUserId(UUID userId);
    Cart addItemToCart(UUID userId, CartItem item);
    Cart removeItemFromCart(UUID userId, UUID itemId);
    Cart updateItemQuantity(UUID userId, UUID itemId, int quantity);
    void clearCart(UUID userId);
}
