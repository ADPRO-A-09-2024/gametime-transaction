package id.ac.ui.cs.advprog.gametime.transaction.model.state;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;

public class SuccessPayment extends PaymentStatus {
    public SuccessPayment(Transaction transaction) {
        super(transaction, "SUCCESS");
    }

    @Override
    public void onPay(boolean enoughBalance) {
    }
}
