package id.ac.ui.cs.advprog.gametime.transaction.service;


import id.ac.ui.cs.advprog.gametime.transaction.dto.CreateProductDTO;
import id.ac.ui.cs.advprog.gametime.transaction.dto.UpdateProductDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Product createProduct(CreateProductDTO createProductDTO) {
        User seller = userRepository.findById(Integer.parseInt(createProductDTO.getSellerId()))
                .orElseThrow(() -> new IllegalArgumentException("Seller not found"));

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .seller(seller)
                .name(createProductDTO.getName())
                .description(createProductDTO.getDescription())
                .category(createProductDTO.getCategory())
                .price(Integer.parseInt(createProductDTO.getPrice()))
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> getProductsBySeller(Integer sellerId) {
        return productRepository.findAll().stream()
                .filter(product -> product.getSeller().getId().equals(sellerId))
                .toList();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(UUID id, UpdateProductDTO updateProductDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        int price = Integer.parseInt(updateProductDTO.getPrice());

        if (price <= 0) {
            throw new IllegalArgumentException("Price must be more than 0");
        }

        product.setName(updateProductDTO.getName());
        product.setDescription(updateProductDTO.getDescription());
        product.setCategory(updateProductDTO.getCategory());
        product.setPrice(price);

        return productRepository.save(product);
    }
}
