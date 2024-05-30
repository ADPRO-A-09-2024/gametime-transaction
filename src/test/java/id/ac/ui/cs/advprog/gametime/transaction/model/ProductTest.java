package id.ac.ui.cs.advprog.gametime.transaction.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductTest {
    @InjectMocks
    Product product;

    @Test
    void testIdGetterAndSetter() {
        UUID id = UUID.randomUUID();
        product.setId(id);
        assertEquals(id, product.getId());
    }

    @Test
    void testSellerGetterAndSetter() {
        User seller = new User();
        product.setSeller(seller);
        assertEquals(seller, product.getSeller());
    }

    @Test
    void testNameGetterAndSetter() {
        String name = "Boneka doraemon";
        product.setName(name);
        assertEquals(name, product.getName());
    }

    @Test
    void testDescriptionGetterAndSetter() {
        String description = "Boneka bagus";
        product.setDescription(description);
        assertEquals(description, product.getDescription());
    }

    @Test
    void testCategoryGetterAndSetter() {
        String category = "Boneka";
        product.setCategory(category);
        assertEquals(category, product.getCategory());
    }

    @Test
    void testPriceGetterAndSetter() {
        int price = 100000;
        product.setPrice(price);
        assertEquals(price, product.getPrice());
    }
}


