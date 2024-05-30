package id.ac.ui.cs.advprog.gametime.transaction.model.builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.User;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserBuilderTest {

    @Test
    void testBuildUser() {
        // Test valid user creation
        User user = new UserBuilder()
                .id(1)
                .username("test_username")
                .email("test@example.com")
                .password("test_password")
                .role("SELLER")
                .balance(100)
                .build();

        assertEquals(1, user.getId());
        assertEquals("test_username", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("test_password", user.getPassword());
        assertEquals("SELLER", user.getRole());
        assertEquals(100, user.getBalance());
    }

    @Test
    void testInvalidUserCreation() {
        // Test null id
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(null)
                    .username("test_username")
                    .email("test@example.com")
                    .password("test_password")
                    .role("SELLER")
                    .balance(100)
                    .build();
        });

        // Test empty username
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(1)
                    .username("")
                    .email("test@example.com")
                    .password("test_password")
                    .role("SELLER")
                    .balance(100)
                    .build();
        });

        // Test short username
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(1)
                    .username("usr")
                    .email("test@example.com")
                    .password("test_password")
                    .role("SELLER")
                    .balance(100)
                    .build();
        });

        // Test invalid username characters
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(1)
                    .username("user#name")
                    .email("test@example.com")
                    .password("test_password")
                    .role("SELLER")
                    .balance(100)
                    .build();
        });

        // Test invalid email format
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(1)
                    .username("test_username")
                    .email("invalid_email")
                    .password("test_password")
                    .role("SELLER")
                    .balance(100)
                    .build();
        });

        // Test null email
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(1)
                    .username("test_username")
                    .email(null)
                    .password("test_password")
                    .role("SELLER")
                    .balance(100)
                    .build();
        });

        // Test empty email
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(1)
                    .username("test_username")
                    .email("")
                    .password("test_password")
                    .role("SELLER")
                    .balance(100)
                    .build();
        });

        // Test empty password
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(1)
                    .username("test_username")
                    .email("test@example.com")
                    .password("")
                    .role("SELLER")
                    .balance(100)
                    .build();
        });

        // Test short password
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(1)
                    .username("test_username")
                    .email("test@example.com")
                    .password("1234567")
                    .role("SELLER")
                    .balance(100)
                    .build();
        });

        // Test empty role
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(1)
                    .username("test_username")
                    .email("test@example.com")
                    .password("test_password")
                    .role("")
                    .balance(100)
                    .build();
        });

        // Test invalid role
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(1)
                    .username("test_username")
                    .email("test@example.com")
                    .password("test_password")
                    .role("INVALID_ROLE")
                    .balance(100)
                    .build();
        });

        // Test negative balance
        assertThrows(IllegalArgumentException.class, () -> {
            new UserBuilder()
                    .id(1)
                    .username("test_username")
                    .email("test@example.com")
                    .password("test_password")
                    .role("SELLER")
                    .balance(-100)
                    .build();
        });
    }
}

