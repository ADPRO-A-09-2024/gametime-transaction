package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/get")
    public String getAllProduct() {
        return "hello";
    }

    @GetMapping("/search")
    public String searchProduct() {
        return "Hello World";
    }

    @GetMapping("/get/{idProduct}")
    public String getProductById(@PathVariable("idProduct") String id) {
        return "hello";
    }

    @PostMapping("/create")
    public String createProduct(@RequestBody Map<String, String> requestBody) {
       return "hello";
    }

    @PutMapping("/get/{idProduct}")
    public String editProduct(@PathVariable("idProduct") String idProduct,
                              @RequestBody Map<String, String> requestBody) {
        return "hello";
    }

    @GetMapping("/{idProduct}/review")
    public String getProductReview(@PathVariable("idProduct") String id) {
        return "Hello " + id + "!";
    }

    @PostMapping("/{idProduct}/review")
    public String addProductReview(@PathVariable("idProduct") String id) {
        return "Hello " + id + "!";
    }

    @PutMapping("/{idProduct}/review")
    public String updateProductReview(@PathVariable("idProduct") String idProduct,
            @RequestBody Map<String, String> requestBody) {
        return "Hello " + idProduct + " " + requestBody.get("idReview") + "!";
    }

    @DeleteMapping("/{idProduct}/review")
    public String deleteProductReview(@PathVariable("idProduct") String idProduct,
            @RequestBody Map<String, String> requestBody) {
        return "Hello " + idProduct + " " + requestBody.get("idReview") + "!";
    }

}
