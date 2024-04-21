package id.ac.ui.cs.advprog.gametime.transaction.repository;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import id.ac.ui.cs.advprog.gametime.transaction.model.builder.TransactionBuilder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TransactionRepository {
    List<Transaction> transactions = new ArrayList<>();

    public Transaction create(String buyerId, String sellerId, Map<String, Integer> products,
                              float price, String paymentStatus) {
        String id = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(id, buyerId, sellerId,
                                                  products, new Date(), price,
                                     "WAITING_PAYMENT");
        this.transactions.add(transaction);
        return transaction;
    }

    public Transaction getById(String id) {
        for (Transaction transaction : this.transactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return null;
    }

    public List<Transaction> getByBuyerId(String buyerId) {
        List<Transaction> transactionsByBuyer = new ArrayList<>();
        for (Transaction transaction : this.transactions) {
            if (transaction.getBuyerId().equals(buyerId)) {
                transactionsByBuyer.add(transaction);
            }
        }
        return transactionsByBuyer;
    }

    public List<Transaction> getBySellerId(String sellerId) {
        List<Transaction> transactionsBySeller = new ArrayList<>();
        for (Transaction transaction : this.transactions) {
            if (transaction.getBuyerId().equals(sellerId)) {
                transactionsBySeller.add(transaction);
            }
        }
        return transactionsBySeller;
    }

    public Transaction updateStatus(String id, String paymentStatus) {
        Transaction transaction = this.getById(id);
        transaction.updateStatus(paymentStatus);
        return transaction;
    }
}
