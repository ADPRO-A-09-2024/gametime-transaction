package id.ac.ui.cs.advprog.gametime.transaction.model.state;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;

public class WaitingPayment extends PaymentStatus {
    public WaitingPayment(Transaction transaction) {
        super(transaction, "WAITING");
    }

    @Override
    public void onPay(boolean enoughBalance) {
        if (enoughBalance) {
            transaction.changeStatus(new SuccessPayment(transaction));
        } else {
            transaction.changeStatus(new FailedPayment(transaction));
        }
    }
}
