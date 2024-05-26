package id.ac.ui.cs.advprog.gametime.transaction.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    private CartItem cartItem;
    private Cart cart;
    private Product product;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        cart.setUserId(1);

        product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Test Product");
        product.setDescription("Product Description");
        product.setCategory("Category");
        product.setPrice(1000);

        cartItem = new CartItem();
        cartItem.setId(UUID.randomUUID());
        cartItem.setCart(cart);
        cartItem.setProduct(product);
    }

    @Test
    void testCartItemInitialization() {
        assertNotNull(cartItem.getId());
        assertNotNull(cartItem.getCart());
        assertNotNull(cartItem.getProduct());
    }

    @Test
    void testCartItemCartAssociation() {
        assertEquals(1, cartItem.getCart().getUserId());
    }

    @Test
    void testCartItemProductAssociation() {
        assertEquals("Test Product", cartItem.getProduct().getName());
    }

    @Test
    void testSetCart() {
        Cart newCart = new Cart();
        newCart.setUserId(2);

        cartItem.setCart(newCart);

        assertEquals(2, cartItem.getCart().getUserId());
    }

    @Test
    void testSetProduct() {
        Product newProduct = new Product();
        newProduct.setId(UUID.randomUUID());
        newProduct.setName("New Product");

        cartItem.setProduct(newProduct);

        assertEquals("New Product", cartItem.getProduct().getName());
    }

    @Test
    void testSetCartToNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cartItem.setCart(null);
        });

        assertEquals("Cart cannot be null", exception.getMessage());
    }

    @Test
    void testSetProductToNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cartItem.setProduct(null);
        });

        assertEquals("Product cannot be null", exception.getMessage());
    }
}
