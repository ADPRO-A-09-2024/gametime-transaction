package id.ac.ui.cs.advprog.gametime.transaction.model.Builder;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;

import java.util.UUID;

public class CartItemBuilder {
    private UUID id;
    private Product product;

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

    public CartItem build() {
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setProduct(product);
        return cartItem;
    }
}
