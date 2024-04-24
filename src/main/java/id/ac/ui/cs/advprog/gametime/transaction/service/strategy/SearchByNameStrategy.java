package id.ac.ui.cs.advprog.gametime.transaction.service.strategy;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.*;
import java.util.List;

public class SearchByNameStrategy implements SearchStrategy {

    private ProductRepository productRepository;

    @Override
    public List<Product> search(String name) {
        return productRepository.findByNameContaining(name);
    }
}