package id.ac.ui.cs.advprog.gametime.transaction.service.strategy;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import java.util.List;


public interface SearchStrategy {
    List<Product> search(String term);
}