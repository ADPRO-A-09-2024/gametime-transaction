package id.ac.ui.cs.advprog.gametime.transaction.service;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.*;

import org.mockito.Mock;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import id.ac.ui.cs.advprog.gametime.transaction.dto.ProductReviewDTO;
import id.ac.ui.cs.advprog.gametime.transaction.repository.UserRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductReviewRepository;

public class ProductReviewServiceImplTest {

    @Mock
    private ProductReviewRepository productReviewRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductReviewServiceImpl productReviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProductReview() {
        // Arrange
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setContent("Test review");
        productReviewDTO.setRating("4.5");

        User author = new User();
        author.setId(1);

        Product product = new Product();
        product.setId(UUID.randomUUID());

        ProductReview savedProductReview = new ProductReview();
        savedProductReview.setAuthor(author);
        savedProductReview.setProduct(product);
        savedProductReview.setContent(productReviewDTO.getContent());
        savedProductReview.setRating(Double.parseDouble(productReviewDTO.getRating()));
        when(productReviewRepository.save(any(ProductReview.class))).thenReturn(savedProductReview);

        when(userRepository.findById(1)).thenReturn(Optional.of(author));
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(product));

        // Act
        ProductReview result = productReviewService.addProductReview(productReviewDTO);

        // Assert
        assertEquals(1, result.getAuthor().getId());
        assertEquals(product.getId(), result.getProduct().getId());
        assertEquals("Test review", result.getContent());
        assertEquals(4.5, result.getRating(), 0.01);
    }

    @Test
    public void testAddProductReviewAuthorNotFound() {
        // Arrange
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setContent("Test review");
        productReviewDTO.setRating("4.5");

        when(userRepository.findById(anyInt())).thenReturn(Optional.empty()); // Author not found
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(new Product()));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productReviewService.addProductReview(productReviewDTO);
        });
        assertEquals("Author not found", exception.getMessage());
    }

    @Test
    public void testAddProductReviewProductNotFound() {
        // Arrange
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setContent("Test review");
        productReviewDTO.setRating("4.5");

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(new User()));
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty()); // Product not found

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productReviewService.addProductReview(productReviewDTO);
        });
        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    public void testGetProductReviewById() {
        // Arrange
        UUID id = UUID.randomUUID();
        ProductReview productReview = new ProductReview();
        productReview.setId(id);
        when(productReviewRepository.findById(id)).thenReturn(Optional.of(productReview));

        // Act
        ProductReview result = productReviewService.getProductReviewById(id);

        // Assert
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetProductReviewsByProduct() {
        // Arrange
        UUID productId = UUID.randomUUID();
        ProductReview productReview1 = new ProductReview();
        ProductReview productReview2 = new ProductReview();
        productReview1.setProduct(Product.builder().id(productId).build());
        productReview2.setProduct(Product.builder().id(UUID.randomUUID()).build());
        when(productReviewRepository.findAll()).thenReturn(List.of(productReview1, productReview2));

        // Act
        List<ProductReview> result = productReviewService.getProductReviewsByProduct(productId.toString());

        // Assert
        assertEquals(1, result.size());
        assertEquals(productId, result.get(0).getProduct().getId());
    }

    @Test
    public void testGetProductReviewsByAuthor() {
        // Arrange
        Integer authorId = 1;
        ProductReview productReview1 = new ProductReview();
        ProductReview productReview2 = new ProductReview();
        productReview1.setAuthor(User.builder().id(authorId).build());
        productReview2.setAuthor(User.builder().id(2).build());
        when(productReviewRepository.findAll()).thenReturn(List.of(productReview1, productReview2));

        // Act
        List<ProductReview> result = productReviewService.getProductReviewsByAuthor(authorId.toString());

        // Assert
        assertEquals(1, result.size());
        assertEquals(authorId, result.get(0).getAuthor().getId());
    }

    @Test
    public void testGetAllProductReviews() {
        // Arrange
        ProductReview productReview1 = new ProductReview();
        ProductReview productReview2 = new ProductReview();
        when(productReviewRepository.findAll()).thenReturn(List.of(productReview1, productReview2));

        // Act
        List<ProductReview> result = productReviewService.getAllProductReviews();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateProductReview() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setContent("Updated review");
        productReviewDTO.setRating("3.5");

        Product product = new Product();
        product.setId(UUID.randomUUID());

        ProductReview updatedProductReview = new ProductReview();
        updatedProductReview.setId(productReviewId);
        updatedProductReview.setContent("Updated review");
        updatedProductReview.setRating(3.5);
        updatedProductReview.setProduct(product);
        when(productReviewRepository.findById(productReviewId)).thenReturn(Optional.of(updatedProductReview));

        User author = new User();
        author.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(author));
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(product));

        when(productReviewRepository.save(any(ProductReview.class))).thenReturn(updatedProductReview);

        // Act
        ProductReview result = productReviewService.updateProductReview(productReviewId.toString(), productReviewDTO);

        // Assert
        assertEquals(productReviewId, result.getId());
        assertEquals("Updated review", result.getContent());
        assertEquals(3.5, result.getRating(), 0.01);
    }

    @Test
    public void testUpdateProductReviewReviewNotFound() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setContent("Updated review");
        productReviewDTO.setRating("3.5");

        when(productReviewRepository.findById(any(UUID.class))).thenReturn(Optional.empty()); // Review not found
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(new User()));
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(new Product()));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productReviewService.updateProductReview(productReviewId.toString(), productReviewDTO);
        });
        assertEquals("Review not found", exception.getMessage());
    }

    @Test
    public void testUpdateProductReviewAuthorNotFound() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setContent("Updated review");
        productReviewDTO.setRating("3.5");

        when(productReviewRepository.findById(any(UUID.class))).thenReturn(Optional.of(new ProductReview()));
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty()); // Author not found
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(new Product()));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productReviewService.updateProductReview(productReviewId.toString(), productReviewDTO);
        });
        assertEquals("Author not found", exception.getMessage());
    }

    @Test
    public void testUpdateProductReviewProductNotFound() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setContent("Updated review");
        productReviewDTO.setRating("3.5");

        when(productReviewRepository.findById(any(UUID.class))).thenReturn(Optional.of(new ProductReview()));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(new User()));
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty()); // Product not found

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productReviewService.updateProductReview(productReviewId.toString(), productReviewDTO);
        });
        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    public void testUpdateProductReviewRatingOutOfRange() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setContent("Updated review");
        productReviewDTO.setRating("-1");

        ProductReview existingProductReview = new ProductReview();
        existingProductReview.setId(productReviewId);
        when(productReviewRepository.findById(productReviewId)).thenReturn(Optional.of(existingProductReview));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(new User()));
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(new Product()));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productReviewService.updateProductReview(productReviewId.toString(), productReviewDTO);
        });
        assertEquals("Rating must be between 0 and 5", exception.getMessage());

        // Resetting the mock to reuse
        reset(productReviewRepository);

        productReviewDTO.setRating("6");

        when(productReviewRepository.findById(productReviewId)).thenReturn(Optional.of(existingProductReview));

        // Act & Assert
        exception = assertThrows(IllegalArgumentException.class, () -> {
            productReviewService.updateProductReview(productReviewId.toString(), productReviewDTO);
        });
        assertEquals("Rating must be between 0 and 5", exception.getMessage());
    }

    @Test
    public void testDeleteProductReview() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();
        ProductReview productReview = new ProductReview();
        productReview.setId(productReviewId);

        Product product = new Product();
        product.setId(UUID.randomUUID());

        productReview.setProduct(product);

        when(productReviewRepository.findById(productReviewId)).thenReturn(java.util.Optional.of(productReview));
        when(productRepository.findById(product.getId())).thenReturn(java.util.Optional.of(product));

        // Act
        productReviewService.deleteProductReview(productReviewId.toString());

        verify(productReviewRepository, times(1)).delete(productReview);
    }

    @Test
    public void testDeleteProductReviewReviewNotFound() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();

        when(productReviewRepository.findById(any(UUID.class))).thenReturn(Optional.empty()); // Review not found

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productReviewService.deleteProductReview(productReviewId.toString());
        });
        assertEquals("Review not found", exception.getMessage());
    }

    @Test
    public void testDeleteAllProductReviews() {
        // Act
        productReviewService.deleteAllProductReviews();

        // Assert
        verify(productReviewRepository, times(1)).deleteAll();
    }

    @Test
    public void testUpdateProductRating() {
        // Arrange
        Product testProduct = new Product();
        testProduct.setId(UUID.randomUUID());
        testProduct.setName("Test Product");

        ArrayList<ProductReview> testProductReviews = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ProductReview review = new ProductReview();
            review.setId(UUID.randomUUID());
            review.setProduct(testProduct);
            review.setRating(4.0);
            testProductReviews.add(review);
        }
        when(productRepository.findById(testProduct.getId())).thenReturn(java.util.Optional.of(testProduct));
        when(productReviewRepository.findAll()).thenReturn(testProductReviews);

        // Act
        productReviewService.updateProductRating(testProduct.getId());

        // Calculate expected average rating
        double totalRating = testProductReviews.stream().mapToDouble(ProductReview::getRating).sum();
        double expectedAverageRating = totalRating / testProductReviews.size();

        // Assert
        assertEquals(expectedAverageRating, testProduct.getRating(), 0.001);
        verify(productRepository, times(1)).save(testProduct);
    }

    @Test
    public void testUpdateProductRatingProductNotFound() {
        // Arrange
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productReviewService.updateProductRating(UUID.randomUUID());
        });
        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    void testValidateProductReviewID_Valid() {
        String productReviewId = "123e4567-e89b-12d3-a456-556642440000";
        assertEquals(productReviewId, productReviewService.validateProductReviewID(productReviewId).toString());
    }

    @Test
    void testValidateProductReviewID_Invalid() {
        String productReviewId = "invalid_id";
        assertThrows(IllegalArgumentException.class, () -> productReviewService.validateProductReviewID(productReviewId));
    }

    @Test
    void testValidateAuthorID_Valid() {
        String authorId = "123";
        assertEquals(123, productReviewService.validateAuthorID(authorId));
    }

    @Test
    void testValidateAuthorID_Invalid() {
        String authorId = "invalid_id";
        assertThrows(IllegalArgumentException.class, () -> productReviewService.validateAuthorID(authorId));
    }

    @Test
    void testValidateProductID_Valid() {
        String productId = "123e4567-e89b-12d3-a456-556642440000";
        assertEquals(productId, productReviewService.validateProductID(productId).toString());
    }

    @Test
    void testValidateProductID_Invalid() {
        String productId = "invalid_id";
        assertThrows(IllegalArgumentException.class, () -> productReviewService.validateProductID(productId));
    }

    @Test
    void testValidateContent_Valid() {
        String content = "This is a valid content.";
        assertEquals(content, productReviewService.validateContent(content));
    }

    @Test
    void testValidateContent_Null() {
        String content = null;
        assertThrows(IllegalArgumentException.class, () -> productReviewService.validateContent(content));
    }

    @Test
    void testValidateContent_Empty() {
        String content = "";
        assertThrows(IllegalArgumentException.class, () -> productReviewService.validateContent(content));
    }

    @Test
    void testValidateRating_Valid() {
        String rating = "4.5";
        assertEquals(4.5, productReviewService.validateRating(rating));
    }

    @Test
    void testValidateRating_Invalid_LowerBound() {
        String rating = "-1";
        assertThrows(IllegalArgumentException.class, () -> productReviewService.validateRating(rating));
    }

    @Test
    void testValidateRating_Invalid_UpperBound() {
        String rating = "5.1";
        assertThrows(IllegalArgumentException.class, () -> productReviewService.validateRating(rating));
    }

    @Test
    void testValidateRating_Invalid_NonNumeric() {
        String rating = "not_a_number";
        assertThrows(NumberFormatException.class, () -> productReviewService.validateRating(rating));
    }
}
