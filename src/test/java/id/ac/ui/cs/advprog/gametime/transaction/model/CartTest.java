package id.ac.ui.cs.advprog.gametime.transaction.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("testUser");
        user.setPassword("password");
        user.setRole("BUYER");

        cart = new Cart();
        cart.setUserId(user.getId());
        cart.setUser(user);
        cart.setItems(new ArrayList<>());
    }

    @Test
    void testCartUserAssociation() {
        assertEquals(1, cart.getUserId());
        assertEquals("testUser", cart.getUser().getUsername());
    }

    @Test
    void testAddItemToCart() {
        CartItem item = new CartItem();
        item.setId(UUID.randomUUID());
        item.setCart(cart);

        cart.getItems().add(item);

        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().contains(item));
    }

    @Test
    void testRemoveItemFromCart() {
        CartItem item = new CartItem();
        item.setId(UUID.randomUUID());
        item.setCart(cart);

        cart.getItems().add(item);
        cart.getItems().remove(item);

        assertEquals(0, cart.getItems().size());
    }

    @Test
    void testCartInitialization() {
        assertNotNull(cart.getItems());
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    void testAddNullItemToCart() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            cart.getItems().add(null);
        });

        assertEquals("Cannot add null item to cart", exception.getMessage());
    }

    @Test
    void testRemoveNonExistentItemFromCart() {
        CartItem item = new CartItem();
        item.setId(UUID.randomUUID());

        cart.getItems().remove(item);

        assertEquals(0, cart.getItems().size());
    }

    @Test
    void testSetUserIdToNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.setUserId(null);
        });

        assertEquals("User ID cannot be null", exception.getMessage());
    }
}
