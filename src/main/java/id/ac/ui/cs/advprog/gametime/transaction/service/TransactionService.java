package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    public Transaction create(String buyerId, String sellerId, Map<String, Integer> products,
                              float price);

    public Transaction pay(String id);

    public List<Transaction> getHistory(String userId);
}
