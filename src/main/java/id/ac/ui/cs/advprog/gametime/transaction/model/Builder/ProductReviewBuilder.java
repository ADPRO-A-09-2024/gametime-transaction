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
        this.author = author;
        return this;
    }

    public ProductReviewBuilder product(Product product) {
        this.product = product;
        return this;
    }

    public ProductReviewBuilder content(String content) {
        this.content = content;
        return this;
    }

    public ProductReviewBuilder rating(double rating) {
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
