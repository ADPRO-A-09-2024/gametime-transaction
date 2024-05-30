package id.ac.ui.cs.advprog.gametime.transaction.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import id.ac.ui.cs.advprog.gametime.transaction.model.builder.ProductReviewBuilder;

@Entity
@Getter
@Setter
@Table(name = "product_review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private double rating = 0;

    public static ProductReviewBuilder builder() {
        return new ProductReviewBuilder();
    }
}
