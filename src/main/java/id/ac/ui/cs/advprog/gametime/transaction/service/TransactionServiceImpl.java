package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.dto.TransactionDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Enum.PaymentStatus;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import id.ac.ui.cs.advprog.gametime.transaction.repository.TransactionRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Transaction createTransaction(TransactionDTO transactionDTO) {
        User buyer = userRepository.findById(transactionDTO.getBuyerId())
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found"));
        User seller = userRepository.findById(transactionDTO.getSellerId())
                .orElseThrow(() -> new IllegalArgumentException("Seller not found"));

        Transaction transaction = Transaction.builder()
                .buyer(buyer)
                .seller(seller)
                .products(transactionDTO.getProducts())
                .price(getPrice(transactionDTO.getProducts()))
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
    public Transaction pay(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        User buyer = transaction.getBuyer();
        int buyerBalance = buyer.getBalance();
        User seller = transaction.getSeller();
        int sellerBalance = seller.getBalance();
        int price = transaction.getPrice();

        if (buyerBalance < price) {
            transaction.setPaymentStatus(PaymentStatus.FAILED.name());
        } else {
            buyer.setBalance(buyerBalance - price);
            seller.setBalance(sellerBalance + price);
            transaction.setPaymentStatus(PaymentStatus.SUCCESS.name());
        }

        return transactionRepository.save(transaction);
    }

    private int getPrice(List<Product> products) {
        int price = 0;
        for (Product product : products) {
            price += product.getPrice();
        }

        return price;
    }
}
