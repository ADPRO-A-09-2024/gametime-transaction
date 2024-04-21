package id.ac.ui.cs.advprog.gametime.transaction.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
public class Transaction {
    private final String id;
    private final String buyerId;
    private final String sellerId;
    private final Map<String, Integer> products;
    private final Date date;
    private final float price;
    private String paymentStatus;

    public Transaction(String id, String buyerId, String sellerId,
                       Map<String, Integer> products, Date date,
                       float price, String paymentStatus) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.products = products;
        this.date = date;
        this.price = price;
        this.paymentStatus = paymentStatus;
    }

    public void updateStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
