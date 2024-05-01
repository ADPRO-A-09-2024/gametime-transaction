package id.ac.ui.cs.advprog.gametime.transaction.model.Builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartBuilder {
    private UUID id;
    private User buyer;
    private List<Product> productList = new ArrayList<>();

    public CartBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public CartBuilder setBuyer(User buyer) {
        this.buyer = buyer;
        return this;
    }

    public CartBuilder setProductList(List<Product> productList) {
        this.productList = productList;
        return this;
    }

    public Cart build() {
        Cart cart = new Cart();
        cart.setId(this.id);
        cart.setBuyer(this.buyer);
        cart.setProductList(this.productList);
        return cart;
    }
}
