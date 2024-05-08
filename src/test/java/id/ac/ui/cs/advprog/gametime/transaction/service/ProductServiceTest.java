package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.SearchStrategy;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.SearchStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ProductServiceTest {


    @Mock
    private SearchStrategyFactory searchStrategyFactory;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SearchStrategy searchStrategy;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearch() {
        Product product = new Product();
        product.setName("Test Product");
        List<Product> products = Arrays.asList(product);

        when(searchStrategyFactory.getStrategy("name", productRepository)).thenReturn(searchStrategy);
        when(searchStrategy.search("Test")).thenReturn(products);

        List<Product> result = productService.search("name", "Test");

        assertEquals(products, result);
    }

    @Test
    public void testSearchInvalidType() {
        when(searchStrategyFactory.getStrategy("invalid", productRepository)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> productService.search("invalid", "Test"));
    }

    @Test
    public void testFilterByRatingLessThanEqual() {
        Product product = new Product();
        product.setRating(4.5);
        List<Product> products = Arrays.asList(product);

        when(productRepository.findByRatingLessThanEqual(4.5)).thenReturn(products);

        CompletableFuture<List<Product>> result = productService.filterByRatingLessThanEqual(4.5);

        assertEquals(products, result.join());
    }

    @Test
    public void testFilterByRatingGreaterThanEqual() {
        Product product = new Product();
        product.setRating(4.5);
        List<Product> products = Arrays.asList(product);

        when(productRepository.findByRatingGreaterThanEqual(4.5)).thenReturn(products);

        CompletableFuture<List<Product>> result = productService.filterByRatingGreaterThanEqual(4.5);

        assertEquals(products, result.join());
    }

    @Test
    public void testFilterByPriceLessThanEqual() {
        Product product = new Product();
        product.setPrice(100);
        List<Product> products = Arrays.asList(product);

        when(productRepository.findByPriceLessThanEqual(100)).thenReturn(products);

        CompletableFuture<List<Product>> result = productService.filterByPriceLessThanEqual(100);

        assertEquals(products, result.join());
    }

    @Test
    public void testFilterByPriceGreaterThanEqual() {
        Product product = new Product();
        product.setPrice(100);
        List<Product> products = Arrays.asList(product);

        when(productRepository.findByPriceGreaterThanEqual(100)).thenReturn(products);

        CompletableFuture<List<Product>> result = productService.filterByPriceGreaterThanEqual(100);

        assertEquals(products, result.join());
    }

    @Test
    public void testFilterByRatingLessThanEqualInvalid() {
        assertThrows(IllegalArgumentException.class, () -> productService.filterByRatingLessThanEqual(-1.0));
    }

    @Test
    public void testFilterByRatingGreaterThanEqualInvalid() {
        assertThrows(IllegalArgumentException.class, () -> productService.filterByRatingGreaterThanEqual(-1.0));
    }

    @Test
    public void testFilterByPriceLessThanEqualInvalid() {
        assertThrows(IllegalArgumentException.class, () -> productService.filterByPriceLessThanEqual(-1));
    }

    @Test
    public void testFilterByPriceGreaterThanEqualInvalid() {
        assertThrows(IllegalArgumentException.class, () -> productService.filterByPriceGreaterThanEqual(-1));
    }

}