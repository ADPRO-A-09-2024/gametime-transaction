package id.ac.ui.cs.advprog.gametime.transaction.repository;

import id.ac.ui.cs.advprog.gametime.transaction.model.Buyer;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    public Buyer getById(String id);
}
