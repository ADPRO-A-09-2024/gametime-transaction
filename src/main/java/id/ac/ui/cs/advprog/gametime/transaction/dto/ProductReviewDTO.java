package id.ac.ui.cs.advprog.gametime.transaction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class ProductReviewDTO {
    private String authorId;
    private String productId;
    private String content;
    private String rating;
}
