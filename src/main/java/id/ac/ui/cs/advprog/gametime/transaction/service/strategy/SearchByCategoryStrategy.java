package id.ac.ui.cs.advprog.gametime.transaction.service.strategy;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class SearchByCategoryStrategy implements SearchStrategy {

    private ProductRepository productRepository;

    @Override
    public List<Product> search(String category) {
        return productRepository.findByCategoryContaining(category);
    }
}