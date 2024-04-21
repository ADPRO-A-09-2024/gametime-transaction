package id.ac.ui.cs.advprog.gametime.transaction.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Product {
    private final String id;
    private final String sellerId;
    private String name;
    private String description;
    private String category;
    private float price;
    private float rating;
    private List<String> reviewsId;

    public Product(String id, String sellerId, String name,
                   String description, String category, float price,
                   float rating, List<String> reviewsId) {
        this.id = checkValidId(id);
        this.sellerId = checkValidId(sellerId);
        this.changeName(name);
        this.changeDescription(description);
        this.changeCategory(category);
        this.changePrice(price);
        this.changeRating(rating);
        this.reviewsId = reviewsId;
    }

    public void changeName(String name) {
        this.name = checkStringNotEmpty(name);
    }

    public void changeDescription(String description) {
        this.description = checkStringNotEmpty(description);
    }

    public void changeCategory(String category) {
        this.category = checkStringNotEmpty(category);
    }

    public void changePrice(float price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be more than 0");
        }

        this.price = price;
    }

    public void changeRating(float rating) {
        if ((rating < 1 && rating != 0) || rating > 5) {
            throw new IllegalArgumentException("Rating must be 0 (empty) or between than 1 and 5");
        }

        this.rating = rating;
    }

    public void addReview(String reviewId) {
        this.reviewsId.add(checkValidId(reviewId));
    }

    private String checkValidId(String id) {
        UUID x = UUID.fromString(id);
        return id;
    }

    private String checkStringNotEmpty(String data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Product detail must not be empty");
        }

        return data;
    }
}
