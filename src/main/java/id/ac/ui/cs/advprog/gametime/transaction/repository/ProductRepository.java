package id.ac.ui.cs.advprog.gametime.transaction.repository;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public Product create(String sellerId, String name, String description,
                          String category, float rating, float price,
                          List<String> reviewsId) {
        String id = UUID.randomUUID().toString();
        Product product = new Product(id, sellerId, name, description, category,
                                      price, rating, new ArrayList<>());
        this.products.add(product);
        return product;
    }

    public Product getById(String id) {
        for (Product product : this.products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }

        return null;
    }

    public List<Product> getBySellerId(String sellerId) {
        List<Product> productsBySeller = new ArrayList<>();

        for (Product product : this.products) {
            if (product.getSellerId().equals(sellerId)) {
                productsBySeller.add(product);
            }
        }

        return productsBySeller;
    }

    public List<Product> getAllProducts() {
        return this.products;
    }

    public Product update(String id, String name, String description, String category, float price) {
        Product product = this.getById(id);
        product.changeName(name);
        product.changeDescription(description);
        product.changeCategory(category);
        product.changePrice(price);
        return product;
    }
}
