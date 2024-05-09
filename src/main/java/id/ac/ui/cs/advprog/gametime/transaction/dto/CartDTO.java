package id.ac.ui.cs.advprog.gametime.transaction.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartDTO {
    // Setters
    // Getters
    private Integer userId;
    private List<CartItemDTO> items;

}
