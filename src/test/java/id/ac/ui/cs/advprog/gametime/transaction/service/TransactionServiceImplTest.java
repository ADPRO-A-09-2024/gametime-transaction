package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Buyer;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.TransactionRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {
    @InjectMocks
    TransactionServiceImpl transactionService;
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    Buyer buyer;

    List<Transaction> transactions;
    Product product;

    @BeforeEach
    void setUp() {
        List<String> productsId = new ArrayList<>();
        productsId.add("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3452");

        this.product = new Product("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3452",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3451",
                "Boneka doraemon",
                "Boneka doraemon asli 100% reject pabrik warna kuning",
                "Boneka",
                100000,
                0,
                new ArrayList<>());

        Transaction transaction1 = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3453",
                productsId,
                new Date(),
                100000,
                "WAITING");

        Transaction transaction2 = new Transaction("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3465",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3464",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3463",
                productsId,
                new Date(),
                100000,
                "SUCCESS");

        this.transactions = new ArrayList<>();
        this.transactions.add(transaction1);
        this.transactions.add(transaction2);
    }

    @Test
    void testCreate() {
        Transaction transaction1 = this.transactions.getFirst();
        doReturn(transaction1).when(this.transactionRepository).create(
                transaction1.getBuyerId(),
                transaction1.getSellerId(),
                transaction1.getProductsId(),
                transaction1.getPrice(),
                transaction1.getPaymentStatus());
        doReturn(this.product).when(this.productRepository).getById(transaction1.getProductsId().getFirst());

        Transaction transactionFromCreate = this.transactionService.create(
                transaction1.getBuyerId(),
                transaction1.getSellerId(),
                transaction1.getProductsId());

        assertEquals(transaction1.getBuyerId(), transactionFromCreate.getBuyerId());
        assertEquals(transaction1.getSellerId(), transactionFromCreate.getSellerId());
        assertEquals(transaction1.getProductsId().getFirst(), transactionFromCreate.getProductsId().getFirst());
        assertEquals(transaction1.getPrice(), transactionFromCreate.getPrice());
        assertEquals(transaction1.getPaymentStatus(), transactionFromCreate.getPaymentStatus());

        verify(this.transactionRepository, times(1)).create(
                transaction1.getBuyerId(),
                transaction1.getSellerId(),
                transaction1.getProductsId(),
                transaction1.getPrice(),
                transaction1.getPaymentStatus());
        verify(this.productRepository, times(1)).getById(transaction1.getProductsId().getFirst());
    }

    @Test
    void testPayEnoughBalance() {
        Transaction transaction1 = this.transactions.getFirst();
        doReturn(transaction1).when(this.transactionRepository).getById(transaction1.getId());
        doReturn(this.buyer).when(this.userRepository).getById(transaction1.getBuyerId());
        doReturn(100000F).when(this.buyer).getBalance();

        Transaction transactionFromPay = this.transactionService.pay(transaction1.getId());
        assertEquals(transaction1.getBuyerId(), transactionFromPay.getBuyerId());
        assertEquals(transaction1.getSellerId(), transactionFromPay.getSellerId());
        assertEquals(transaction1.getProductsId().getFirst(), transactionFromPay.getProductsId().getFirst());
        assertEquals(transaction1.getPrice(), transactionFromPay.getPrice());
        assertEquals("SUCCESS", transactionFromPay.getPaymentStatus());

        verify(this.transactionRepository, times(1)).getById(transaction1.getId());
        verify(this.userRepository, times(1)).getById(transaction1.getBuyerId());
        verify(this.buyer, times(1)).getBalance();
    }

    @Test
    void testPayNotEnoughBalance() {
        Transaction transaction1 = this.transactions.getFirst();
        doReturn(transaction1).when(this.transactionRepository).getById(transaction1.getId());
        doReturn(this.buyer).when(this.userRepository).getById(transaction1.getBuyerId());
        doReturn(20000F).when(this.buyer).getBalance();

        Transaction transactionFromPay = this.transactionService.pay(transaction1.getId());
        assertEquals(transaction1.getBuyerId(), transactionFromPay.getBuyerId());
        assertEquals(transaction1.getSellerId(), transactionFromPay.getSellerId());
        assertEquals(transaction1.getProductsId().getFirst(), transactionFromPay.getProductsId().getFirst());
        assertEquals(transaction1.getPrice(), transactionFromPay.getPrice());
        assertEquals("FAILED", transactionFromPay.getPaymentStatus());

        verify(this.transactionRepository, times(1)).getById(transaction1.getId());
        verify(this.userRepository, times(1)).getById(transaction1.getBuyerId());
        verify(this.buyer, times(1)).getBalance();
    }

    @Test
    void testGetHistorySeller() {
        Transaction transaction1 = this.transactions.getFirst();
        List<Transaction> transaction1List = new ArrayList<>();
        transaction1List.add(transaction1);
        doReturn(transaction1List).when(this.transactionRepository).getBySellerId(transaction1.getSellerId());

        Transaction transactionFromGet = this.transactionService.getHistory(transaction1.getSellerId()).getFirst();

        assertEquals(transaction1.getBuyerId(), transactionFromGet.getBuyerId());
        assertEquals(transaction1.getSellerId(), transactionFromGet.getSellerId());
        assertEquals(transaction1.getProductsId().getFirst(), transactionFromGet.getProductsId().getFirst());
        assertEquals(transaction1.getPrice(), transactionFromGet.getPrice());
        assertEquals(transaction1.getPaymentStatus(), transactionFromGet.getPaymentStatus());

        verify(this.transactionRepository, times(1)).getBySellerId(transaction1.getSellerId());
        verify(this.transactionRepository, times(0)).getByBuyerId(anyString());
    }

    @Test
    void testGetHistoryBuyer() {
        Transaction transaction1 = this.transactions.getFirst();
        List<Transaction> transaction1List = new ArrayList<>();
        transaction1List.add(transaction1);
        doReturn(new ArrayList<>()).when(this.transactionRepository).getBySellerId(transaction1.getBuyerId());
        doReturn(transaction1List).when(this.transactionRepository).getByBuyerId(transaction1.getBuyerId());

        Transaction transactionFromGet = this.transactionService.getHistory(transaction1.getBuyerId()).getFirst();

        assertEquals(transaction1.getBuyerId(), transactionFromGet.getBuyerId());
        assertEquals(transaction1.getSellerId(), transactionFromGet.getSellerId());
        assertEquals(transaction1.getProductsId().getFirst(), transactionFromGet.getProductsId().getFirst());
        assertEquals(transaction1.getPrice(), transactionFromGet.getPrice());
        assertEquals(transaction1.getPaymentStatus(), transactionFromGet.getPaymentStatus());

        verify(this.transactionRepository, times(1)).getBySellerId(transaction1.getBuyerId());
        verify(this.transactionRepository, times(1)).getByBuyerId(transaction1.getBuyerId());
    }
}
