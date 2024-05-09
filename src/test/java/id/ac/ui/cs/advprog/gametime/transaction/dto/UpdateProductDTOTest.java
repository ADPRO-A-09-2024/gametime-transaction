package id.ac.ui.cs.advprog.gametime.transaction.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateProductDTOTest {

    @Test
    public void testAllArgsConstructor() {
        // Test constructor with all arguments
        UpdateProductDTO updateProductDTO = new UpdateProductDTO("Test Name", "Test Description", "Test Category", "100");

        assertEquals("Test Name", updateProductDTO.getName());
        assertEquals("Test Description", updateProductDTO.getDescription());
        assertEquals("Test Category", updateProductDTO.getCategory());
        assertEquals("100", updateProductDTO.getPrice());
    }
}

