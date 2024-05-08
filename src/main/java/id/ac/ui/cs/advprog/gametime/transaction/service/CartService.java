package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import java.util.List;
import java.util.UUID;

public interface CartService {
    Cart addProductToCart(UUID cartId, UUID productId, int quantity);
    Cart removeProductFromCart(UUID cartId, UUID productId);
    Cart getCartById(UUID cartId);
    List<Cart> getAllCarts();
    Cart updateProductQuantity(UUID cartId, UUID productId, int quantity);
    Cart clearCart(UUID cartId);
}
