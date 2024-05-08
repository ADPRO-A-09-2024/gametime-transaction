package id.ac.ui.cs.advprog.gametime.transaction.service.strategy;

import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class SearchStrategyFactory {
    public SearchStrategy getStrategy(String type, ProductRepository productRepository) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("NAME")) {
            return new SearchByNameStrategy(productRepository);
        } else if (type.equalsIgnoreCase("CATEGORY")) {
            return new SearchByCategoryStrategy(productRepository);
        } else if (type.equalsIgnoreCase("RATING")) {
            return new SearchByRatingStrategy(productRepository);
        }

        return null;
    }
}