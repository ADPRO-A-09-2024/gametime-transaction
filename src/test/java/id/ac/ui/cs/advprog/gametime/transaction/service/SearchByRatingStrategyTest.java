package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.SearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SearchByRatingStrategyTest {

    @Mock
    private SearchRepository searchRepository;

    @InjectMocks
    private SearchByRatingStrategy searchByRatingStrategy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearch() {
        Product product = new Product();
        product.setName("Test Product");
        List<Product> products = Arrays.asList(product);

        when(searchRepository.findByRating(4.5)).thenReturn(products);

        List<Product> result = searchByRatingStrategy.search("4.5");

        assertEquals(products, result);
    }
}