package id.ac.ui.cs.advprog.gametime.transaction.model.Builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CartBuilderTest {

    @Test
    public void testBuildCart() {
        User user = new User();
        user.setId(1);
        user.setUsername("test_username");
        user.setRole("BUYER");

        List<CartItem> items = new ArrayList<>();
        CartItem item = new CartItem();
        item.setId(UUID.randomUUID());
        items.add(item);

        // Test valid cart creation
        Cart cart = new CartBuilder()
                .user(user)
                .items(items)
                .build();

        assertEquals(1, cart.getUserId());
        assertEquals(user, cart.getUser());
        assertEquals(items, cart.getItems());
    }

    @Test
    public void testInvalidCartCreation() {
        User user = new User();
        user.setId(1);
        user.setUsername("test_username");
        user.setRole("SELLER");

        List<CartItem> items = new ArrayList<>();
        CartItem item = new CartItem();
        item.setId(UUID.randomUUID());
        items.add(item);

        // Test non-buyer user
        assertThrows(IllegalArgumentException.class, () -> {
            new CartBuilder()
                    .user(user)
                    .items(items)
                    .build();
        });

        // Test null user
        assertThrows(IllegalArgumentException.class, () -> {
            new CartBuilder()
                    .user(null)
                    .items(items)
                    .build();
        });

        // Test null items
        Cart cart = new CartBuilder()
                .user(user)
                .items(null)
                .build();

        assertNotNull(cart.getItems());
        assertTrue(cart.getItems().isEmpty());
    }
}
