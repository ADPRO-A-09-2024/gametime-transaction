package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;

import java.util.List;

public interface TransactionService {
    public Transaction create(String buyerId, String sellerId, List<String> products);

    public Transaction pay(String id);

    public List<Transaction> getHistory(String userId);
}
