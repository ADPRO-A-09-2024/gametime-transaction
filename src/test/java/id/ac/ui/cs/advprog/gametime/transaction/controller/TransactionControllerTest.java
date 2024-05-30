package id.ac.ui.cs.advprog.gametime.transaction.controller;

import id.ac.ui.cs.advprog.gametime.transaction.dto.PayTransactionResponseDTO;
import id.ac.ui.cs.advprog.gametime.transaction.dto.TransactionDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import id.ac.ui.cs.advprog.gametime.transaction.service.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {
    @Mock
    TransactionServiceImpl transactionService;

    @InjectMocks
    TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTransactionValid() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setBuyerId("1");
        transactionDTO.setSellerId("2");
        transactionDTO.setProducts(new ArrayList<>());

        Transaction transaction = new Transaction();
        when(transactionService.createTransaction(transactionDTO)).thenReturn(transaction);

        ResponseEntity<Transaction> response = transactionController.createTransaction(transactionDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody());
        verify(transactionService, times(1)).createTransaction(transactionDTO);
    }

    @Test
    void testCreateTransactionInvalidBuyerId() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setBuyerId("x");
        transactionDTO.setSellerId("2");
        transactionDTO.setProducts(new ArrayList<>());

        assertThrows(ResponseStatusException.class, () ->
                transactionController.createTransaction(transactionDTO));
        verify(transactionService, times(0)).createTransaction(any(TransactionDTO.class));
    }

    @Test
    void testCreateTransactionInvalidSellerId() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setBuyerId("1");
        transactionDTO.setSellerId("x");
        transactionDTO.setProducts(new ArrayList<>());

        assertThrows(ResponseStatusException.class, () ->
                transactionController.createTransaction(transactionDTO));
        verify(transactionService, times(0)).createTransaction(any(TransactionDTO.class));
    }

    @Test
    void testGetTransactionByIdValid() {
        UUID id = UUID.randomUUID();
        Transaction transaction = new Transaction();
        when(transactionService.getTransactionById(id)).thenReturn(transaction);

        ResponseEntity<Transaction> response = transactionController.getTransactionById(id.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody());
        verify(transactionService, times(1)).getTransactionById(id);
    }

    @Test
    void testGetTransactionByIdInvalidId() {
        assertThrows(ResponseStatusException.class, () ->
                transactionController.getTransactionById("x"));
        verify(transactionService, times(0)).getTransactionById(any(UUID.class));
    }

    @Test
    void testGetTransactionsByBuyerValid() {
        List<Transaction> transactions = new ArrayList<>();
        when(transactionService.getTransactionsByBuyer(1)).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = transactionController.getTransactionsByBuyer("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
        verify(transactionService, times(1)).getTransactionsByBuyer(1);
    }

    @Test
    void testGetTransactionsByBuyerInvalidBuyerId() {
        assertThrows(ResponseStatusException.class, () ->
                transactionController.getTransactionsByBuyer("x"));
        verify(transactionService, times(0)).getTransactionsByBuyer(any(Integer.class));
    }

    @Test
    void testGetTransactionsBySellerValid() {
        List<Transaction> transactions = new ArrayList<>();
        when(transactionService.getTransactionsBySeller(1)).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = transactionController.getTransactionsBySeller("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
        verify(transactionService, times(1)).getTransactionsBySeller(1);
    }

    @Test
    void testGetTransactionsBySellerInvalidSellerId() {
        assertThrows(ResponseStatusException.class, () ->
                transactionController.getTransactionsBySeller("x"));
        verify(transactionService, times(0)).getTransactionsBySeller(any(Integer.class));
    }

    @Test
    void testGetAllTransactionsValid() {
        List<Transaction> transactions = new ArrayList<>();
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = transactionController.getAllTransactions();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
        verify(transactionService, times(1)).getAllTransactions();
    }

    @Test
    void testPayTransactionValid() throws ExecutionException, InterruptedException {
        UUID id = UUID.randomUUID();
        Transaction transaction = new Transaction();
        List<Transaction> otherTransactions = new ArrayList<>();
        when(transactionService.payTransaction(id)).thenReturn(CompletableFuture.completedFuture(transaction));
        when(transactionService.getOtherWaitingTransactions(id)).thenReturn(CompletableFuture.completedFuture(otherTransactions));

        ResponseEntity<PayTransactionResponseDTO> response = transactionController.payTransaction(id.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody().getTransaction());
        assertEquals(otherTransactions, response.getBody().getOtherWaitingTransactions());
        verify(transactionService, times(1)).payTransaction(id);
        verify(transactionService, times(1)).getOtherWaitingTransactions(id);
    }

    @Test
    void testPayTransactionInvalidId() {
        assertThrows(ResponseStatusException.class, () ->
                transactionController.payTransaction("x"));
        verify(transactionService, times(0)).payTransaction(any(UUID.class));
        verify(transactionService, times(0)).getOtherWaitingTransactions(any(UUID.class));
    }
}
