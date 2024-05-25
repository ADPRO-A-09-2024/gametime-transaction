package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.repository.SearchRepository;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class SearchStrategyFactoryTest {

    @Mock
    private SearchRepository searchRepository;

    private SearchStrategyFactory searchStrategyFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        searchStrategyFactory = new SearchStrategyFactory();
    }

    @Test
    public void testGetStrategyName() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("NAME", searchRepository);
        assertTrue(strategy instanceof SearchByNameStrategy);
    }

    @Test
    public void testGetStrategyCategory() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("CATEGORY", searchRepository);
        assertTrue(strategy instanceof SearchByCategoryStrategy);
    }

    @Test
    public void testGetStrategyRating() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("RATING", searchRepository);
        assertTrue(strategy instanceof SearchByRatingStrategy);
    }

    @Test
    public void testGetStrategyNull() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy(null, searchRepository);
        assertNull(strategy);
    }

    @Test
    public void testGetStrategyInvalid() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("INVALID", searchRepository);
        assertNull(strategy);
    }
}