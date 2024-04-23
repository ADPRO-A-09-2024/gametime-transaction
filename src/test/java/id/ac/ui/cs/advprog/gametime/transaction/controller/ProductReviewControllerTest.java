package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.Map;
import java.util.UUID;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import id.ac.ui.cs.advprog.gametime.transaction.dto.ProductReviewDTO;
import id.ac.ui.cs.advprog.gametime.transaction.service.ProductReviewService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductReviewControllerTest {
    @Mock
    private ProductReviewService productReviewService;

    @InjectMocks
    private ProductReviewController productReviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProductReview() {
        // Arrange
        List<ProductReview> reviews = new ArrayList<>();
        reviews.add(new ProductReview());
        when(productReviewService.getAllProductReviews()).thenReturn(reviews);

        // Act
        ResponseEntity<List<ProductReview>> response = productReviewController.getAllProductReview();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviews, response.getBody());
    }

    @Test
    void testGetProductReviewByProduct() {
        // Arrange
        UUID productId = UUID.randomUUID();
        List<ProductReview> reviews = new ArrayList<>();
        reviews.add(new ProductReview());
        when(productReviewService.getProductReviewsByProduct(productId)).thenReturn(reviews);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("productId", productId.toString());

        // Act
        ResponseEntity<List<ProductReview>> response = productReviewController.getProductReviewByProduct(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviews, response.getBody());
    }

    @Test
    void testGetProductReviewByProductWithInvalidProductId() {
        // Arrange
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("productId", "invalid-id");

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productReviewController.getProductReviewByProduct(requestBody);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testGetProductReviewByAuthor() {
        // Arrange
        Integer authorId = 1;
        List<ProductReview> reviews = new ArrayList<>();
        reviews.add(new ProductReview());
        when(productReviewService.getProductReviewsByAuthor(authorId)).thenReturn(reviews);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("authorId", authorId.toString());

        // Act
        ResponseEntity<List<ProductReview>> response = productReviewController.getProductReviewByAuthor(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviews, response.getBody());
    }

    @Test
    void testGetProductReviewByAuthorWithInvalidAuthorId() {
        // Arrange
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("authorId", "invalid-id");

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productReviewController.getProductReviewByAuthor(requestBody);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testAddProductReview() {
        // Arrange
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setContent("Good product");
        productReviewDTO.setRating("4.5");

        when(productReviewService.addProductReview(productReviewDTO)).thenReturn(new ProductReview());

        // Act
        ResponseEntity<ProductReview> response = productReviewController.addProductReview(productReviewDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testAddProductReviewWithInvalidAuthorId() {
        // Arrange
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("invalid"); // Invalid author ID

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productReviewController.addProductReview(productReviewDTO);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testAddProductReviewWithInvalidProductId() {
        // Arrange
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId("invalid"); // Invalid product ID

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productReviewController.addProductReview(productReviewDTO);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testAddProductReviewWithInvalidRating() {
        // Arrange
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setRating("invalid"); // Invalid rating

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productReviewController.addProductReview(productReviewDTO);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateProductReview() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setContent("Updated review");
        productReviewDTO.setRating("3.5");

        ProductReview productReview = new ProductReview();
        productReview.setId(productReviewId);
        when(productReviewService.updateProductReview(productReviewId, productReviewDTO)).thenReturn(productReview);

        // Act
        ResponseEntity<ProductReview> response = productReviewController.updateProductReview(productReviewId.toString(),
                productReviewDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productReview, response.getBody());
    }

    @Test
    void testUpdateProductReviewWithInvalidReviewId() {
        // Arrange
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productReviewController.updateProductReview("invalid-id", productReviewDTO);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateProductReviewWithInvalidAuthorId() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("invalid"); // Invalid author ID

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productReviewController.updateProductReview(productReviewId.toString(), productReviewDTO);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateProductReviewWithInvalidProductId() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId("invalid"); // Invalid product ID

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productReviewController.updateProductReview(productReviewId.toString(), productReviewDTO);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateProductReviewWithInvalidRating() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAuthorId("1");
        productReviewDTO.setProductId(UUID.randomUUID().toString());
        productReviewDTO.setRating("invalid"); // Invalid rating

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productReviewController.updateProductReview(productReviewId.toString(), productReviewDTO);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testDeleteProductReview() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();

        // Act
        ResponseEntity<String> response = productReviewController.deleteProductReview(productReviewId.toString());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Review deleted", response.getBody());
    }

    @Test
    void testDeleteProductReviewWithInvalidReviewId() {
        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productReviewController.deleteProductReview("invalid-id");
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

}
