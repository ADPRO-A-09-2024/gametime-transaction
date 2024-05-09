package id.ac.ui.cs.advprog.gametime.transaction.model.Builder;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;

import java.util.UUID;

public class CartItemBuilder {
    private UUID id;
    private Product product;
    private int quantity;

    public CartItemBuilder id(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        this.id = id;
        return this;
    }

    public CartItemBuilder product(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        this.product = product;
        return this;
    }

    public CartItemBuilder quantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = quantity;
        return this;
    }

    public CartItem build() {
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        return cartItem;
    }
}
