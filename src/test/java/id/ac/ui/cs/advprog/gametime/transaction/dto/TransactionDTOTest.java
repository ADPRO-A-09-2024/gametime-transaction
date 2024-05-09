package id.ac.ui.cs.advprog.gametime.transaction.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionDTOTest {

    @Test
    public void testAllArgsConstructor() {
        // Test constructor with all arguments
        TransactionDTO transactionDTO = new TransactionDTO("buyer123", "seller456", "[{\"name\":\"Product1\",\"description\":\"Description1\",\"category\":\"Category1\",\"price\":\"100\"},{\"name\":\"Product2\",\"description\":\"Description2\",\"category\":\"Category2\",\"price\":\"200\"}]");

        assertEquals("buyer123", transactionDTO.getBuyerId());
        assertEquals("seller456", transactionDTO.getSellerId());
        assertEquals("[{\"name\":\"Product1\",\"description\":\"Description1\",\"category\":\"Category1\",\"price\":\"100\"},{\"name\":\"Product2\",\"description\":\"Description2\",\"category\":\"Category2\",\"price\":\"200\"}]", transactionDTO.getProducts());
    }
}

