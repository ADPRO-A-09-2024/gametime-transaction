package id.ac.ui.cs.advprog.gametime.transaction.service.strategy;

import id.ac.ui.cs.advprog.gametime.transaction.repository.SearchRepository;
import org.springframework.stereotype.Component;

@Component
public class SearchStrategyFactory {
    public SearchStrategy getStrategy(String type, SearchRepository searchRepository) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("NAME")) {
            return new SearchByNameStrategy(searchRepository);
        } else if (type.equalsIgnoreCase("CATEGORY")) {
            return new SearchByCategoryStrategy(searchRepository);
        } else if (type.equalsIgnoreCase("RATING")) {
            return new SearchByRatingStrategy(searchRepository);
        }

        return null;
    }
}