package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;


import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class SearchController {
    @Autowired
    SearchService searchService;



    @GetMapping("/search/{type}/{term}")
    public ResponseEntity<List<Product>> searchProduct(@PathVariable String type, @PathVariable String term) {
        return ResponseEntity.ok(searchService.search(type, term));
    }

    @GetMapping("/filter/rating/less/{rating}")
    public ResponseEntity<List<Product>> filterByRatingLessThanEqual(@PathVariable double rating) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(searchService.filterByRatingLessThanEqual(rating).get());
    }

    @GetMapping("/filter/rating/greater/{rating}")
    public ResponseEntity<List<Product>> filterByRatingGreaterThanEqual(@PathVariable double rating) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(searchService.filterByRatingGreaterThanEqual(rating).get());
    }

    @GetMapping("/filter/price/less/{price}")
    public ResponseEntity<List<Product>> filterByPriceLessThanEqual(@PathVariable int price) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(searchService.filterByPriceLessThanEqual(price).get());
    }

    @GetMapping("/filter/price/greater/{price}")
    public ResponseEntity<List<Product>> filterByPriceGreaterThanEqual(@PathVariable int price) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(searchService.filterByPriceGreaterThanEqual(price).get());
    }







}