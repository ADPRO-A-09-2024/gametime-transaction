package id.ac.ui.cs.advprog.gametime.transaction.service;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.gametime.transaction.dto.ProductReviewDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductReviewRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.UserRepository;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductReview addProductReview(ProductReviewDTO productReviewDTO) {
        User author = userRepository.findById(Integer.parseInt(productReviewDTO.getAuthorId()))
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Product product = productRepository.findById(UUID.fromString(productReviewDTO.getProductId()))
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        ProductReview productReview = ProductReview.builder()
                .author(author)
                .product(product)
                .content(productReviewDTO.getContent())
                .rating(Double.parseDouble(productReviewDTO.getRating()))
                .build();
        productReview = productReviewRepository.save(productReview);

        // Update product's rating asynchronously
        CompletableFuture.runAsync(() -> updateProductRating(product.getId()));

        return productReview;
    }

    @Override
    public ProductReview getProductReviewById(UUID id) {
        return productReviewRepository.findById(id).orElseThrow();
    }

    @Override
    public List<ProductReview> getProductReviewsByProduct(UUID productId) {
        return productReviewRepository.findAll().stream()
                .filter(productReview -> productReview.getProduct().getId().equals(productId)).toList();
    }

    @Override
    public List<ProductReview> getProductReviewsByAuthor(Integer authorId) {
        return productReviewRepository.findAll().stream()
                .filter(productReview -> productReview.getAuthor().getId().equals(authorId)).toList();
    }

    @Override
    public List<ProductReview> getAllProductReviews() {
        return productReviewRepository.findAll();
    }

    @Override
    public ProductReview updateProductReview(UUID productReviewId, ProductReviewDTO productReviewDTO) {
        ProductReview productReview = productReviewRepository.findById(productReviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        User author = userRepository.findById(Integer.parseInt(productReviewDTO.getAuthorId()))
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Product product = productRepository.findById(UUID.fromString(productReviewDTO.getProductId()))
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        double rating = Double.parseDouble(productReviewDTO.getRating());

        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }

        productReview.setAuthor(author);
        productReview.setProduct(product);
        productReview.setContent(productReviewDTO.getContent());
        productReview.setRating(rating);
        productReview = productReviewRepository.save(productReview);

        // Update product's rating asynchronously
        CompletableFuture.runAsync(() -> updateProductRating(product.getId()));

        return productReview;
    }

    @Override
    public void deleteProductReview(UUID productReviewId) {
        ProductReview productReview = productReviewRepository.findById(productReviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        productReviewRepository.delete(productReview);

        // Update product's rating asynchronously
        CompletableFuture.runAsync(() -> updateProductRating(productReview.getProduct().getId()));
    }

    @Override
    public void deleteAllProductReviews() {
        productReviewRepository.deleteAll();
    }

    @Async
    public void updateProductRating(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        List<ProductReview> productReviews = getProductReviewsByProduct(productId);

        double totalRating = productReviews.stream()
                .mapToDouble(ProductReview::getRating)
                .sum();

        if (!productReviews.isEmpty()) {
            double averageRating = totalRating / productReviews.size();
            BigDecimal bd = BigDecimal.valueOf(averageRating);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            product.setRating(bd.doubleValue());
        } else {
            product.setRating(0.0);
        }

        productRepository.save(product);
    }
}
