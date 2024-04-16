package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("")
    public String getAllProduct() {
        return "Hello World";
    }

    @GetMapping("/search")
    public String searchProduct() {
        return "Hello World";
    }

    @GetMapping("/{idProduct}")
    public String getProductByID(@PathVariable("idProduct") String id) {
        return "Hello " + id + "!";
    }

    @PostMapping("")
    public String addProduct() {
        return "Hello World";
    }

    @PutMapping("/{idProduct}")
    public String updateProduct(@PathVariable("idProduct") String id) {
        return "Hello " + id + "!";
    }

    @DeleteMapping("/{idProduct}")
    public String deleteProduct(@PathVariable("idProduct") String id) {
        return "Hello " + id + "!";
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
