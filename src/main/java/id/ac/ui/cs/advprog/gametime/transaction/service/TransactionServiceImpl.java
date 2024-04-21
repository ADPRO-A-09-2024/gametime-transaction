package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import id.ac.ui.cs.advprog.gametime.transaction.model.builder.TransactionBuilder;
import id.ac.ui.cs.advprog.gametime.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction create(String buyerId, String sellerId, Map<String, Integer> products,
                              float price) {
        return this.transactionRepository.create(buyerId, sellerId, products,
                                                 price, "WAITING_PAYMENT");
    }

    @Override
    public Transaction pay(String id) {
        // TODO
        // if saldo cukup
        // kurangi saldo
//        return this.transactionRepository.updateStatus(id, "SUCCESS");

        // else if saldo kurang
//        return this.transactionRepository.updateStatus(id, "FAILED");
        return null;
    }

    @Override
    public List<Transaction> getHistory(String userId) {
        List<Transaction> sellerHistory = this.transactionRepository.getBySellerId(userId);
        if (!sellerHistory.isEmpty()) {
            return sellerHistory;
        }

        return this.transactionRepository.getByBuyerId(userId);
    }
}
