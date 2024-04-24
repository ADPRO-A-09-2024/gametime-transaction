package id.ac.ui.cs.advprog.gametime.transaction.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    @Getter
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @Getter
    @Setter
    private String description;

    @Column(nullable = false)
    @Getter
    @Setter
    private String category;

    @Column(nullable = false)
    @Getter
    @Setter
    private float price;

    @Column(nullable = false)
    @Getter
    @Setter
    private float rating;
}
