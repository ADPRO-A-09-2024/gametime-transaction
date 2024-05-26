package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import id.ac.ui.cs.advprog.gametime.transaction.repository.CartRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.CartItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    @Transactional(readOnly = true)
    public Cart getCartByUserId(Integer userId) {
        return cartRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user ID: " + userId));
    }

    @Async
    @Override
    public CompletableFuture<Cart> addItemToCart(Integer userId, CartItem item) {
        return CompletableFuture.supplyAsync(() -> {
            Cart cart = getCartByUserId(userId);
            if (cart != null) {
                // Check if the item is already present in the cart
                boolean isPresent = cartItemRepository.existsById(item.getId());
                if (!isPresent) {
                    cart.getItems().add(item);
                    item.setCart(cart); // Set the back-reference
                    cartItemRepository.save(item);
                    return cartRepository.save(cart);
                } else {
                    throw new IllegalStateException("Item already added to a cart");
                }
            }
            return null;
        });
    }

    @Async
    @Override
    public CompletableFuture<Cart> removeItemFromCart(Integer userId, UUID itemId) {
        return CompletableFuture.supplyAsync(() -> {
            Cart cart = getCartByUserId(userId);
            if (cart != null) {
                cart.getItems().removeIf(item -> item.getId().equals(itemId));
                cartItemRepository.deleteById(itemId);
                return cartRepository.save(cart);
            }
            return null;
        });
    }

    @Async
    @Override
    public CompletableFuture<Void> clearCart(Integer userId) {
        return CompletableFuture.runAsync(() -> {
            Cart cart = getCartByUserId(userId);
            if (cart != null && cart.getItems() != null) {
                cart.getItems().forEach(item -> cartItemRepository.delete(item));
                cart.setItems(new ArrayList<>());  // Clear the list instead of setting it to null
                cartRepository.save(cart);
            }
        });
    }
}

