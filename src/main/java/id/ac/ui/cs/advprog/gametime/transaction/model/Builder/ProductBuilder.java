package id.ac.ui.cs.advprog.gametime.transaction.model.Builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;

public class ProductBuilder {
    private User seller;
    private String name;
    private String description;
    private String category;
    private int price;
    private double rating;

    public ProductBuilder seller(User seller) {
        if (seller == null) {
            throw new IllegalArgumentException("Seller cannot be null");
        }

        this.seller = seller;
        return this;
    }

    public ProductBuilder name(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.name = name;
        return this;
    }

    public ProductBuilder description(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }

        this.description = description;
        return this;
    }

    public ProductBuilder category(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }

        this.category = category;
        return this;
    }

    public ProductBuilder price(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be more than 0");
        }

        this.price = price;
        return this;
    }

    public ProductBuilder rating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }

        this.rating = rating;
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setSeller(seller);
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setPrice(price);
        product.setRating(rating);
        return product;
    }
}
