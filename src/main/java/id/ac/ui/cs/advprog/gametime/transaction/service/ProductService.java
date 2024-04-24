import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.SearchStrategy;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.SearchStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private SearchStrategyFactory searchStrategyFactory;

    public List<Product> search(String type, String term) {
        SearchStrategy strategy = searchStrategyFactory.getStrategy(type);
        if (strategy != null) {
            return strategy.search(term);
        }
        throw new IllegalArgumentException("Invalid search type: " + type);
    }
}