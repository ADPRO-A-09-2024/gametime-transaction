package id.ac.ui.cs.advprog.gametime.transaction.model.Builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CartItemBuilderTest {

    @Test
    public void testBuildCartItem() {
        Cart cart = new Cart();
        cart.setUserId(1);

        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Test Product");
        product.setDescription("Product Description");
        product.setCategory("Category");
        product.setPrice(1000);

        // Test valid cart item creation
        CartItem cartItem = new CartItemBuilder()
                .id(UUID.randomUUID())
                .product(product)
                .build();

        assertNotNull(cartItem.getId());
        assertEquals(cart, cartItem.getCart());
        assertEquals(product, cartItem.getProduct());
    }

    @Test
    public void testInvalidCartItemCreation() {
        Cart cart = new Cart();
        cart.setUserId(1);

        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Test Product");
        product.setDescription("Product Description");
        product.setCategory("Category");
        product.setPrice(1000);

        // Test null cart
        assertThrows(IllegalArgumentException.class, () -> {
            new CartItemBuilder()
                    .id(UUID.randomUUID())
                    .product(product)
                    .build();
        });

        // Test null product
        assertThrows(IllegalArgumentException.class, () -> {
            new CartItemBuilder()
                    .id(UUID.randomUUID())
                    .product(null)
                    .build();
        });

        // Test null id
        CartItem cartItem = new CartItemBuilder()
                .id(null)
                .product(product)
                .build();

        assertNotNull(cartItem.getId());
    }
}
