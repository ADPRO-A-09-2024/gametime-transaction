package id.ac.ui.cs.advprog.gametime.transaction.controller;

import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import id.ac.ui.cs.advprog.gametime.transaction.dto.ProductReviewDTO;
import id.ac.ui.cs.advprog.gametime.transaction.service.ProductReviewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
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

    @GetMapping("/product")
    public ResponseEntity<List<ProductReview>> getProductReviewByProduct(@RequestBody Map<String, String> requestBody) {
        List<ProductReview> productReviews = productReviewService.getProductReviewsByProduct(requestBody.get("productId"));
        return ResponseEntity.ok(productReviews);
    }

    @GetMapping("/author")
    public ResponseEntity<List<ProductReview>> getProductReviewByAuthor(@RequestBody Map<String, String> requestBody) {
        List<ProductReview> productReviews = productReviewService.getProductReviewsByAuthor(requestBody.get("authorId"));
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
