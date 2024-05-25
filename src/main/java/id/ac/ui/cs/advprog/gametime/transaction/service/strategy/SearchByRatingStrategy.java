package id.ac.ui.cs.advprog.gametime.transaction.service.strategy;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.SearchRepository;
import java.util.List;

public class SearchByRatingStrategy implements SearchStrategy {
    private final SearchRepository searchRepository;

    public SearchByRatingStrategy(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public List<Product> search(String rating) {
        Double ratingDouble = Double.valueOf(rating);
        return searchRepository.findByRating(ratingDouble);
    }
}