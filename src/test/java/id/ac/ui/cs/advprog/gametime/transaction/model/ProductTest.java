package id.ac.ui.cs.advprog.gametime.transaction.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                "Boneka doraemon",
                "Boneka doraemon asli 100% reject pabrik warna kuning",
                "Boneka",
                100000,
                0,
                new ArrayList<>());
    }

    @Test
    void testCreateValid() {
        assertEquals("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455", this.product.getId());
        assertEquals("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454", this.product.getSellerId());
        assertEquals("Boneka doraemon", this.product.getName());
        assertEquals("Boneka doraemon asli 100% reject pabrik warna kuning", this.product.getDescription());
        assertEquals("Boneka", this.product.getCategory());
        assertEquals(100000, this.product.getPrice());
        assertEquals(0, this.product.getRating());
        assertTrue(this.product.getReviewsId().isEmpty());
    }

    @Test
    void testCreateInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Product product = new Product("123",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "Boneka doraemon",
                            "Boneka doraemon asli 100% reject pabrik warna kuning",
                            "Boneka",
                            100000,
                            0,
                            new ArrayList<>());
                }
        );
    }

    @Test
    void testCreateEmptyId() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Product product = new Product("",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "Boneka doraemon",
                            "Boneka doraemon asli 100% reject pabrik warna kuning",
                            "Boneka",
                            100000,
                            0,
                            new ArrayList<>());
                }
        );
    }

    @Test
    void testCreateInvalidSellerId() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Product product = new Product("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                            "123",
                            "Boneka doraemon",
                            "Boneka doraemon asli 100% reject pabrik warna kuning",
                            "Boneka",
                            100000,
                            0,
                            new ArrayList<>());
                }
        );
    }

    @Test
    void testCreateEmptySellerId() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Product product = new Product("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                            "",
                            "Boneka doraemon",
                            "Boneka doraemon asli 100% reject pabrik warna kuning",
                            "Boneka",
                            100000,
                            0,
                            new ArrayList<>());
                }
        );
    }

    @Test
    void testCreateEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Product product = new Product("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "",
                            "Boneka doraemon asli 100% reject pabrik warna kuning",
                            "Boneka",
                            100000,
                            0,
                            new ArrayList<>());
                }
        );
    }

    @Test
    void testCreateEmptyDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Product product = new Product("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "Boneka doraemon",
                            "",
                            "Boneka",
                            100000,
                            0,
                            new ArrayList<>());
                }
        );
    }

    @Test
    void testCreateInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Product product = new Product("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "Boneka doraemon",
                            "Boneka doraemon asli 100% reject pabrik warna kuning",
                            "Boneka",
                            -1,
                            0,
                            new ArrayList<>());
                }
        );
    }

    @Test
    void testCreateRatingLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Product product = new Product("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "Boneka doraemon",
                            "Boneka doraemon asli 100% reject pabrik warna kuning",
                            "Boneka",
                            100000F,
                            0.9F,
                            new ArrayList<>());
                }
        );
    }

    @Test
    void testCreateRatingMoreThan5() {
        assertThrows(IllegalArgumentException.class, () -> {
                    Product product = new Product("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                            "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                            "Boneka doraemon",
                            "Boneka doraemon asli 100% reject pabrik warna kuning",
                            "Boneka",
                            100000,
                            5.1F,
                            new ArrayList<>());
                }
        );
    }

    @Test
    void testChangeNameValid() {
        this.product.changeName("Boneka mickey mouse");
        assertEquals("Boneka mickey mouse", this.product.getName());
    }

    @Test
    void testChangeNameEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                this.product.changeName(""));
    }

    @Test
    void testChangeDescriptionValid() {
        this.product.changeDescription("Boneka doraemon asli 100% reject pabrik warna pink");
        assertEquals("Boneka doraemon asli 100% reject pabrik warna pink", this.product.getDescription());
    }

    @Test
    void testChangeDescriptionEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                this.product.changeDescription(""));
    }

    @Test
    void testChangeCategoryValid() {
        this.product.changeCategory("Dolls");
        assertEquals("Dolls", this.product.getCategory());
    }

    @Test
    void testChangeCategoryEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                this.product.changeCategory(""));
    }

    @Test
    void testChangePriceValid() {
        this.product.changePrice(50000);
        assertEquals(50000, this.product.getPrice());
    }

    @Test
    void testChangePriceNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                this.product.changePrice(-1));
    }


    @Test
    void testChangeRatingValid() {
        this.product.changeRating(2);
        assertEquals(2, this.product.getRating());
    }

    @Test
    void testChangeRatingLessThan1() {
        assertThrows(IllegalArgumentException.class, () ->
                this.product.changeRating(0.9F));
    }

    @Test
    void testChangeRatingMoreThan5() {
        assertThrows(IllegalArgumentException.class, () ->
                this.product.changeRating(5.1F));
    }

    @Test
    void testAddReviewValid() {
        this.product.addReview("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3451");
        assertEquals("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3451", this.product.getReviewsId().getFirst());
    }

    @Test
    void testAddReviewInvalidId() {
        assertThrows(IllegalArgumentException.class, () ->
                this.product.addReview("123"));
    }

    @Test
    void testAddReviewEmptyId() {
        assertThrows(IllegalArgumentException.class, () ->
                this.product.addReview(""));
    }
}
