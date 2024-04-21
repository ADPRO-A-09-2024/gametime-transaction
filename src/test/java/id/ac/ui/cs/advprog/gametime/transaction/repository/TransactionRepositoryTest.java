package id.ac.ui.cs.advprog.gametime.transaction.repository;

import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionRepositoryTest {
    TransactionRepository transactionRepository;
    List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        this.transactionRepository = new TransactionRepository();
        this.transactions = new ArrayList<>();

        List<String> productsId = new ArrayList<>();
        productsId.add("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3452");

        Transaction transaction1 = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                productsId,
                new Date(),
                300000,
                "WAITING");

        Transaction transaction2 = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3465",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3464",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3463",
                productsId,
                new Date(),
                400000,
                "SUCCESS");

        this.transactions.add(transaction1);
        this.transactions.add(transaction2);
    }

    @Test
    void testCreate() {
        Transaction transaction1 = this.transactions.getFirst();
        Transaction transactionFromCreate = this.transactionRepository.create(
                transaction1.getBuyerId(),
                transaction1.getSellerId(),
                transaction1.getProductsId(),
                transaction1.getPrice(),
                transaction1.getPaymentStatus());

        assertEquals(transaction1.getBuyerId(), transactionFromCreate.getBuyerId());
        assertEquals(transaction1.getSellerId(), transactionFromCreate.getSellerId());
        assertEquals(transaction1.getProductsId().getFirst(), transactionFromCreate.getProductsId().getFirst());
        assertEquals(transaction1.getPrice(), transactionFromCreate.getPrice());
        assertEquals(transaction1.getPaymentStatus(), transactionFromCreate.getPaymentStatus());
    }

    @Test
    void testGetByIdValid() {
        Transaction transaction1 = this.transactions.get(0);
        Transaction transaction2 = this.transactions.get(1);
        Transaction transactionFromCreate = this.transactionRepository.create(transaction1.getBuyerId(),
                transaction1.getSellerId(),
                transaction1.getProductsId(),
                transaction1.getPrice(),
                transaction1.getPaymentStatus());
        this.transactionRepository.create(transaction2.getBuyerId(),
                transaction2.getSellerId(),
                transaction2.getProductsId(),
                transaction2.getPrice(),
                transaction2.getPaymentStatus());

        Transaction transactionFromGet = this.transactionRepository.getById(transactionFromCreate.getId());
        assertEquals(transactionFromCreate.getBuyerId(), transactionFromGet.getBuyerId());
        assertEquals(transactionFromCreate.getSellerId(), transactionFromGet.getSellerId());
        assertEquals(transactionFromCreate.getProductsId().getFirst(), transactionFromGet.getProductsId().getFirst());
        assertEquals(transactionFromCreate.getDate(), transactionFromGet.getDate());
        assertEquals(transactionFromCreate.getPrice(), transactionFromGet.getPrice());
        assertEquals(transactionFromCreate.getPaymentStatus(), transactionFromGet.getPaymentStatus());
    }

    @Test
    void testGetByIdNotExist() {
        Transaction transaction1 = this.transactions.get(0);
        Transaction transaction2 = this.transactions.get(1);
        Transaction transactionFromCreate = this.transactionRepository.create(transaction1.getBuyerId(),
                transaction1.getSellerId(),
                transaction1.getProductsId(),
                transaction1.getPrice(),
                transaction1.getPaymentStatus());
        this.transactionRepository.create(transaction2.getBuyerId(),
                transaction2.getSellerId(),
                transaction2.getProductsId(),
                transaction2.getPrice(),
                transaction2.getPaymentStatus());

        Transaction transactionFromGet = this.transactionRepository.getById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3470");
        assertNull(transactionFromGet);
    }

    @Test
    void testGetByIdEmpty() {
        Transaction transactionFromGet = this.transactionRepository.getById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3470");
        assertNull(transactionFromGet);
    }

    @Test
    void testGetByBuyerIdValid() {
        Transaction transaction1 = this.transactions.get(0);
        Transaction transaction2 = this.transactions.get(1);
        Transaction transactionFromCreate = this.transactionRepository.create(transaction1.getBuyerId(),
                transaction1.getSellerId(),
                transaction1.getProductsId(),
                transaction1.getPrice(),
                transaction1.getPaymentStatus());
        this.transactionRepository.create(transaction2.getBuyerId(),
                transaction2.getSellerId(),
                transaction2.getProductsId(),
                transaction2.getPrice(),
                transaction2.getPaymentStatus());

        Transaction transactionFromGet = this.transactionRepository.getByBuyerId(transactionFromCreate.getBuyerId()).getFirst();
        assertEquals(transactionFromCreate.getId(), transactionFromGet.getId());
        assertEquals(transactionFromCreate.getBuyerId(), transactionFromGet.getBuyerId());
        assertEquals(transactionFromCreate.getSellerId(), transactionFromGet.getSellerId());
        assertEquals(transactionFromCreate.getProductsId().getFirst(), transactionFromGet.getProductsId().getFirst());
        assertEquals(transactionFromCreate.getDate(), transactionFromGet.getDate());
        assertEquals(transactionFromCreate.getPrice(), transactionFromGet.getPrice());
        assertEquals(transactionFromCreate.getPaymentStatus(), transactionFromGet.getPaymentStatus());
    }

    @Test
    void testGetByBuyerIdNotExist() {
        Transaction transaction1 = this.transactions.get(0);
        Transaction transaction2 = this.transactions.get(1);
        this.transactionRepository.create(transaction1.getBuyerId(),
                transaction1.getSellerId(),
                transaction1.getProductsId(),
                transaction1.getPrice(),
                transaction1.getPaymentStatus());
        this.transactionRepository.create(transaction2.getBuyerId(),
                transaction2.getSellerId(),
                transaction2.getProductsId(),
                transaction2.getPrice(),
                transaction2.getPaymentStatus());

        List<Transaction> transactionsFromGet = this.transactionRepository.getByBuyerId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3470");
        assertTrue(transactionsFromGet.isEmpty());
    }

    @Test
    void testGetByBuyerIdEmpty() {
        List<Transaction> transactionsFromGet = this.transactionRepository.getByBuyerId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3470");
        assertTrue(transactionsFromGet.isEmpty());
    }

    @Test
    void testGetBySellerIdValid() {
        Transaction transaction1 = this.transactions.get(0);
        Transaction transaction2 = this.transactions.get(1);
        Transaction transactionFromCreate = this.transactionRepository.create(transaction1.getBuyerId(),
                transaction1.getSellerId(),
                transaction1.getProductsId(),
                transaction1.getPrice(),
                transaction1.getPaymentStatus());
        this.transactionRepository.create(transaction2.getBuyerId(),
                transaction2.getSellerId(),
                transaction2.getProductsId(),
                transaction2.getPrice(),
                transaction2.getPaymentStatus());

        Transaction transactionFromGet = this.transactionRepository.getBySellerId(transactionFromCreate.getSellerId()).getFirst();
        assertEquals(transactionFromCreate.getId(), transactionFromGet.getId());
        assertEquals(transactionFromCreate.getBuyerId(), transactionFromGet.getBuyerId());
        assertEquals(transactionFromCreate.getSellerId(), transactionFromGet.getSellerId());
        assertEquals(transactionFromCreate.getProductsId().getFirst(), transactionFromGet.getProductsId().getFirst());
        assertEquals(transactionFromCreate.getDate(), transactionFromGet.getDate());
        assertEquals(transactionFromCreate.getPrice(), transactionFromGet.getPrice());
        assertEquals(transactionFromCreate.getPaymentStatus(), transactionFromGet.getPaymentStatus());
    }

    @Test
    void testGetBySellerIdNotExist() {
        Transaction transaction1 = this.transactions.get(0);
        Transaction transaction2 = this.transactions.get(1);
        this.transactionRepository.create(transaction1.getBuyerId(),
                transaction1.getSellerId(),
                transaction1.getProductsId(),
                transaction1.getPrice(),
                transaction1.getPaymentStatus());
        this.transactionRepository.create(transaction2.getBuyerId(),
                transaction2.getSellerId(),
                transaction2.getProductsId(),
                transaction2.getPrice(),
                transaction2.getPaymentStatus());

        List<Transaction> transactionsFromGet = this.transactionRepository.getBySellerId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3470");
        assertTrue(transactionsFromGet.isEmpty());
    }

    @Test
    void testGetBySellerIdEmpty() {
        List<Transaction> transactionsFromGet = this.transactionRepository.getBySellerId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3470");
        assertTrue(transactionsFromGet.isEmpty());
    }
}
