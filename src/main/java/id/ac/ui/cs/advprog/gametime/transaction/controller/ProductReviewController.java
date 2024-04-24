package id.ac.ui.cs.advprog.gametime.transaction.controller;

import id.ac.ui.cs.advprog.gametime.transaction.model.ProductReview;
import id.ac.ui.cs.advprog.gametime.transaction.dto.ProductReviewDTO;
import id.ac.ui.cs.advprog.gametime.transaction.service.ProductReviewService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;
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
        String productIdString = requestBody.get("productId");
        UUID productId;
        try {
            productId = UUID.fromString(productIdString);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Product ID is not valid");
        }
        List<ProductReview> productReviews = productReviewService.getProductReviewsByProduct(productId);
        return ResponseEntity.ok(productReviews);
    }

    @GetMapping("/author")
    public ResponseEntity<List<ProductReview>> getProductReviewByAuthor(@RequestBody Map<String, String> requestBody) {
        Integer authorId;
        try {
            authorId = Integer.parseInt(requestBody.get("authorId"));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Author ID is not valid");
        }
        List<ProductReview> productReviews = productReviewService.getProductReviewsByAuthor(authorId);
        return ResponseEntity.ok(productReviews);
    }

    @SuppressWarnings("unused")
    @PostMapping("")
    public ResponseEntity<ProductReview> addProductReview(@RequestBody ProductReviewDTO productReviewDTO) {
        try {
            Integer authorId = Integer.parseInt(productReviewDTO.getAuthorId());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Author ID is not valid");
        }
        try {
            UUID productId = UUID.fromString(productReviewDTO.getProductId());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Product ID is not valid");
        }
        try {
            Double rating = Double.parseDouble(productReviewDTO.getRating());
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Rating is not valid");
        }
        ProductReview productReview = productReviewService.addProductReview(productReviewDTO);
        return ResponseEntity.ok(productReview);
    }

    @SuppressWarnings("unused")
    @PutMapping("/{idReview}")
    public ResponseEntity<ProductReview> updateProductReview(@PathVariable("idReview") String idReview,
            @RequestBody ProductReviewDTO productReviewDTO) {
        UUID productReviewId;
        try {
            productReviewId = UUID.fromString(idReview);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Review ID is not valid");
        }
        try {
            Integer authorId = Integer.parseInt(productReviewDTO.getAuthorId());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Author ID is not valid");
        }
        try {
            UUID productId = UUID.fromString(productReviewDTO.getProductId());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Product ID is not valid");
        }
        try {
            Double rating = Double.parseDouble(productReviewDTO.getRating());
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Rating is not valid");
        }
        ProductReview productReview = productReviewService.updateProductReview(productReviewId, productReviewDTO);
        return ResponseEntity.ok(productReview);
    }

    @DeleteMapping("/{idReview}")
    public ResponseEntity<String> deleteProductReview(@PathVariable("idReview") String idReview) {
        UUID productReviewId;
        try {
            productReviewId = UUID.fromString(idReview);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Review ID is not valid");
        }
        productReviewService.deleteProductReview(productReviewId);
        return ResponseEntity.ok("Review deleted");
    }
}
