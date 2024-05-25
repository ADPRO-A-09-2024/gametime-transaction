package id.ac.ui.cs.advprog.gametime.transaction.service;

import com.google.gson.Gson;
import id.ac.ui.cs.advprog.gametime.transaction.TransactionApplication;
import id.ac.ui.cs.advprog.gametime.transaction.dto.TransactionDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Enum.PaymentStatus;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.TransactionRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public Transaction createTransaction(TransactionDTO transactionDTO) {
        User buyer = userRepository.findById(Integer.parseInt(transactionDTO.getBuyerId()))
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found"));
        User seller = userRepository.findById(Integer.parseInt(transactionDTO.getSellerId()))
                .orElseThrow(() -> new IllegalArgumentException("Seller not found"));

        List<Product> products = Arrays.stream(new Gson().fromJson(transactionDTO.getProducts(), String[].class))
                .map(productId -> productRepository.findById(UUID.fromString(productId))
                        .orElseThrow(() -> new IllegalArgumentException("Product not found")))
                .toList();

        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID())
                .buyer(buyer)
                .seller(seller)
                .products(products)
                .price(getPrice(products))
                .date(new Date())
                .paymentStatus(PaymentStatus.WAITING.name())
                .build();
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(UUID id) {
        return transactionRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Transaction> getTransactionsByBuyer(Integer buyerId) {
        return transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getBuyer().getId().equals(buyerId))
                .toList();
    }

    @Override
    public List<Transaction> getTransactionsBySeller(Integer sellerId) {
        return transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getSeller().getId().equals(sellerId))
                .toList();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    @Async
    public CompletableFuture<Transaction> payTransaction(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        User buyer = transaction.getBuyer();
        int buyerBalance = buyer.getBalance();
        User seller = transaction.getSeller();
        int sellerBalance = seller.getBalance();
        int price = transaction.getPrice();

        if (buyerBalance < price ) {
            transaction.setPaymentStatus(PaymentStatus.FAILED.name());
        } else {
            buyer.setBalance(buyerBalance - price);
            seller.setBalance(sellerBalance + price);
            transaction.setPaymentStatus(PaymentStatus.SUCCESS.name());
        }

        return CompletableFuture.completedFuture(transactionRepository.save(transaction));
    }

    @Override
    @Async
    public CompletableFuture<List<Transaction>> getOtherWaitingTransactions(UUID id) {
        Integer buyerId = transactionRepository.findById(id).orElseThrow()
                .getBuyer()
                .getId();

        return CompletableFuture.completedFuture(
                transactionRepository.findAll().stream()
                        .filter(transaction -> !transaction.getId().equals(id))
                        .filter(transaction -> transaction.getBuyer().getId().equals(buyerId))
                        .filter(transaction -> transaction.getPaymentStatus().equals(PaymentStatus.WAITING.name()))
                        .toList());
    }

    private int getPrice(List<Product> products) {
        int price = 0;
        for (Product product : products) {
            price += product.getPrice();
        }

        return price;
    }
}
