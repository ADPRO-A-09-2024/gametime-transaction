package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final SearchStrategyFactory searchStrategyFactory;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(SearchStrategyFactory searchStrategyFactory, ProductRepository productRepository) {
        this.searchStrategyFactory = searchStrategyFactory;
        this.productRepository = productRepository;
    }

    public List<Product> search(String type, String term) {
        SearchStrategy strategy = searchStrategyFactory.getStrategy(type, productRepository);
        if (strategy != null) {
            return strategy.search(term);
        }
        throw new IllegalArgumentException("Invalid search type: " + type);
    }
}