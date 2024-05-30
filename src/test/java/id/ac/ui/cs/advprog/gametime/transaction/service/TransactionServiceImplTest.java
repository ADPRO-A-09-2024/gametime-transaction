package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.dto.TransactionDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.enums.PaymentStatus;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.Transaction;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.TransactionRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class TransactionServiceImplTest {
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    TransactionServiceImpl transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTransaction() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setBuyerId("1");
        transactionDTO.setSellerId("2");

        UUID id = UUID.randomUUID();
        ArrayList<String> products = new ArrayList<>();
        products.add(id.toString());
        transactionDTO.setProducts(products);

        Product product = new Product();
        product.setPrice(100000);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        User buyer = new User();
        User seller = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(buyer));
        when(userRepository.findById(2)).thenReturn(Optional.of(seller));

        Transaction transaction = new Transaction();
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction transactionFromCreate = transactionService.createTransaction(transactionDTO);
        assertEquals(transaction, transactionFromCreate);
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findById(2);
        verify(productRepository, times(1)).findById(id);
        verify(transactionRepository, times(1)).save(any(Transaction.class));

    }

    @Test
    void testCreateTransactionBuyerNotFound() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setBuyerId("1");

        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                transactionService.createTransaction(transactionDTO));
        verify(userRepository, times(1)).findById(1);
        verify(productRepository, times(0)).findById(any(UUID.class));
    }

    @Test
    void testCreateTransactionSellerNotFound() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setBuyerId("1");
        transactionDTO.setSellerId("2");

        User buyer = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(buyer));
        when(userRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                transactionService.createTransaction(transactionDTO));
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findById(2);
        verify(productRepository, times(0)).findById(any(UUID.class));
    }

    @Test
    void testCreateTransactionProductsNotFound() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setBuyerId("1");
        transactionDTO.setSellerId("2");

        UUID id = UUID.randomUUID();
        ArrayList<String> products = new ArrayList<>();
        products.add(id.toString());
        transactionDTO.setProducts(products);
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        User buyer = new User();
        User seller = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(buyer));
        when(userRepository.findById(2)).thenReturn(Optional.of(seller));

        assertThrows(IllegalArgumentException.class, () ->
                transactionService.createTransaction(transactionDTO));
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findById(2);
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void testGetTransactionById() {
        Transaction transaction = new Transaction();
        UUID id = UUID.randomUUID();
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));

        assertEquals(transaction, transactionService.getTransactionById(id));
        verify(transactionRepository, times(1)).findById(id);
    }

    @Test
    void testGetTransactionByBuyer() {
        User buyer1 = new User();
        buyer1.setId(1);
        User buyer2 = new User();
        buyer2.setId(2);

        Transaction transaction1 = new Transaction();
        transaction1.setBuyer(buyer1);
        Transaction transaction2 = new Transaction();
        transaction2.setBuyer(buyer2);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> transactionsFromGet = transactionService.getTransactionsByBuyer(1);
        assertEquals(transaction1, transactionsFromGet.getFirst());
        assertEquals(1,  transactionsFromGet.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testGetTransactionsByBuyerEmpty() {
        User buyer1 = new User();
        buyer1.setId(1);
        User buyer2 = new User();
        buyer2.setId(2);

        Transaction transaction1 = new Transaction();
        transaction1.setBuyer(buyer1);
        Transaction transaction2 = new Transaction();
        transaction2.setBuyer(buyer2);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> transactionsFromGet = transactionService.getTransactionsByBuyer(3);
        assertTrue(transactionsFromGet.isEmpty());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testGetTransactionBySeller() {
        User seller1 = new User();
        seller1.setId(1);
        User seller2 = new User();
        seller2.setId(2);

        Transaction transaction1 = new Transaction();
        transaction1.setSeller(seller1);
        Transaction transaction2 = new Transaction();
        transaction2.setSeller(seller2);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> transactionsFromGet = transactionService.getTransactionsBySeller(1);
        assertEquals(transaction1, transactionsFromGet.getFirst());
        assertEquals(1,  transactionsFromGet.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testGetTransactionsBySellerEmpty() {
        User seller1 = new User();
        seller1.setId(1);
        User seller2 = new User();
        seller2.setId(2);

        Transaction transaction1 = new Transaction();
        transaction1.setSeller(seller1);
        Transaction transaction2 = new Transaction();
        transaction2.setSeller(seller2);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> transactionsFromGet = transactionService.getTransactionsBySeller(3);
        assertTrue(transactionsFromGet.isEmpty());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testGetAllTransactions() {
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> transactionsFromGet = transactionService.getAllTransactions();
        assertEquals(transaction1, transactionsFromGet.get(0));
        assertEquals(transaction2,  transactionsFromGet.get(1));
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testPayTransactionSuccess() throws ExecutionException, InterruptedException {
        User seller = new User();
        seller.setBalance(0);
        User buyer = new User();
        buyer.setBalance(100000);

        Transaction transaction = new Transaction();
        UUID id = UUID.randomUUID();
        transaction.setId(id);
        transaction.setBuyer(buyer);
        transaction.setSeller(seller);
        transaction.setPrice(100000);

        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction transactionFromPay = transactionService.payTransaction(id).get();
        assertEquals(transaction, transactionFromPay);
        assertEquals("SUCCESS", transaction.getPaymentStatus());
        assertEquals(0, buyer.getBalance());
        assertEquals(100000, seller.getBalance());
        verify(transactionRepository, times(1)).findById(id);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void testPayTransactionFailed() throws ExecutionException, InterruptedException {
        User seller = new User();
        seller.setBalance(0);
        User buyer = new User();
        buyer.setBalance(50000);

        Transaction transaction = new Transaction();
        UUID id = UUID.randomUUID();
        transaction.setId(id);
        transaction.setBuyer(buyer);
        transaction.setSeller(seller);
        transaction.setPrice(100000);

        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction transactionFromPay = transactionService.payTransaction(id).get();
        assertEquals(transaction, transactionFromPay);
        assertEquals("FAILED", transaction.getPaymentStatus());
        assertEquals(50000, buyer.getBalance());
        assertEquals(0, seller.getBalance());
        verify(transactionRepository, times(1)).findById(id);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void testPayTransactionNotFound() {
        UUID id = UUID.randomUUID();
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                transactionService.payTransaction(id));
        verify(transactionRepository, times(1)).findById(id);
        verify(transactionRepository, times(0)).save(any(Transaction.class));
    }

    @Test
    void testGetOtherWaitingTransactions() throws ExecutionException, InterruptedException {
        User buyer = new User();
        buyer.setId(1);

        UUID id = UUID.randomUUID();

        Transaction transaction1 = new Transaction();
        transaction1.setId(id);
        transaction1.setBuyer(buyer);
        transaction1.setPaymentStatus(PaymentStatus.WAITING.name());
        Transaction transaction2 = new Transaction();
        transaction2.setId(UUID.randomUUID());
        transaction2.setBuyer(buyer);
        transaction2.setPaymentStatus(PaymentStatus.WAITING.name());

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction1));
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> otherTransactions = transactionService.getOtherWaitingTransactions(id).get();
        assertEquals(1, otherTransactions.size());
        assertEquals(transaction2, otherTransactions.getFirst());
        verify(transactionRepository, times(1)).findById(id);
        verify(transactionRepository, times(1)).findAll();
    }


    @Test
    void testGetOtherWaitingTransactionsNotFound() {
        UUID id = UUID.randomUUID();
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
                transactionService.getOtherWaitingTransactions(id));
        verify(transactionRepository, times(1)).findById(id);
        verify(transactionRepository, times(0)).findAll();
    }
}
