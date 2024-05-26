package id.ac.ui.cs.advprog.gametime.transaction.model;

import id.ac.ui.cs.advprog.gametime.transaction.model.Builder.CartBuilder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cart")
public class Cart {
    @Id
    private Integer userId;  // Using the User's ID as Cart ID

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId  // Ensures the cart ID is mapped to the user ID
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    public static CartBuilder builder() {
        return new CartBuilder();
    }

    // Constructors, getters, and setters
}
