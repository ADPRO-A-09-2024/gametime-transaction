package id.ac.ui.cs.advprog.gametime.transaction.model;

import id.ac.ui.cs.advprog.gametime.transaction.model.state.FailedPayment;
import id.ac.ui.cs.advprog.gametime.transaction.model.state.PaymentStatus;
import id.ac.ui.cs.advprog.gametime.transaction.model.state.SuccessPayment;
import id.ac.ui.cs.advprog.gametime.transaction.model.state.WaitingPayment;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class Transaction {
    private final String id;
    private final String buyerId;
    private final String sellerId;
    private final List<String> productsId;
    private final Date date;
    private final float price;
    private PaymentStatus paymentStatus;

    public Transaction(String id, String buyerId, String sellerId,
                       List<String> products, Date date,
                       float price, String paymentStatus) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productsId = products;
        this.date = date;
        this.price = price;
        this.paymentStatus = getPaymentStatus(paymentStatus);
    }

    public void changeStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void pay(boolean enoughBalance) {
        this.paymentStatus.onPay(enoughBalance);
    }

    private PaymentStatus getPaymentStatus(String paymentStatus) {
        return switch (paymentStatus) {
            case "WAITING" -> new WaitingPayment(this);
            case "FAILED" -> new FailedPayment(this);
            case "SUCCESS" -> new SuccessPayment(this);
            default -> throw new IllegalArgumentException();
        };
    }}
