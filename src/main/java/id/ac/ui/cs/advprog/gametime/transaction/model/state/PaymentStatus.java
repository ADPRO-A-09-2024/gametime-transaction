package id.ac.ui.cs.advprog.gametime.transaction.model.state;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import lombok.Getter;

public abstract class PaymentStatus {
    protected Transaction transaction;
    @Getter
    protected String value;

    PaymentStatus(Transaction transaction, String value) {
        this.transaction = transaction;
        this.value = value;
    }

    public abstract void onPay(boolean enoughBalance);
}
