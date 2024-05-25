package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import id.ac.ui.cs.advprog.gametime.transaction.dto.CreateProductDTO;
import id.ac.ui.cs.advprog.gametime.transaction.dto.UpdateProductDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDTO createProductDTO) {
        try {
            Integer.parseInt(createProductDTO.getSellerId());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Seller ID is not valid");
        }
        try {
            Integer.parseInt(createProductDTO.getPrice());
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Price is not valid");
        }

        return ResponseEntity.ok(productService.createProduct(createProductDTO));
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") String productId) {
        UUID id;
        try {
            id = UUID.fromString(productId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Product ID is not valid");
        }

        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/getBySeller/{sellerId}")
    public ResponseEntity<List<Product>> getProductsBySeller(@PathVariable("sellerId") String sellerId) {
        Integer id;
        try {
            id = Integer.parseInt(sellerId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Seller ID is not valid");
        }

        return ResponseEntity.ok(productService.getProductsBySeller(id));
    }


    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/get/{productId}")
    public ResponseEntity<Product> editProduct(@PathVariable("productId") String productId,
                              @RequestBody UpdateProductDTO updateProductDTO) {

        UUID id;
        try {
            id = UUID.fromString(productId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Product ID is not valid");
        }
        try {
            Integer.parseInt(updateProductDTO.getPrice());
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Price is not valid");
        }

        return ResponseEntity.ok(productService.updateProduct(id, updateProductDTO));
    }

    @GetMapping("/search/{type}/{term}")
    public ResponseEntity<List<Product>> searchProduct(@PathVariable String type, @PathVariable String term) {
        return ResponseEntity.ok(productService.search(type, term));
    }

    @GetMapping("/filter/rating/less/{rating}")
    public ResponseEntity<List<Product>> filterByRatingLessThanEqual(@PathVariable double rating) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(productService.filterByRatingLessThanEqual(rating).get());
    }

    @GetMapping("/filter/rating/greater/{rating}")
    public ResponseEntity<List<Product>> filterByRatingGreaterThanEqual(@PathVariable double rating) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(productService.filterByRatingGreaterThanEqual(rating).get());
    }

    @GetMapping("/filter/price/less/{price}")
    public ResponseEntity<List<Product>> filterByPriceLessThanEqual(@PathVariable int price) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(productService.filterByPriceLessThanEqual(price).get());
    }

    @GetMapping("/filter/price/greater/{price}")
    public ResponseEntity<List<Product>> filterByPriceGreaterThanEqual(@PathVariable int price) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(productService.filterByPriceGreaterThanEqual(price).get());
    }
}