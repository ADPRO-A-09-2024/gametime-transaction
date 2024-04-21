package id.ac.ui.cs.advprog.gametime.transaction.model.state;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;

public class FailedPayment extends PaymentStatus {
    public FailedPayment(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void onPay(boolean enoughBalance) {
    }
}
