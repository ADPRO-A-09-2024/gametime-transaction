package id.ac.ui.cs.advprog.gametime.transaction.model.Builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import id.ac.ui.cs.advprog.gametime.transaction.model.Enum.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartBuilder {
    private UUID id;
    private User user;
    private List<CartItem> items = new ArrayList<>();

    public CartBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public CartBuilder user(User user) {
        if (!UserRole.BUYER.equals(user.getRole())) {
            throw new IllegalArgumentException("Only users with the 'BUYER' role can have a cart.");
        }
        this.user = user;
        return this;
    }

    public CartBuilder items(List<CartItem> items) {
        this.items = new ArrayList<>(items); // Defensively copies the list
        return this;
    }

    public Cart build() {
        Cart cart = new Cart();
        cart.setId(id);
        cart.setUser(user);
        cart.setItems(items);
        return cart;
    }
}
