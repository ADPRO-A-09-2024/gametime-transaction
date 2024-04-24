import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByNameContaining(String name);
    List<Product> findByCategoryContaining(String category);
    List<Product> findByRating(double rating);
}