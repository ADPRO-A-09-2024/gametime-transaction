package id.ac.ui.cs.advprog.gametime.transaction.model.Builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;

public class ProductReviewBuilder {
    private User author;
    private Product product;
    private String content;
    private double rating = 0;

    public ProductReviewBuilder author(User author) {
        if (author == null)
            throw new IllegalArgumentException("Author cannot be null");
        this.author = author;
        return this;
    }

    public ProductReviewBuilder product(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null");
        this.product = product;
        return this;
    }

    public ProductReviewBuilder content(String content) {
        if (content == null || content.isEmpty())
            throw new IllegalArgumentException("Content cannot be empty");
        this.content = content;
        return this;
    }

    public ProductReviewBuilder rating(double rating) {
        if (rating < 0 || rating > 5)
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        this.rating = rating;
        return this;
    }

    public ProductReview build() {
        ProductReview productReview = new ProductReview();
        productReview.setAuthor(author);
        productReview.setProduct(product);
        productReview.setContent(content);
        productReview.setRating(rating);
        return productReview;
    }
}
