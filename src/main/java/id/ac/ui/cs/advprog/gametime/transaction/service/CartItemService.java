package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import java.util.UUID;

public interface CartItemService {
    CartItem createOrUpdateCartItem(CartItem cartItem);
    void deleteCartItem(UUID cartItemId);
}
