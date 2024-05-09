package id.ac.ui.cs.advprog.gametime.transaction.model;

import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @InjectMocks
    private User user;

    @Test
    public void testGettersAndSetters() {
        // Test setters
        user.setId(1);
        user.setUsername("test_username");
        user.setEmail("test@example.com");
        user.setPassword("test_password");
        user.setRole("ROLE_USER");
        user.setBalance(100);

        // Test getters
        assertEquals(1, user.getId());
        assertEquals("test@example.com", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("test_username", user.getName());
        assertEquals("test_password", user.getPassword());
        assertEquals("ROLE_USER", user.getRole());
        assertEquals(100, user.getBalance());
    }

    @Test
    public void testUserDetailsMethods() {
        // Test UserDetails methods
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isEnabled());
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void testGetAuthorities() {
        // Test getAuthorities
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(0, authorities.size());
    }

    @Test
    public void testAllArgsConstructor() {
        // Test AllArgsConstructor
        User newUser = new User(1, "test_username", "test@example.com", "test_password", "ROLE_USER", 100);
        assertEquals(1, newUser.getId());
        assertEquals("test_username", newUser.getName());
        assertEquals("test@example.com", newUser.getEmail());
        assertEquals("test_password", newUser.getPassword());
        assertEquals("ROLE_USER", newUser.getRole());
        assertEquals(100, newUser.getBalance());
    }
}

