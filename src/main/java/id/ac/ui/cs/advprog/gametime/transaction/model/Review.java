package id.ac.ui.cs.advprog.gametime.transaction.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Getter
    private Integer id;
}
