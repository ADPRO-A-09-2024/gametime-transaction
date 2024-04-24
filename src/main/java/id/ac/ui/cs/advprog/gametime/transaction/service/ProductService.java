package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.dto.ProductDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product getProductById(UUID id);
    List<Product> getProductsBySeller(Integer sellerId);
    List<Product> getAllProducts();
    Product updateProduct(UUID id, ProductDTO productDTO);
}
