package id.ac.ui.cs.advprog.gametime.transaction.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductReviewTest {

    @Mock
    private User author;

    @Mock
    private Product product;

    @InjectMocks
    private ProductReview productReview;

    @Test
    public void testIdGetterAndSetter() {
        // Set up test data
        UUID id = UUID.randomUUID();

        // Set data through setter
        productReview.setId(id);

        // Test getter
        assertEquals(id, productReview.getId());
    }

    @Test
    public void testAuthorGetterAndSetter() {
        // Set up test data
        User testAuthor = new User();

        // Set data through setter
        productReview.setAuthor(testAuthor);

        // Test getter
        assertEquals(testAuthor, productReview.getAuthor());
    }

    @Test
    public void testProductGetterAndSetter() {
        // Set up test data
        Product testProduct = new Product();

        // Set data through setter
        productReview.setProduct(testProduct);

        // Test getter
        assertEquals(testProduct, productReview.getProduct());
    }

    @Test
    public void testContentGetterAndSetter() {
        // Set up test data
        String content = "Great product!";

        // Set data through setter
        productReview.setContent(content);

        // Test getter
        assertEquals(content, productReview.getContent());
    }

    @Test
    public void testRatingGetterAndSetter() {
        // Set up test data
        double rating = 4.5;

        // Set data through setter
        productReview.setRating(rating);

        // Test getter
        assertEquals(rating, productReview.getRating());
    }
}

