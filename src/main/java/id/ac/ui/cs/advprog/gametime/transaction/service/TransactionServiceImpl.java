package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Buyer;
import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.TransactionRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public Transaction create(String buyerId, String sellerId, List<String> productsId) {
        return this.transactionRepository.create(buyerId, sellerId, productsId,
                                                 this.getPrice(productsId),
                                    "WAITING");
    }

    @Override
    public Transaction pay(String id) {
        Transaction transaction = this.transactionRepository.getById(id);
        Buyer buyer = (Buyer) this.userRepository.getById(transaction.getBuyerId());
        float price = transaction.getPrice();
        float buyerBalance = buyer.getBalance();

        if (buyerBalance >= price) {
            buyer.updateBalance(buyerBalance - price);
            transaction.pay(true);
        } else {
            transaction.pay(false);
        }

        return transaction;
    }

    @Override
    public List<Transaction> getHistory(String userId) {
        List<Transaction> sellerHistory = this.transactionRepository.getBySellerId(userId);
        if (!sellerHistory.isEmpty()) {
            return sellerHistory;
        }

        return this.transactionRepository.getByBuyerId(userId);
    }

    private float getPrice(List<String> productsId) {
        float price = 0;
        for (String productId : productsId) {
            price += this.productRepository.getById(productId).getPrice();
        }

        return price;
    }
}
