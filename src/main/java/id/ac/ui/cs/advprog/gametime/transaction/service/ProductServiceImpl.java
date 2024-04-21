package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product create(String sellerId, String name, String description, String category, float price) {
        return this.productRepository.create(sellerId, name, description, category, 0, price, new ArrayList<>());
    }

    @Override
    public Product getById(String id) {
        return this.productRepository.getById(id);
    }

    @Override
    public Product edit(String id, String name, String description, String category, float price) {
        return this.productRepository.update(id, name, description, category, price);
    }

    @Override
    public List<Product> getBySellerId(String sellerId) {
        return this.productRepository.getBySellerId(sellerId);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.getAllProducts();
    }
}
