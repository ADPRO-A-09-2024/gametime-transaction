package id.ac.ui.cs.advprog.gametime.transaction.model.Builder;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionBuilderTest {
    TransactionBuilder transactionBuilder;

    @BeforeEach
    void setUp() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product());

        transactionBuilder = new TransactionBuilder()
                .id(UUID.randomUUID())
                .buyer(new User())
                .seller(new User())
                .products(products)
                .date(new Date())
                .price(100000)
                .paymentStatus("WAITING");
    }

    @Test
    void testIdValid() {
        UUID id = UUID.randomUUID();
        transactionBuilder.id(id);
        assertEquals(id, transactionBuilder.build().getId());
    }

    @Test
    void testIdNull() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionBuilder.id(null));
    }

    @Test
    void testBuyerValid() {
        User buyer = new User();
        transactionBuilder.buyer(buyer);
        assertEquals(buyer, transactionBuilder.build().getBuyer());
    }

    @Test
    void testBuyerNull() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionBuilder.buyer(null));
    }

    @Test
    void testSellerValid() {
        User seller = new User();
        transactionBuilder.seller(seller);
        assertEquals(seller, transactionBuilder.build().getSeller());
    }

    @Test
    void testSellerNull() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionBuilder.seller(null));
    }

    @Test
    void testProductsValid() {
        ArrayList<Product> products = new ArrayList<>();
        Product product = new Product();
        products.add(product);
        transactionBuilder.products(products);

        assertEquals(1, transactionBuilder.build().getProducts().size());
        assertEquals(product, transactionBuilder.build().getProducts().getFirst());
    }

    @Test
    void testProductsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionBuilder.products(null));
    }

    @Test
    void testProductsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionBuilder.products(new ArrayList<>()));
    }

    @Test
    void testDateValid() {
        Date date = new Date();
        transactionBuilder.date(date);
        assertEquals(date, transactionBuilder.build().getDate());
    }

    @Test
    void testDateNull() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionBuilder.date(null));
    }

    @Test
    void testPriceValid() {
        transactionBuilder.price(100000);
        assertEquals(100000, transactionBuilder.build().getPrice());
    }

    @Test
    void testPrice0() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionBuilder.price(0));
    }

    @Test
    void testPriceNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionBuilder.price(-10));
    }

    @Test
    void testPaymentStatusValidWaiting() {
        transactionBuilder.paymentStatus("WAITING");
        assertEquals("WAITING", transactionBuilder.build().getPaymentStatus());
    }

    @Test
    void testPaymentStatusValidSuccess() {
        transactionBuilder.paymentStatus("SUCCESS");
        assertEquals("SUCCESS", transactionBuilder.build().getPaymentStatus());
    }

    @Test
    void testPaymentStatusValidFailed() {
        transactionBuilder.paymentStatus("FAILED");
        assertEquals("FAILED", transactionBuilder.build().getPaymentStatus());
    }

    @Test
    void testPaymentStatusNull() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionBuilder.paymentStatus(null));
    }

    @Test
    void testPaymentStatusEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionBuilder.paymentStatus(""));
    }

    @Test
    void testPaymentStatusInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionBuilder.paymentStatus("CANCELED"));
    }
}
