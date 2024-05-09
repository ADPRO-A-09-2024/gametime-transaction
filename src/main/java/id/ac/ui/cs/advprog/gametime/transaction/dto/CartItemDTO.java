package id.ac.ui.cs.advprog.gametime.transaction.dto;

import java.util.UUID;

public class CartItemDTO {
    private UUID id;
    private UUID productId;
    private int quantity;

    // Getters
    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
