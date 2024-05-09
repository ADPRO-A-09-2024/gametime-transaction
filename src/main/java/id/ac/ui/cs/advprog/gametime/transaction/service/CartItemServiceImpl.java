package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import id.ac.ui.cs.advprog.gametime.transaction.repository.CartItemRepository;
import id.ac.ui.cs.advprog.gametime.transaction.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public CartItem createOrUpdateCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void deleteCartItem(UUID cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
