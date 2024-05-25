package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.SearchRepository;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchService {
    private final SearchStrategyFactory searchStrategyFactory;
    private final SearchRepository searchRepository;

    @Autowired
    public SearchService(SearchStrategyFactory searchStrategyFactory, SearchRepository searchRepository) {
        this.searchStrategyFactory = searchStrategyFactory;
        this.searchRepository = searchRepository;
    }

    public List<Product> search(String type, String term) {
        SearchStrategy strategy = searchStrategyFactory.getStrategy(type, searchRepository);
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
        List<Product> products = searchRepository.findByRatingLessThanEqual(rating);
        return CompletableFuture.completedFuture(products);
    }

    @Async
    public CompletableFuture<List<Product>> filterByRatingGreaterThanEqual(double rating) {
        if (rating < 0) {
            throw new IllegalArgumentException("Rating must be greater than or equal to 0");
        }
        List<Product> products = searchRepository.findByRatingGreaterThanEqual(rating);
        return CompletableFuture.completedFuture(products);
    }

    @Async
    public CompletableFuture<List<Product>> filterByPriceLessThanEqual(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than or equal to 0");
        }
        List<Product> products = searchRepository.findByPriceLessThanEqual(price);
        return CompletableFuture.completedFuture(products);
    }

    @Async
    public CompletableFuture<List<Product>> filterByPriceGreaterThanEqual(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than or equal to 0");
        }
        List<Product> products = searchRepository.findByPriceGreaterThanEqual(price);
        return CompletableFuture.completedFuture(products);
    }



}