package id.ac.ui.cs.advprog.gametime.transaction.model.Builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductReviewBuilderTest {

    @Test
    public void testBuildWithValidData() {
        // Arrange
        User author = new User();
        Product product = new Product();
        String content = "This is a review";
        double rating = 4.5;

        // Act
        ProductReview productReview = new ProductReviewBuilder()
                .author(author)
                .product(product)
                .content(content)
                .rating(rating)
                .build();

        // Assert
        assertEquals(author, productReview.getAuthor());
        assertEquals(product, productReview.getProduct());
        assertEquals(content, productReview.getContent());
        assertEquals(rating, productReview.getRating());
    }

    @Test
    public void testBuildWithNullAuthor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductReviewBuilder()
                    .author(null)
                    .product(new Product())
                    .content("This is a review")
                    .rating(4.5)
                    .build();
        });
    }

    @Test
    public void testBuildWithNullProduct() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductReviewBuilder()
                    .author(new User())
                    .product(null)
                    .content("This is a review")
                    .rating(4.5)
                    .build();
        });
    }

    @Test
    public void testBuildWithNullContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductReviewBuilder()
                    .author(new User())
                    .product(new Product())
                    .content(null)
                    .rating(4.5)
                    .build();
        });
    }

    @Test
    public void testBuildWithEmptyContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductReviewBuilder()
                    .author(new User())
                    .product(new Product())
                    .content("")
                    .rating(4.5)
                    .build();
        });
    }

    @Test
    public void testBuildWithRatingMoreThan5() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductReviewBuilder()
                    .author(new User())
                    .product(new Product())
                    .content("This is a review")
                    .rating(6.0)
                    .build();
        });
    }

    @Test
    public void testBuildWithRatingLessThan0() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductReviewBuilder()
                    .author(new User())
                    .product(new Product())
                    .content("This is a review")
                    .rating(-1.0)
                    .build();
        });
    }
}
