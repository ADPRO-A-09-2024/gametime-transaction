package id.ac.ui.cs.advprog.gametime.transaction.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Product product;
    private User seller;

    @BeforeEach
    public void setup() {
        product = new Product();
        seller = new User();
        seller.setUsername("Test User");
    }

    @Test
    public void testId() {
        UUID id = UUID.randomUUID();
        product.setId(id);
        assertEquals(id, product.getId());
    }

    @Test
    public void testSeller() {
        product.setSeller(seller);
        assertEquals(seller, product.getSeller());
    }

    @Test
    public void testName() {
        product.setName("Test Product");
        assertEquals("Test Product", product.getName());
    }

    @Test
    public void testDescription() {
        product.setDescription("Test Description");
        assertEquals("Test Description", product.getDescription());
    }

    @Test
    public void testCategory() {
        product.setCategory("Test Category");
        assertEquals("Test Category", product.getCategory());
    }

    @Test
    public void testPrice() {
        product.setPrice(100);
        assertEquals(100, product.getPrice());
    }

    @Test
    public void testRating() {
        product.setRating(4.5);
        assertEquals(4.5, product.getRating(), 0.001);
    }
}