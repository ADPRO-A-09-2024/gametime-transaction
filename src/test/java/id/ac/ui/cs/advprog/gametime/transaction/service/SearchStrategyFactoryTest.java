package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchStrategyFactoryTest {

    private SearchStrategyFactory searchStrategyFactory;

    @BeforeEach
    public void setup() {
        searchStrategyFactory = new SearchStrategyFactory();
    }

    @Test
    public void testGetStrategyName() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("NAME");
        assertTrue(strategy instanceof SearchByNameStrategy);
    }

    @Test
    public void testGetStrategyCategory() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("CATEGORY");
        assertTrue(strategy instanceof SearchByCategoryStrategy);
    }

    @Test
    public void testGetStrategyRating() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("RATING");
        assertTrue(strategy instanceof SearchByRatingStrategy);
    }

    @Test
    public void testGetStrategyNull() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy(null);
        assertNull(strategy);
    }

    @Test
    public void testGetStrategyInvalid() {
        SearchStrategy strategy = searchStrategyFactory.getStrategy("INVALID");
        assertNull(strategy);
    }
}