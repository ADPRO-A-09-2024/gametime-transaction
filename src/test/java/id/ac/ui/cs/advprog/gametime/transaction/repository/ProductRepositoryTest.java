package id.ac.ui.cs.advprog.gametime.transaction.repository;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private Product product;

    @BeforeEach
    public void setup() {
        product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Test Product");
        product.setCategory("Test Category");
        product.setRating(4.5);
    }

    @Test
    public void testFindByNameContaining() {
        when(productRepository.findByNameContaining(anyString())).thenReturn(Arrays.asList(product));

        List<Product> products = productRepository.findByNameContaining("Test");
        verify(productRepository, times(1)).findByNameContaining("Test");
    }

    @Test
    public void testFindByCategoryContaining() {
        when(productRepository.findByCategoryContaining(anyString())).thenReturn(Arrays.asList(product));

        List<Product> products = productRepository.findByCategoryContaining("Test");
        verify(productRepository, times(1)).findByCategoryContaining("Test");
    }

    @Test
    public void testFindByRating() {
        when(productRepository.findByRating(anyDouble())).thenReturn(Arrays.asList(product));

        List<Product> products = productRepository.findByRating(4.5);
        verify(productRepository, times(1)).findByRating(4.5);
    }

    @Test
    public void testFindByNameContainingInvalid() {
        List<Product> products = productRepository.findByNameContaining("");
        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindByCategoryContainingInvalid() {
        List<Product> products = productRepository.findByCategoryContaining("");
        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindByRatingInvalid() {
        List<Product> products = productRepository.findByRating(-1.0);
        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindByRatingLessThanEqual() {
        when(productRepository.findByRatingLessThanEqual(anyDouble())).thenReturn(Arrays.asList(product));

        List<Product> products = productRepository.findByRatingLessThanEqual(4.5);
        verify(productRepository, times(1)).findByRatingLessThanEqual(4.5);
    }

    @Test
    public void testFindByRatingGreaterThanEqual() {
        when(productRepository.findByRatingGreaterThanEqual(anyDouble())).thenReturn(Arrays.asList(product));

        List<Product> products = productRepository.findByRatingGreaterThanEqual(4.5);
        verify(productRepository, times(1)).findByRatingGreaterThanEqual(4.5);
    }

    @Test
    public void testFindByPriceLessThanEqual() {
        when(productRepository.findByPriceLessThanEqual(anyInt())).thenReturn(Arrays.asList(product));

        List<Product> products = productRepository.findByPriceLessThanEqual(100);
        verify(productRepository, times(1)).findByPriceLessThanEqual(100);
    }

    @Test
    public void testFindByPriceGreaterThanEqual() {
        when(productRepository.findByPriceGreaterThanEqual(anyInt())).thenReturn(Arrays.asList(product));

        List<Product> products = productRepository.findByPriceGreaterThanEqual(100);
        verify(productRepository, times(1)).findByPriceGreaterThanEqual(100);
    }

    @Test
    public void testFindByRatingLessThanEqualInvalid() {
        List<Product> products = productRepository.findByRatingLessThanEqual(-1.0);
        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindByRatingGreaterThanEqualInvalid() {
        List<Product> products = productRepository.findByRatingGreaterThanEqual(-1.0);
        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindByPriceLessThanEqualInvalid() {
        List<Product> products = productRepository.findByPriceLessThanEqual(-1);
        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindByPriceGreaterThanEqualInvalid() {
        List<Product> products = productRepository.findByPriceGreaterThanEqual(-1);
        assertTrue(products.isEmpty());
    }
}