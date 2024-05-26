package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.dto.TransactionDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface TransactionService {
    Transaction createTransaction(TransactionDTO transactionDTO);
    Transaction getTransactionById(UUID id);
    List<Transaction> getTransactionsByBuyer(Integer buyerId);
    List<Transaction> getTransactionsBySeller(Integer sellerId);
    List<Transaction> getAllTransactions();
    CompletableFuture<Transaction> payTransaction(UUID id);
    CompletableFuture<List<Transaction>> getOtherWaitingTransactions(UUID id);
}
