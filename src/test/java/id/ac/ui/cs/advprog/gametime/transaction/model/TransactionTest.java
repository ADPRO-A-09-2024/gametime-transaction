package id.ac.ui.cs.advprog.gametime.transaction.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TransactionTest {
    Transaction transaction;
    List<String> productsId;

    @BeforeEach
    void setUp() {
        this.productsId = new ArrayList<>();
        this.productsId.add("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3452");

        this.transaction = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                this.productsId,
                new Date(),
                300000,
                "WAITING");
    }

    @Test
    void testCreateValid() {
        assertEquals("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455", this.transaction.getId());
        assertEquals("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454", this.transaction.getBuyerId());
        assertEquals("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453", this.transaction.getSellerId());
        assertEquals("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3452", this.transaction.getProductsId().getFirst());
        assertEquals("WAITING", this.transaction.getPaymentStatus());
    }

    @Test
    void testCreateInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction transaction = new Transaction("123",
                    "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                    "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                    this.productsId,
                    new Date(),
                    300000,
                    "WAITING");
            }
        );
    }

    @Test
    void testCreateEmptyId() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Transaction transaction = new Transaction("",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                            this.productsId,
                            new Date(),
                            300000,
                            "WAITING");
                }
        );
    }

    @Test
    void testCreateInvalidBuyerId() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Transaction transaction = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "123",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                            this.productsId,
                            new Date(),
                            300000,
                            "WAITING");
                }
        );
    }

    @Test
    void testCreateEmptyBuyerId() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Transaction transaction = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                            this.productsId,
                            new Date(),
                            300000,
                            "WAITING");
                }
        );
    }

    @Test
    void testCreateInvalidSellerId() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Transaction transaction = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "123",
                            this.productsId,
                            new Date(),
                            300000,
                            "WAITING");
                }
        );
    }

    @Test
    void testCreateEmptySellerId() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Transaction transaction = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "",
                            this.productsId,
                            new Date(),
                            300000,
                            "WAITING");
                }
        );
    }

    @Test
    void testCreateInvalidProductsId() {
        this.productsId.add("123");

        assertThrows(IllegalArgumentException.class, () -> {
                    Transaction transaction = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                            this.productsId,
                            new Date(),
                            300000,
                            "WAITING");
                }
        );
    }

    @Test
    void testCreateEmptyProductsId() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Transaction transaction = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                            new ArrayList<>(),
                            new Date(),
                            300000,
                            "WAITING");
                }
        );
    }

    @Test
    void testCreateNullDate() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Transaction transaction = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                            this.productsId,
                            null,
                            300000,
                            "WAITING");
                }
        );
    }

    @Test
    void testCreateInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Transaction transaction = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                            this.productsId,
                            new Date(),
                            -1,
                            "WAITING");
                }
        );
    }

    @Test
    void testCreateInvalidPaymentStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Transaction transaction = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                            this.productsId,
                            new Date(),
                            300000,
                            "WAITING_PAYMENT");
                }
        );
    }
}
