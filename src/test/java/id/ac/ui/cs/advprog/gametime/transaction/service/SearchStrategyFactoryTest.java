package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class SearchStrategyFactoryTest {

    @Mock
    private ProductRepository productRepository;

    private SearchStrategyFactory searchStrategyFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        searchStrategyFactory = new SearchStrategyFactory();
    }

    @Test
    public void testGetStrategyName() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("NAME", productRepository);
        assertTrue(strategy instanceof SearchByNameStrategy);
    }

    @Test
    public void testGetStrategyCategory() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("CATEGORY", productRepository);
        assertTrue(strategy instanceof SearchByCategoryStrategy);
    }

    @Test
    public void testGetStrategyRating() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("RATING", productRepository);
        assertTrue(strategy instanceof SearchByRatingStrategy);
    }

    @Test
    public void testGetStrategyNull() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy(null, productRepository);
        assertNull(strategy);
    }

    @Test
    public void testGetStrategyInvalid() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("INVALID", productRepository);
        assertNull(strategy);
    }
}