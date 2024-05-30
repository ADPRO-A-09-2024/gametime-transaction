package id.ac.ui.cs.advprog.gametime.transaction.controller;

import id.ac.ui.cs.advprog.gametime.transaction.dto.CreateProductDTO;
import id.ac.ui.cs.advprog.gametime.transaction.dto.UpdateProductDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {
    @Mock
    ProductServiceImpl productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductValid() {
        CreateProductDTO createProductDTO = new CreateProductDTO();
        createProductDTO.setName("Boneka doraemon");
        createProductDTO.setSellerId("1");
        createProductDTO.setDescription("Boneka bagus");
        createProductDTO.setCategory("Boneka");
        createProductDTO.setPrice("100000");

        Product product = new Product();
        when(productService.createProduct(createProductDTO)).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(createProductDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).createProduct(createProductDTO);
    }

    @Test
    void testCreateProductInvalidSellerId() {
        CreateProductDTO createProductDTO = new CreateProductDTO();
        createProductDTO.setName("Boneka doraemon");
        createProductDTO.setSellerId("x");
        createProductDTO.setDescription("Boneka bagus");
        createProductDTO.setCategory("Boneka");
        createProductDTO.setPrice("100000");

        assertThrows(ResponseStatusException.class, () ->
                productController.createProduct(createProductDTO));
        verify(productService, times(0)).createProduct(any(CreateProductDTO.class));
    }

    @Test
    void testCreateProductInvalidPrice() {
        CreateProductDTO createProductDTO = new CreateProductDTO();
        createProductDTO.setName("Boneka doraemon");
        createProductDTO.setSellerId("1");
        createProductDTO.setDescription("Boneka bagus");
        createProductDTO.setCategory("Boneka");
        createProductDTO.setPrice("x");

        assertThrows(ResponseStatusException.class, () ->
                productController.createProduct(createProductDTO));
        verify(productService, times(0)).createProduct(any(CreateProductDTO.class));
    }

    @Test
    void testGetProductValid() {
        UUID id = UUID.randomUUID();
        Product product = new Product();

        when(productService.getProductById(id)).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById(id.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).getProductById(id);
    }

    @Test
    void testGetProductInvalidId() {
        assertThrows(ResponseStatusException.class, () ->
                productController.getProductById("x"));
        verify(productService, times(0)).getProductById(any(UUID.class));
    }

    @Test
    void testGetProductsBySellerValid() {
        List<Product> products = new ArrayList<>();

        when(productService.getProductsBySeller(1)).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getProductsBySeller("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
        verify(productService, times(1)).getProductsBySeller(1);
    }

    @Test
    void testGetProductsBySellerInvalidSellerId() {
        assertThrows(ResponseStatusException.class, () ->
                productController.getProductsBySeller("x"));
        verify(productService, times(0)).getProductsBySeller(any(Integer.class));
    }

    @Test
    void testGetAllProductsValid() {
        List<Product> products = new ArrayList<>();

        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testEditProductValid() {
        UpdateProductDTO updateProductDTO = new UpdateProductDTO();
        updateProductDTO.setName("Boneka doraemon");
        updateProductDTO.setDescription("Boneka bagus");
        updateProductDTO.setCategory("Boneka");
        updateProductDTO.setPrice("100000");

        Product product = new Product();
        UUID id = UUID.randomUUID();
        when(productService.updateProduct(id, updateProductDTO)).thenReturn(product);

        ResponseEntity<Product> response = productController.editProduct(id.toString(), updateProductDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).updateProduct(id, updateProductDTO);
    }

    @Test
    void testEditProductInvalidId() {
        UpdateProductDTO updateProductDTO = new UpdateProductDTO();
        updateProductDTO.setName("Boneka doraemon");
        updateProductDTO.setDescription("Boneka bagus");
        updateProductDTO.setCategory("Boneka");
        updateProductDTO.setPrice("100000");

        assertThrows(ResponseStatusException.class, () ->
                productController.editProduct("x", updateProductDTO));
        verify(productService, times(0)).updateProduct(any(UUID.class), any(UpdateProductDTO.class));
    }

    @Test
    void testEditProductInvalidPrice() {
        UpdateProductDTO updateProductDTO = new UpdateProductDTO();
        updateProductDTO.setName("Boneka doraemon");
        updateProductDTO.setDescription("Boneka bagus");
        updateProductDTO.setCategory("Boneka");
        updateProductDTO.setPrice("x");

        String id = UUID.randomUUID().toString();

        assertThrows(ResponseStatusException.class, () ->
                productController.editProduct(id, updateProductDTO));
        verify(productService, times(0)).updateProduct(any(UUID.class), any(UpdateProductDTO.class));
    }
}
