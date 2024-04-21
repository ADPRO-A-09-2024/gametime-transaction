package id.ac.ui.cs.advprog.gametime.transaction.model.builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class TransactionBuilder {
    private String id;
    private String buyerId;
    private String sellerId;
    private Map<String, Integer> products;
    private Date date;
    private float price;
    private String paymentStatus;

    public TransactionBuilder addId() {
        this.id = UUID.randomUUID().toString();
        return this;
    }

    public TransactionBuilder addId(String id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder addBuyerId(String buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public TransactionBuilder addSellerId(String sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public TransactionBuilder addProducts(Map<String, Integer> products) {
        this.products = products;
        return this;
    }
    public TransactionBuilder addDate() {
        this.date = new Date();
        return this;
    }

    public TransactionBuilder addDate(Date date) {
        this.date = date;
        return this;
    }

    public TransactionBuilder addPrice(float price) {
        this.price = price;
        return this;
    }

    public TransactionBuilder addPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public Transaction build() {
        return new Transaction(id, buyerId, sellerId, products, date, price, paymentStatus);
    }
}
