package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/get")
    public List<Product> getAllProduct() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/search")
    public String searchProduct() {
        return "Hello World";
    }

    @GetMapping("/get/{idProduct}")
    public Product getProductById(@PathVariable("idProduct") String id) {
        return this.productService.getById(id);
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Map<String, String> requestBody) {
        String sellerId = requestBody.get("sellerId");
        String name = requestBody.get("name");
        String description = requestBody.get("description");
        String category = requestBody.get("category");
        float price = Float.parseFloat(requestBody.get("price"));

        return this.productService.create(sellerId, name, description, category, price);
    }

    @PutMapping("/get/{idProduct}")
    public Product editProduct(@PathVariable("idProduct") String idProduct,
                              @RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        String description = requestBody.get("description");
        String category = requestBody.get("category");
        float price = Float.parseFloat(requestBody.get("price"));

        return this.productService.edit(idProduct, name, description, category, price);
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
