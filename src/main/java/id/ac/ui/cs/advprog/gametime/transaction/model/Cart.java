package id.ac.ui.cs.advprog.gametime.transaction.model;

import id.ac.ui.cs.advprog.gametime.transaction.model.Builder.CartBuilder;
import id.ac.ui.cs.advprog.gametime.transaction.model.Builder.ProductReviewBuilder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne(mappedBy = "cart")
    private User buyer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private List<Product> productList;

    public static CartBuilder builder() {
        return new CartBuilder();
    }
}
