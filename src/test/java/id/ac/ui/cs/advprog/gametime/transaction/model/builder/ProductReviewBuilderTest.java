package id.ac.ui.cs.advprog.gametime.transaction.model.builder;

import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductReviewBuilderTest {

    @Test
    void testBuild() {
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

}

