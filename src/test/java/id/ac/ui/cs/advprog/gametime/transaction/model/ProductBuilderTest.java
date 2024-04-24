package id.ac.ui.cs.advprog.gametime.transaction.model;

import id.ac.ui.cs.advprog.gametime.transaction.model.Builder.ProductBuilder;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductBuilderTest {
    private ProductBuilder productBuilder;
    private User seller;

    @BeforeEach
    public void setup() {
        productBuilder = new ProductBuilder();
        seller = new User();
        seller.setUsername("Test User");
    }

    @Test
    public void testSeller() {
        assertDoesNotThrow(() -> productBuilder.seller(seller));
        assertThrows(IllegalArgumentException.class, () -> productBuilder.seller(null));
    }

    @Test
    public void testName() {
        assertDoesNotThrow(() -> productBuilder.name("Test Product"));
        assertThrows(IllegalArgumentException.class, () -> productBuilder.name(null));
        assertThrows(IllegalArgumentException.class, () -> productBuilder.name(""));
    }

    @Test
    public void testDescription() {
        assertDoesNotThrow(() -> productBuilder.description("Test Description"));
        assertThrows(IllegalArgumentException.class, () -> productBuilder.description(null));
        assertThrows(IllegalArgumentException.class, () -> productBuilder.description(""));
    }

    @Test
    public void testCategory() {
        assertDoesNotThrow(() -> productBuilder.category("Test Category"));
        assertThrows(IllegalArgumentException.class, () -> productBuilder.category(null));
        assertThrows(IllegalArgumentException.class, () -> productBuilder.category(""));
    }

    @Test
    public void testPrice() {
        assertDoesNotThrow(() -> productBuilder.price(100));
        assertThrows(IllegalArgumentException.class, () -> productBuilder.price(0));
        assertThrows(IllegalArgumentException.class, () -> productBuilder.price(-1));
    }

    @Test
    public void testRating() {
        assertDoesNotThrow(() -> productBuilder.rating(4.5));
        assertThrows(IllegalArgumentException.class, () -> productBuilder.rating(-1));
        assertThrows(IllegalArgumentException.class, () -> productBuilder.rating(5.1));
    }

    @Test
    public void testBuild() {
        productBuilder.seller(seller)
                .name("Test Product")
                .description("Test Description")
                .category("Test Category")
                .price(100)
                .rating(4.5);

        Product product = productBuilder.build();

        assertEquals(seller, product.getSeller());
        assertEquals("Test Product", product.getName());
        assertEquals("Test Description", product.getDescription());
        assertEquals("Test Category", product.getCategory());
        assertEquals(100, product.getPrice());
        assertEquals(4.5, product.getRating(), 0.001);
    }
}