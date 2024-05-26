package id.ac.ui.cs.advprog.gametime.transaction.dto;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private String buyerId;
    private String sellerId;
    private List<String> products;
}
