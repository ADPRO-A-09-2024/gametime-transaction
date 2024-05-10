package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Cart;
import id.ac.ui.cs.advprog.gametime.transaction.model.CartItem;
import id.ac.ui.cs.advprog.gametime.transaction.repository.CartRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.CartItemRepository;
import id.ac.ui.cs.advprog.gametime.transaction.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Cart getCartByUserId(UUID userId) {
        return cartRepository.findById(userId).orElse(null);
    }

    @Async
    @Override
    public CompletableFuture<Cart> addItemToCart(UUID userId, CartItem item) {
        return CompletableFuture.supplyAsync(() -> {
            Cart cart = getCartByUserId(userId);
            if (cart != null) {
                cart.getItems().add(item);
                cartItemRepository.save(item);
                return cartRepository.save(cart);
            }
            return null;
        });
    }


    @Async
    @Override
    public CompletableFuture<Cart> removeItemFromCart(UUID userId, UUID itemId) {
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


    @Override
    @Transactional
    public Cart updateItemQuantity(UUID userId, UUID itemId, int quantity) {
        CartItem item = cartItemRepository.findById(itemId).orElse(null);
        if (item != null) {
            item.setQuantity(quantity);
            cartItemRepository.save(item);
            return getCartByUserId(userId);
        }
        return null;
    }

    @Async
    @Override
    public CompletableFuture<Void> clearCart(UUID userId) {
        return CompletableFuture.runAsync(() -> {
            Cart cart = getCartByUserId(userId);
            if (cart != null) {
                cartItemRepository.deleteAll(cart.getItems());
                cart.setItems(null);
                cartRepository.save(cart);
            }
        });
    }
}
