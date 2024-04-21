package id.ac.ui.cs.advprog.gametime.transaction.model.state;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;

public abstract class PaymentStatus {
    protected Transaction transaction;

    PaymentStatus(Transaction transaction) {
        this.transaction = transaction;
    }

    public abstract void onPay(boolean enoughBalance);
}
