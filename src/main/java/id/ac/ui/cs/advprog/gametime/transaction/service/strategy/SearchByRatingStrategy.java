package id.ac.ui.cs.advprog.gametime.transaction.service.strategy;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import java.util.List;

public class SearchByRatingStrategy implements SearchStrategy {
    private final ProductRepository productRepository;

    public SearchByRatingStrategy(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> search(String rating) {
        Double ratingDouble = Double.valueOf(rating);
        return productRepository.findByRating(ratingDouble);
    }
}