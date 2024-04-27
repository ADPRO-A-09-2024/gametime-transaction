package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.SearchStrategy;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.SearchStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Mock
    private SearchStrategyFactory searchStrategyFactory;

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

        when(searchStrategyFactory.getStrategy("name")).thenReturn(searchStrategy);
        when(searchStrategy.search("Test")).thenReturn(products);

        List<Product> result = productService.search("name", "Test");

        assertEquals(products, result);
    }

    @Test
    public void testSearchInvalidType() {
        when(searchStrategyFactory.getStrategy("invalid")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> productService.search("invalid", "Test"));
    }
}