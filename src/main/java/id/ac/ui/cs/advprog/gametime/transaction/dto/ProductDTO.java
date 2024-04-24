package id.ac.ui.cs.advprog.gametime.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int sellerId;
    private String name;
    private String description;
    private String category;
    private int price;
}
