
package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.List;
import java.util.UUID;

import id.ac.ui.cs.advprog.gametime.transaction.dto.CreateProductDTO;
import id.ac.ui.cs.advprog.gametime.transaction.dto.UpdateProductDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/search")
    public String searchProduct() {
        return "Hello World";
    }

    @GetMapping("/search/{type}/{term}")
    public ResponseEntity<List<Product>> searchProduct(@PathVariable String type, @PathVariable String term) {
        return ResponseEntity.ok(productService.search(type, term));
    }
}
