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
@SuppressWarnings("unused")
@DataJpaTest
class SearchRepositoryTest {

    @Mock
    private SearchRepository searchRepository;

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
    void testFindByNameContaining() {
        when(searchRepository.findByNameContaining(anyString())).thenReturn(Arrays.asList(product));

        List<Product> products = searchRepository.findByNameContaining("Test");
        verify(searchRepository, times(1)).findByNameContaining("Test");
    }

    @Test
    void testFindByCategoryContaining() {
        when(searchRepository.findByCategoryContaining(anyString())).thenReturn(Arrays.asList(product));

        List<Product> products = searchRepository.findByCategoryContaining("Test");
        verify(searchRepository, times(1)).findByCategoryContaining("Test");
    }

    @Test
    void testFindByRating() {
        when(searchRepository.findByRating(anyDouble())).thenReturn(Arrays.asList(product));

        List<Product> products = searchRepository.findByRating(4.5);
        verify(searchRepository, times(1)).findByRating(4.5);
    }

    @Test
    void testFindByNameContainingInvalid() {
        List<Product> products = searchRepository.findByNameContaining("");
        assertTrue(products.isEmpty());
    }

    @Test
    void testFindByCategoryContainingInvalid() {
        List<Product> products = searchRepository.findByCategoryContaining("");
        assertTrue(products.isEmpty());
    }

    @Test
    void testFindByRatingInvalid() {
        List<Product> products = searchRepository.findByRating(-1.0);
        assertTrue(products.isEmpty());
    }

    @Test
    void testFindByRatingLessThanEqual() {
        when(searchRepository.findByRatingLessThanEqual(anyDouble())).thenReturn(Arrays.asList(product));

        List<Product> products = searchRepository.findByRatingLessThanEqual(4.5);
        verify(searchRepository, times(1)).findByRatingLessThanEqual(4.5);
    }

    @Test
    void testFindByRatingGreaterThanEqual() {
        when(searchRepository.findByRatingGreaterThanEqual(anyDouble())).thenReturn(Arrays.asList(product));

        List<Product> products = searchRepository.findByRatingGreaterThanEqual(4.5);
        verify(searchRepository, times(1)).findByRatingGreaterThanEqual(4.5);
    }

    @Test
    void testFindByPriceLessThanEqual() {
        when(searchRepository.findByPriceLessThanEqual(anyInt())).thenReturn(Arrays.asList(product));

        List<Product> products = searchRepository.findByPriceLessThanEqual(100);
        verify(searchRepository, times(1)).findByPriceLessThanEqual(100);
    }

    @Test
    void testFindByPriceGreaterThanEqual() {
        when(searchRepository.findByPriceGreaterThanEqual(anyInt())).thenReturn(Arrays.asList(product));

        List<Product> products = searchRepository.findByPriceGreaterThanEqual(100);
        verify(searchRepository, times(1)).findByPriceGreaterThanEqual(100);
    }

    @Test
    void testFindByRatingLessThanEqualInvalid() {
        List<Product> products = searchRepository.findByRatingLessThanEqual(-1.0);
        assertTrue(products.isEmpty());
    }

    @Test
    void testFindByRatingGreaterThanEqualInvalid() {
        List<Product> products = searchRepository.findByRatingGreaterThanEqual(-1.0);
        assertTrue(products.isEmpty());
    }

    @Test
    void testFindByPriceLessThanEqualInvalid() {
        List<Product> products = searchRepository.findByPriceLessThanEqual(-1);
        assertTrue(products.isEmpty());
    }

    @Test
    void testFindByPriceGreaterThanEqualInvalid() {
        List<Product> products = searchRepository.findByPriceGreaterThanEqual(-1);
        assertTrue(products.isEmpty());
    }
}