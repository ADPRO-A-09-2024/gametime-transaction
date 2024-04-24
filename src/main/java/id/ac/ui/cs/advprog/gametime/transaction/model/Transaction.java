package id.ac.ui.cs.advprog.gametime.transaction.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    @Getter
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    @Column(nullable = false)
    @Getter
    private Date date;

    @Column(nullable = false)
    @Getter
    private float price;

    @Column(nullable = false)
    @Getter
    private String paymentStatus;
}
