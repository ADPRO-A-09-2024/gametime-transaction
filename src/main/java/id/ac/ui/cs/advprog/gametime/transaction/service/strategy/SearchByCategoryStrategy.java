package id.ac.ui.cs.advprog.gametime.transaction.service.strategy;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.SearchRepository;

import java.util.List;

public class SearchByCategoryStrategy implements SearchStrategy {

    private final SearchRepository searchRepository;

    public SearchByCategoryStrategy(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public List<Product> search(String category) {
        return searchRepository.findByCategoryContaining(category);
    }
}