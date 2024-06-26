package id.ac.ui.cs.advprog.gametime.transaction.controller;

import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import id.ac.ui.cs.advprog.gametime.transaction.dto.ProductReviewDTO;
import id.ac.ui.cs.advprog.gametime.transaction.service.ProductReviewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ProductReviewController {
    @Autowired
    ProductReviewService productReviewService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductReview>> getAllProductReview() {
        return ResponseEntity.ok(productReviewService.getAllProductReviews());
    }

    @GetMapping("/product/{idProduct}")
    public ResponseEntity<List<ProductReview>> getProductReviewByProduct(@PathVariable("idProduct") String idProduct) {
        List<ProductReview> productReviews = productReviewService.getProductReviewsByProduct(idProduct);
        return ResponseEntity.ok(productReviews);
    }

    @GetMapping("/author/{idAuthor}")
    public ResponseEntity<List<ProductReview>> getProductReviewByAuthor(@PathVariable("idAuthor") String idAuthor) {
        List<ProductReview> productReviews = productReviewService.getProductReviewsByAuthor(idAuthor);
        return ResponseEntity.ok(productReviews);
    }

    @PostMapping("")
    public ResponseEntity<ProductReview> addProductReview(@RequestBody ProductReviewDTO productReviewDTO) {
        ProductReview productReview = productReviewService.addProductReview(productReviewDTO);
        return ResponseEntity.ok(productReview);
    }

    @PutMapping("/{idReview}")
    public ResponseEntity<ProductReview> updateProductReview(@PathVariable("idReview") String idReview,
            @RequestBody ProductReviewDTO productReviewDTO) {
        ProductReview productReview = productReviewService.updateProductReview(idReview, productReviewDTO);
        return ResponseEntity.ok(productReview);
    }

    @DeleteMapping("/{idReview}")
    public ResponseEntity<String> deleteProductReview(@PathVariable("idReview") String idReview) {
        productReviewService.deleteProductReview(idReview);
        return ResponseEntity.ok("Review with id: " + idReview + " has been deleted");
    }
}
