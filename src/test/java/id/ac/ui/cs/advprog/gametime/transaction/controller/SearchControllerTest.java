package id.ac.ui.cs.advprog.gametime.transaction.controller;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.concurrent.CompletableFuture;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class SearchControllerTest {

    @Mock
    private SearchService searchService;

    @InjectMocks
    private SearchController searchController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    public void testSearchProduct() throws Exception {
        Product product = new Product();
        product.setName("Test Product");
        List<Product> products = Arrays.asList(product);

        when(searchService.search("name", "Test")).thenReturn(products);

        mockMvc.perform(get("/product/search/name/Test"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'name': 'Test Product'}]"));
    }


    @Test
    public void testFilterByRatingLessThanEqual() throws Exception {
        Product product = new Product();
        product.setRating(4.5);
        List<Product> products = Arrays.asList(product);

        when(searchService.filterByRatingLessThanEqual(5.0)).thenReturn(CompletableFuture.completedFuture(products));

        mockMvc.perform(get("/product/filter/rating/less/5.0"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'rating': 4.5}]"));
    }

    @Test
    public void testFilterByRatingGreaterThanEqual() throws Exception {
        Product product = new Product();
        product.setRating(4.5);
        List<Product> products = Arrays.asList(product);

        when(searchService.filterByRatingGreaterThanEqual(4.0)).thenReturn(CompletableFuture.completedFuture(products));

        mockMvc.perform(get("/product/filter/rating/greater/4.0"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'rating': 4.5}]"));
    }

    @Test
    public void testFilterByPriceLessThanEqual() throws Exception {
        Product product = new Product();
        product.setPrice(100);
        List<Product> products = Arrays.asList(product);

        when(searchService.filterByPriceLessThanEqual(150)).thenReturn(CompletableFuture.completedFuture(products));

        mockMvc.perform(get("/product/filter/price/less/150"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'price': 100}]"));
    }

    @Test
    public void testFilterByPriceGreaterThanEqual() throws Exception {
        Product product = new Product();
        product.setPrice(100);
        List<Product> products = Arrays.asList(product);

        when(searchService.filterByPriceGreaterThanEqual(50)).thenReturn(CompletableFuture.completedFuture(products));

        mockMvc.perform(get("/product/filter/price/greater/50"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'price': 100}]"));
    }





}