package id.ac.ui.cs.advprog.gametime.transaction.model;

import lombok.Getter;

import java.util.List;

@Getter
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
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.reviewsId = reviewsId;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeCategory(String category) {
        this.category = category;
    }

    public void changePrice(float price) {
        this.price = price;
    }

    public void updateRating(float rating) {
        this.rating = rating;
    }

    public void addReview(String reviewId) {
        this.reviewsId.add(reviewId);
    }
}
