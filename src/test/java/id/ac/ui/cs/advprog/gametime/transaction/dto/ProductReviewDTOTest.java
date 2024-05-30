package id.ac.ui.cs.advprog.gametime.transaction.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductReviewDTOTest {

    @Test
    void testAllArgsConstructor() {
        // Test constructor with all arguments
        ProductReviewDTO productReviewDTO = new ProductReviewDTO("author123", "product456", "Great product!", "5");

        assertEquals("author123", productReviewDTO.getAuthorId());
        assertEquals("product456", productReviewDTO.getProductId());
        assertEquals("Great product!", productReviewDTO.getContent());
        assertEquals("5", productReviewDTO.getRating());
    }
}

