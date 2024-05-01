package id.ac.ui.cs.advprog.gametime.transaction.repository;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
}
