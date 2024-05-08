package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


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