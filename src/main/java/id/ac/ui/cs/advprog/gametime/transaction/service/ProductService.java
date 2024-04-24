import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

     public List<Product> searchProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }
}