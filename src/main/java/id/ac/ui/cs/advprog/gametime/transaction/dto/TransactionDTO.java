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
    private int buyerId;
    private int sellerId;
    private List<Product> products;
}
