package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    @Async
    public CompletableFuture<List<Product>> filterByRatingLessThanEqual(double rating) {
        if (rating < 0) {
            throw new IllegalArgumentException("Rating must be greater than or equal to 0");
        }
        List<Product> products = productRepository.findByRatingLessThanEqual(rating);
        return CompletableFuture.completedFuture(products);
    }

    @Async
    public CompletableFuture<List<Product>> filterByRatingGreaterThanEqual(double rating) {
        if (rating < 0) {
            throw new IllegalArgumentException("Rating must be greater than or equal to 0");
        }
        List<Product> products = productRepository.findByRatingGreaterThanEqual(rating);
        return CompletableFuture.completedFuture(products);
    }

    @Async
    public CompletableFuture<List<Product>> filterByPriceLessThanEqual(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than or equal to 0");
        }
        List<Product> products = productRepository.findByPriceLessThanEqual(price);
        return CompletableFuture.completedFuture(products);
    }

    @Async
    public CompletableFuture<List<Product>> filterByPriceGreaterThanEqual(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than or equal to 0");
        }
        List<Product> products = productRepository.findByPriceGreaterThanEqual(price);
        return CompletableFuture.completedFuture(products);
    }



}