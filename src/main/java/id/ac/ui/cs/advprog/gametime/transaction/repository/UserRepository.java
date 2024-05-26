package id.ac.ui.cs.advprog.gametime.transaction.repository;

import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Override
    Optional<User> findById(Integer id);
}

