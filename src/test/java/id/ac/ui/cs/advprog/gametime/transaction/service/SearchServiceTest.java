package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.SearchRepository;
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

public class SearchServiceTest {


    @Mock
    private SearchStrategyFactory searchStrategyFactory;

    @Mock
    private SearchRepository searchRepository;

    @Mock
    private SearchStrategy searchStrategy;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearch() {
        Product product = new Product();
        product.setName("Test Product");
        List<Product> products = Arrays.asList(product);

        when(searchStrategyFactory.getStrategy("name", searchRepository)).thenReturn(searchStrategy);
        when(searchStrategy.search("Test")).thenReturn(products);

        List<Product> result = searchService.search("name", "Test");

        assertEquals(products, result);
    }

    @Test
    public void testSearchInvalidType() {
        when(searchStrategyFactory.getStrategy("invalid", searchRepository)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> searchService.search("invalid", "Test"));
    }

    @Test
    public void testFilterByRatingLessThanEqual() {
        Product product = new Product();
        product.setRating(4.5);
        List<Product> products = Arrays.asList(product);

        when(searchRepository.findByRatingLessThanEqual(4.5)).thenReturn(products);

        CompletableFuture<List<Product>> result = searchService.filterByRatingLessThanEqual(4.5);

        assertEquals(products, result.join());
    }

    @Test
    public void testFilterByRatingGreaterThanEqual() {
        Product product = new Product();
        product.setRating(4.5);
        List<Product> products = Arrays.asList(product);

        when(searchRepository.findByRatingGreaterThanEqual(4.5)).thenReturn(products);

        CompletableFuture<List<Product>> result = searchService.filterByRatingGreaterThanEqual(4.5);

        assertEquals(products, result.join());
    }

    @Test
    public void testFilterByPriceLessThanEqual() {
        Product product = new Product();
        product.setPrice(100);
        List<Product> products = Arrays.asList(product);

        when(searchRepository.findByPriceLessThanEqual(100)).thenReturn(products);

        CompletableFuture<List<Product>> result = searchService.filterByPriceLessThanEqual(100);

        assertEquals(products, result.join());
    }

    @Test
    public void testFilterByPriceGreaterThanEqual() {
        Product product = new Product();
        product.setPrice(100);
        List<Product> products = Arrays.asList(product);

        when(searchRepository.findByPriceGreaterThanEqual(100)).thenReturn(products);

        CompletableFuture<List<Product>> result = searchService.filterByPriceGreaterThanEqual(100);

        assertEquals(products, result.join());
    }

    @Test
    public void testFilterByRatingLessThanEqualInvalid() {
        assertThrows(IllegalArgumentException.class, () -> searchService.filterByRatingLessThanEqual(-1.0));
    }

    @Test
    public void testFilterByRatingGreaterThanEqualInvalid() {
        assertThrows(IllegalArgumentException.class, () -> searchService.filterByRatingGreaterThanEqual(-1.0));
    }

    @Test
    public void testFilterByPriceLessThanEqualInvalid() {
        assertThrows(IllegalArgumentException.class, () -> searchService.filterByPriceLessThanEqual(-1));
    }

    @Test
    public void testFilterByPriceGreaterThanEqualInvalid() {
        assertThrows(IllegalArgumentException.class, () -> searchService.filterByPriceGreaterThanEqual(-1));
    }

}