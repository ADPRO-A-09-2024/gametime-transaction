
import org.springframework.stereotype.Component;

public class SearchStrategyFactory {
    public SearchStrategy getStrategy(String type) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("NAME")) {
            return new SearchByNameStrategy();
        } else if (type.equalsIgnoreCase("CATEGORY")) {
            return new SearchByCategoryStrategy();
        } else if (type.equalsIgnoreCase("RATING")) {
            return new SearchByRatingStrategy();
        }

        return null;
    }
}