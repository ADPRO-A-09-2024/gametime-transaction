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
        when(productReviewService.getProductReviewsByProduct(productId.toString())).thenReturn(reviews);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("productId", productId.toString());

        // Act
        ResponseEntity<List<ProductReview>> response = productReviewController.getProductReviewByProduct(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviews, response.getBody());
    }

    @Test
    void testGetProductReviewByAuthor() {
        // Arrange
        Integer authorId = 1;
        List<ProductReview> reviews = new ArrayList<>();
        reviews.add(new ProductReview());
        when(productReviewService.getProductReviewsByAuthor(authorId.toString())).thenReturn(reviews);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("authorId", authorId.toString());

        // Act
        ResponseEntity<List<ProductReview>> response = productReviewController.getProductReviewByAuthor(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviews, response.getBody());
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
        when(productReviewService.updateProductReview(productReviewId.toString(), productReviewDTO)).thenReturn(productReview);

        // Act
        ResponseEntity<ProductReview> response = productReviewController.updateProductReview(productReviewId.toString(),
                productReviewDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productReview, response.getBody());
    }

    @Test
    void testDeleteProductReview() {
        // Arrange
        UUID productReviewId = UUID.randomUUID();

        // Act
        ResponseEntity<String> response = productReviewController.deleteProductReview(productReviewId.toString());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Review with id: " + productReviewId.toString() + " has been deleted", response.getBody());
    }

}
