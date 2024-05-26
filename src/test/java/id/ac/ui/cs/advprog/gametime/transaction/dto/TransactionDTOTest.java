package id.ac.ui.cs.advprog.gametime.transaction.dto;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionDTOTest {

    @Test
    public void testAllArgsConstructor() {
        // Test constructor with all arguments
        List<String> products = new ArrayList<>();
        TransactionDTO transactionDTO = new TransactionDTO("buyer123", "seller456", products);

        assertEquals("buyer123", transactionDTO.getBuyerId());
        assertEquals("seller456", transactionDTO.getSellerId());
        assertEquals(products, transactionDTO.getProducts());
    }
}

