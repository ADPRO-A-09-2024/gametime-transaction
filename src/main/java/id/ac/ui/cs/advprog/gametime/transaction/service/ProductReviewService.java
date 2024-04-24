package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import id.ac.ui.cs.advprog.gametime.transaction.dto.ProductReviewDTO;

import java.util.List;
import java.util.UUID;

public interface ProductReviewService {
    ProductReview addProductReview(ProductReviewDTO productReviewDTO);
    ProductReview getProductReviewById(UUID id);
    List<ProductReview> getProductReviewsByProduct(UUID productId);
    List<ProductReview> getProductReviewsByAuthor(Integer authorId);
    List<ProductReview> getAllProductReviews();
    ProductReview updateProductReview(UUID productReviewId, ProductReviewDTO productReviewDTO);
    void deleteProductReview(UUID productReviewId);
    void deleteAllProductReviews();
}
