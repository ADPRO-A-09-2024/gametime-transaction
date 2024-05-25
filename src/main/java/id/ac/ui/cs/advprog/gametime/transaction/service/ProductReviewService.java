package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import id.ac.ui.cs.advprog.gametime.transaction.dto.ProductReviewDTO;

import java.util.List;
import java.util.UUID;

public interface ProductReviewService {
    ProductReview addProductReview(ProductReviewDTO productReviewDTO);
    ProductReview getProductReviewById(UUID id);
    List<ProductReview> getProductReviewsByProduct(String productId);
    List<ProductReview> getProductReviewsByAuthor(String authorId);
    List<ProductReview> getAllProductReviews();
    ProductReview updateProductReview(String productReviewId, ProductReviewDTO productReviewDTO);
    void deleteProductReview(String productReviewId);
    void deleteAllProductReviews();
    UUID validateProductReviewID(String productReviewId);
    int validateAuthorID(String authorId);
    UUID validateProductID(String productId);
    String validateContent(String content);
    double validateRating(String rating);
}
