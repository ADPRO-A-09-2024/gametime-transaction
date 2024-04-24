import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByNameContaining(String name);

    
}