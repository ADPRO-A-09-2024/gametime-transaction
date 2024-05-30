package id.ac.ui.cs.advprog.gametime.transaction.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreateProductDTOTest {

    @Test
    void testAllArgsConstructor() {
        // Test constructor with all arguments
        CreateProductDTO createProductDTO = new CreateProductDTO("seller123", "Test Product", "Test Description", "Test Category", "100");

        assertEquals("seller123", createProductDTO.getSellerId());
        assertEquals("Test Product", createProductDTO.getName());
        assertEquals("Test Description", createProductDTO.getDescription());
        assertEquals("Test Category", createProductDTO.getCategory());
        assertEquals("100", createProductDTO.getPrice());
    }
}

