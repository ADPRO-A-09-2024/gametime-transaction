package id.ac.ui.cs.advprog.gametime.transaction.model;

import id.ac.ui.cs.advprog.gametime.transaction.model.state.FailedPayment;
import id.ac.ui.cs.advprog.gametime.transaction.model.state.PaymentStatus;
import id.ac.ui.cs.advprog.gametime.transaction.model.state.SuccessPayment;
import id.ac.ui.cs.advprog.gametime.transaction.model.state.WaitingPayment;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
                       List<String> productsId, Date date,
                       float price, String paymentStatus) {
        this.id = checkValidId(id);
        this.buyerId = checkValidId(buyerId);
        this.sellerId = checkValidId(sellerId);
        this.productsId = checkValidIds(productsId);
        this.date = checkDate(date);
        this.price = checkPrice(price);
        this.paymentStatus = convertPaymentStatus(paymentStatus);
    }

    public void changeStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void pay(boolean enoughBalance) {
        this.paymentStatus.onPay(enoughBalance);
    }

    public String getPaymentStatus() {
        return this.paymentStatus.getValue();
    }

    private PaymentStatus convertPaymentStatus(String paymentStatus) {
        return switch (paymentStatus) {
            case "WAITING" -> new WaitingPayment(this);
            case "FAILED" -> new FailedPayment(this);
            case "SUCCESS" -> new SuccessPayment(this);
            default -> throw new IllegalArgumentException();
        };
    }

    private String checkValidId(String id) {
        UUID x = UUID.fromString(id);
        return id;
    }

    private List<String> checkValidIds(List<String> ids) {
        if (ids.isEmpty()) {
            throw new IllegalArgumentException("Products must not be empty");
        }

        for (String id : ids) {
            UUID x = UUID.fromString(id);
        }

        return ids;
    }

    private Date checkDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null");
        }

        return date;
    }

    private float checkPrice(float price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be more than 0");
        }

        return price;
    }
}
