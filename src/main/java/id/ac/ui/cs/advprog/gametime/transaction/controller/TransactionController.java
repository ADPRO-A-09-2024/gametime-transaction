package id.ac.ui.cs.advprog.gametime.transaction.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import id.ac.ui.cs.advprog.gametime.transaction.dto.PayTransactionResponseDTO;
import id.ac.ui.cs.advprog.gametime.transaction.dto.TransactionDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import id.ac.ui.cs.advprog.gametime.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            Integer.parseInt(transactionDTO.getBuyerId());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Buyer ID is not valid");
        }
        try {
            Integer.parseInt(transactionDTO.getSellerId());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Seller ID is not valid");
        }

        return ResponseEntity.ok(transactionService.createTransaction(transactionDTO));
    }

    @GetMapping("/get/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("transactionId") String transactionId) {
        UUID id;
        try {
            id = UUID.fromString(transactionId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Transaction ID is not valid");
        }

        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping("/getByBuyer/{buyerId}")
    public ResponseEntity<List<Transaction>> getTransactionsByBuyer(@PathVariable("buyerId") String buyerId) {
        Integer id;
        try {
            id = Integer.parseInt(buyerId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Buyer ID is not valid");
        }

        return ResponseEntity.ok(transactionService.getTransactionsByBuyer(id));
    }

    @GetMapping("/getBySeller/{sellerId}")
    public ResponseEntity<List<Transaction>> getTransactionsBySeller(@PathVariable("sellerId") String sellerId) {
        Integer id;
        try {
            id = Integer.parseInt(sellerId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Seller ID is not valid");
        }

        return ResponseEntity.ok(transactionService.getTransactionsBySeller(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @PostMapping("/pay/{transactionId}")
    public ResponseEntity<PayTransactionResponseDTO> payTransaction(@PathVariable("transactionId") String transactionId) throws ExecutionException, InterruptedException {
        UUID id;
        try {
            id = UUID.fromString(transactionId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Transaction ID is not valid");
        }

        CompletableFuture<Transaction> currentTransaction = transactionService.payTransaction(id);
        CompletableFuture<List<Transaction>> otherWaitingTransactions = transactionService.getOtherWaitingTransactions(id);

        CompletableFuture.allOf(currentTransaction, otherWaitingTransactions).join();

        PayTransactionResponseDTO response = new PayTransactionResponseDTO(
                currentTransaction.get(), otherWaitingTransactions.get());

        return ResponseEntity.ok(response);
    }
}
