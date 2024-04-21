package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
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
        String[] productsIdArray = new Gson().fromJson(requestBody.get("price"), String[].class);
        List<String> productsId = Arrays.asList(productsIdArray);

        return this.transactionService.create(buyerId, sellerId, productsId);
    }

    @PostMapping("/pay/{id}")
    public Transaction pay(@PathVariable("id") String id,
                           @RequestBody Map<String, String> requestBody) {
        return this.transactionService.pay(id);
    }

    @GetMapping("/get/{userId}")
    public List<Transaction> getHistory(@PathVariable("userId") String userId,
                                        @RequestBody Map<String, String> requestBody) {
        return this.transactionService.getHistory(userId);
    }
}
