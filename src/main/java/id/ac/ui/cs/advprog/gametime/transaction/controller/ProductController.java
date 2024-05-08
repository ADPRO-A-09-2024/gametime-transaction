package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.List;
import java.util.UUID;


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

}