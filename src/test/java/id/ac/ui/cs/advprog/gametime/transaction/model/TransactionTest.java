package id.ac.ui.cs.advprog.gametime.transaction.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TransactionTest {
    @InjectMocks
    Transaction transaction;

    @Test
    void testIdGetterAndSetter() {
        UUID id = UUID.randomUUID();
        transaction.setId(id);
        assertEquals(id, transaction.getId());
    }

    @Test
    void testBuyerGetterAndSetter() {
        User buyer = new User();
        transaction.setBuyer(buyer);
        assertEquals(buyer, transaction.getBuyer());
    }


    @Test
    void testProductsGetterAndSetter() {
        User seller = new User();
        transaction.setSeller(seller);
        assertEquals(seller, transaction.getSeller());
    }

    @Test
    void testDateGetterAndSetter() {
        Date date = new Date();
        transaction.setDate(date);
        assertEquals(date, transaction.getDate());
    }

    @Test
    void testPriceGetterAndSetter() {
        int price = 100000;
        transaction.setPrice(price);
        assertEquals(price, transaction.getPrice());
    }

    @Test
    void testPaymentStatusGetterAndSetter() {
        String paymentStatus = "WAITING";
        transaction.setPaymentStatus(paymentStatus);
        assertEquals(paymentStatus, transaction.getPaymentStatus());
    }
}
