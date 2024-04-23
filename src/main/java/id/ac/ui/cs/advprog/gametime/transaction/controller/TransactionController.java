package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @PostMapping("/create")
    public String create(@RequestBody Map<String, String> requestBody) {
        return "hello";
    }

    @PostMapping("/pay/{id}")
    public String pay(@PathVariable("id") String id,
                           @RequestBody Map<String, String> requestBody) {
        return "hello";
    }

    @GetMapping("/get/{userId}")
    public String getHistory(@PathVariable("userId") String userId,
                                        @RequestBody Map<String, String> requestBody) {
        return "hello";
    }
}
