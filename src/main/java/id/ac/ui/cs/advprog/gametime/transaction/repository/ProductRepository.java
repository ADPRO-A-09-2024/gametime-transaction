package id.ac.ui.cs.advprog.gametime.transaction.repository;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    //Searching
    List<Product> findByNameContaining(String name);
    List<Product> findByCategoryContaining(String category);
    List<Product> findByRating(double rating);

    //Filtering
    List<Product> findByRatingGreaterThanEqual(double rating);
    List<Product> findByRatingLessThanEqual(double rating);
    List<Product> findByPriceLessThanEqual(int price);
    List<Product> findByPriceGreaterThanEqual(int price);

}