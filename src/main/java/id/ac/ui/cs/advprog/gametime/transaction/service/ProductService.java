package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;

import java.util.List;

public interface ProductService {
    public Product create(String sellerId, String name, String description, String category, float price);
    public Product getById(String id);
    public Product edit(String id, String name, String description, String category, float price);
    public List<Product> getBySellerId(String sellerId);
    public List<Product> getAllProducts();
}
