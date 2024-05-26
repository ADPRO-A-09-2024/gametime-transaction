package id.ac.ui.cs.advprog.gametime.transaction.dto;

import java.util.UUID;

public class CartItemDTO {
    private UUID id;
    private UUID productId;


    // Getters
    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}
