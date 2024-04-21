package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import id.ac.ui.cs.advprog.gametime.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/create")
    public Transaction create(@RequestBody Map<String, String> requestBody) {
        String buyerId = requestBody.get("buyerId");
        String sellerId = requestBody.get("sellerId");
        // TODO
        // get list of products
        // get price of products
//        return transactionService.create(buyerId, sellerId, products, price);

        return null;
    }

    @PostMapping("/pay/{id}")
    public Transaction pay(@PathVariable("id") String id,
                           @RequestBody Map<String, String> requestBody) {
        return null;
    }

    @GetMapping("/get/{userId}")
    public List<Transaction> getHistory(@PathVariable("userId") String userId,
                                        @RequestBody Map<String, String> requestBody) {
        return transactionService.getHistory(userId);
    }
}
