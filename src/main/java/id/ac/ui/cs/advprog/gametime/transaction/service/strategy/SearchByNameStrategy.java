package id.ac.ui.cs.advprog.gametime.transaction.service.strategy;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.SearchRepository;

import java.util.List;

public class SearchByNameStrategy implements SearchStrategy {

    private SearchRepository searchRepository;

    public SearchByNameStrategy(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public List<Product> search(String name) {
        return searchRepository.findByNameContaining(name);
    }
}