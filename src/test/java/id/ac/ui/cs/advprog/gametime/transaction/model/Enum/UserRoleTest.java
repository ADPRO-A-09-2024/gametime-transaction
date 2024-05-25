package id.ac.ui.cs.advprog.gametime.transaction.model.Enum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserRoleTest {

    @Test
    public void testContains() {
        // Test if SELLER value is contained
        assertTrue(UserRole.contains("SELLER"));

        // Test if BUYER value is contained
        assertTrue(UserRole.contains("BUYER"));

        // Test if non-existent value is not contained
        assertFalse(UserRole.contains("ADMIN"));
    }
}

