package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @GetMapping("buy")
    public String buyProduct(@RequestBody Map<String, String> requestBody) {
        return "Hello " + requestBody.get("idProduct") + "!";
    }

    @PostMapping("buy")
    public String buyProduct() {
        return "Hello World!";
    }
}
