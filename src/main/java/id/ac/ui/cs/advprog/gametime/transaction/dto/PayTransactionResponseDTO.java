package id.ac.ui.cs.advprog.gametime.transaction.dto;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayTransactionResponseDTO {
    private Transaction transaction;
    private List<Transaction> otherWaitingTransactions;
}
