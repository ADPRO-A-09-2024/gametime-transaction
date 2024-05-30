package id.ac.ui.cs.advprog.gametime.transaction.model.builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductBuilderTest {
    ProductBuilder productBuilder;

    @BeforeEach
    void setUp() {
        productBuilder = new ProductBuilder()
                .id(UUID.randomUUID())
                .seller(new User())
                .name("Boneka doraemon")
                .description("Boneka bagus")
                .category("Boneka")
                .price(100000)
                .rating(4.5);
    }

    @Test
    void testIdValid() {
        UUID id = UUID.randomUUID();
        productBuilder.id(id);

        assertEquals(id, productBuilder.build().getId());
    }

    @Test
    void testIdNull() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.id(null));
    }

    @Test
    void testSellerValid() {
        User seller = new User();
        productBuilder.seller(seller);

        assertEquals(seller, productBuilder.build().getSeller());
    }

    @Test
    void testSellerNull() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.seller(null));
    }

    @Test
    void testNameValid() {
        productBuilder.name("Boneka doraemon");
        assertEquals("Boneka doraemon", productBuilder.build().getName());
    }

    @Test
    void testNameNull() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.name(null));
    }

    @Test
    void testNameEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.name(""));
    }

    @Test
    void testDescriptionValid() {
        productBuilder.description("Boneka bagus");
        assertEquals("Boneka bagus", productBuilder.build().getDescription());
    }

    @Test
    void testDescriptionNull() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.description(null));
    }

    @Test
    void testDescriptionEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.description(""));
    }

    @Test
    void testCategoryValid() {
        productBuilder.category("Boneka");
        assertEquals("Boneka", productBuilder.build().getCategory());
    }

    @Test
    void testCategoryNull() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.category(null));
    }

    @Test
    void testCategoryEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.category(""));
    }

    @Test
    void testPriceValid() {
        productBuilder.price(100000);
        assertEquals(100000, productBuilder.build().getPrice());
    }

    @Test
    void testPrice0() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.price(0));
    }

    @Test
    void testPriceNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.price(-10));
    }

    @Test
    void testRatingValid() {
        productBuilder.rating(4.5);
        assertEquals(4.5, productBuilder.build().getRating());
    }

    @Test
    void testRatingValid0() {
        productBuilder.rating(0);
        assertEquals(0, productBuilder.build().getRating());
    }

    @Test
    void testRatingValid5() {
        productBuilder.rating(5);
        assertEquals(5, productBuilder.build().getRating());
    }

    @Test
    void testRatingNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.rating(-0.1));
    }

    @Test
    void testRatingMoreThan5() {
        assertThrows(IllegalArgumentException.class, () ->
                productBuilder.rating(5.1));
    }
}
