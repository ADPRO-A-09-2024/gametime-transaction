package id.ac.ui.cs.advprog.gametime.transaction.model.Builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.Enum.PaymentStatus;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;

import java.util.Date;
import java.util.List;

public class TransactionBuilder {
    private User buyer;
    private User seller;
    private List<Product> products;
    private Date date;
    private double price;
    private String paymentStatus;

    public TransactionBuilder buyer(User buyer) {
        if (buyer == null) {
            throw new IllegalArgumentException("Seller cannot be null");
        }

        this.buyer = buyer;
        return this;
    }
    public TransactionBuilder seller(User seller) {
        if (seller == null) {
            throw new IllegalArgumentException("Seller cannot be null");
        }

        this.seller = seller;
        return this;
    }

    public TransactionBuilder products(List<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Products cannot be empty");
        }

        this.products = products;
        return this;
    }

    public TransactionBuilder date(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        this.date = date;
        return this;
    }

    public TransactionBuilder price(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be more than 0");
        }

        this.price = price;
        return this;
    }

    public TransactionBuilder paymentStatus(String paymentStatus) {
        if (paymentStatus == null || paymentStatus.isEmpty()) {
            throw new IllegalArgumentException("Payment status cannot be empty");
        }
        if (!PaymentStatus.contains(paymentStatus)) {
            throw new IllegalArgumentException("Invalid payment status");
        }

        this.paymentStatus = paymentStatus;
        return this;
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setBuyer(buyer);
        transaction.setSeller(seller);
        transaction.setProducts(products);
        transaction.setDate(date);
        transaction.setPrice(price);
        transaction.setPaymentStatus(paymentStatus);
        return transaction;
    }
}
