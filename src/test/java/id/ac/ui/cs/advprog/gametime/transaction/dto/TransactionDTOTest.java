package id.ac.ui.cs.advprog.gametime.transaction.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDTOTest {

    @Test
    void testAllArgsConstructor() {
        // Test constructor with all arguments
        List<String> products = new ArrayList<>();
        TransactionDTO transactionDTO = new TransactionDTO("buyer123", "seller456", products);

        assertEquals("buyer123", transactionDTO.getBuyerId());
        assertEquals("seller456", transactionDTO.getSellerId());
        assertEquals(products, transactionDTO.getProducts());
    }
}

