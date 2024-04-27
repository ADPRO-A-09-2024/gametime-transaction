package id.ac.ui.cs.advprog.gametime.transaction.repository;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByNameContaining(String name);
    List<Product> findByCategoryContaining(String category);
    List<Product> findByRating(double rating);
}
