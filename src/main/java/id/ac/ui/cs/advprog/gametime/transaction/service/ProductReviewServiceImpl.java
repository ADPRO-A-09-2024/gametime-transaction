package id.ac.ui.cs.advprog.gametime.transaction.service;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import id.ac.ui.cs.advprog.gametime.transaction.dto.ProductReviewDTO;
import id.ac.ui.cs.advprog.gametime.transaction.repository.UserRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductReviewRepository;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    @Autowired
    public ProductReviewRepository productReviewRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProductRepository productRepository;

    @Override
    public ProductReview addProductReview(ProductReviewDTO productReviewDTO) {
        User author = userRepository.findById(validateAuthorID(productReviewDTO.getAuthorId()))
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Product product = productRepository.findById(validateProductID(productReviewDTO.getProductId()))
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        String content = validateContent(productReviewDTO.getContent());
        double rating = validateRating(productReviewDTO.getRating());

        ProductReview productReview = ProductReview.builder()
                .author(author)
                .product(product)
                .content(content)
                .rating(rating)
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
    public List<ProductReview> getProductReviewsByProduct(String productId) {
        return productReviewRepository.findAll().stream()
                .filter(productReview -> productReview.getProduct().getId().equals(validateProductID(productId))).toList();
    }

    @Override
    public List<ProductReview> getProductReviewsByAuthor(String authorId) {
        return productReviewRepository.findAll().stream()
                .filter(productReview -> productReview.getAuthor().getId().equals(validateAuthorID(authorId))).toList();
    }

    @Override
    public List<ProductReview> getAllProductReviews() {
        return productReviewRepository.findAll();
    }

    @Override
    public ProductReview updateProductReview(String productReviewId, ProductReviewDTO productReviewDTO) {
        ProductReview productReview = productReviewRepository.findById(validateProductReviewID(productReviewId))
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        User author = userRepository.findById(validateAuthorID(productReviewDTO.getAuthorId()))
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Product product = productRepository.findById(validateProductID(productReviewDTO.getProductId()))
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        String content = validateContent(productReviewDTO.getContent());
        double rating = validateRating(productReviewDTO.getRating());

        productReview.setAuthor(author);
        productReview.setProduct(product);
        productReview.setContent(content);
        productReview.setRating(rating);
        productReview = productReviewRepository.save(productReview);

        // Update product's rating asynchronously
        CompletableFuture.runAsync(() -> updateProductRating(product.getId()));

        return productReview;
    }

    @Override
    public void deleteProductReview(String productReviewId) {
        ProductReview productReview = productReviewRepository.findById(validateProductReviewID(productReviewId))
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

        List<ProductReview> productReviews = getProductReviewsByProduct(productId.toString());

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

    @Override
    public UUID validateProductReviewID(String productReviewId) {
        UUID productReviewIdValue;
        try {
            productReviewIdValue = UUID.fromString(productReviewId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Product Review ID must be a valid UUID");
        }
        return productReviewIdValue;
    }

    @Override
    public int validateAuthorID(String authorId) {
        int authorIdValue;
        try {
            authorIdValue = Integer.parseInt(authorId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Author ID must be an integer");
        }
        return authorIdValue;
    }

    @Override
    public UUID validateProductID(String productId) {
        UUID productIdValue;
        try {
            productIdValue = UUID.fromString(productId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Product ID must be a valid UUID");
        }
        return productIdValue;
    }

    @Override
    public String validateContent(String content) {
        if (content == null || content.isEmpty())
            throw new IllegalArgumentException("Content cannot be empty");
        return content;
    }

    @Override
    public double validateRating(String rating) {
        double ratingValue = Double.parseDouble(rating);
        if (ratingValue < 0 || ratingValue > 5)
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        return ratingValue;
    }
}
