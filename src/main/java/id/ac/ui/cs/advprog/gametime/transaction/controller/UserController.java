package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public String getUserProfile(@RequestBody Map<String, String> requestBody) {
        return "Hello " + requestBody.get("idUser") + "!";
    }

    @GetMapping("/{username}")
    public String getUserByUsername(@PathVariable("username") String username) {
        return "Hello " + username + "!";
    }

    @PutMapping("/profile/update")
    public String updateUserProfile() {
        return "Hello World!";
    }

    @GetMapping("/transaction-history")
    public String getAllTransaction() {
        return "Hello World!";
    }

    @GetMapping("/transaction-history/{idTransaction}")
    public String getTransactionByID(@PathVariable("idTransaction") String id) {
        return "Hello " + id + "!";
    }

    @PostMapping("/transaction-history")
    public String addTransaction() {
        return "Hello World!";
    }

    @PutMapping("/transaction-history/{idTransaction}")
    public String updateTransaction(@PathVariable("idTransaction") String id) {
        return "Hello " + id + "!";
    }

    @DeleteMapping("/transaction-history/{idTransaction}")
    public String deleteTransaction(@PathVariable("idTransaction") String id) {
        return "Hello " + id + "!";
    }

    @GetMapping("/cart")
    public String getCart() {
        return "Hello World!";
    }

    @PostMapping("/cart")
    public String addCart() {
        return "Hello World!";
    }

    @PutMapping("/cart")
    public String updateCart() {
        return "Hello World!";
    }

    @DeleteMapping("/cart")
    public String deleteCart() {
        return "Hello World!";
    }

}
