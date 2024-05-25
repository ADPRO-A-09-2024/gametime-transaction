package id.ac.ui.cs.advprog.gametime.transaction.repository;

import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductReviewRepository extends JpaRepository<ProductReview, UUID> {
}
