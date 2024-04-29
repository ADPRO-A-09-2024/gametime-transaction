package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.dto.CreateProductDTO;
import id.ac.ui.cs.advprog.gametime.transaction.dto.UpdateProductDTO;
import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import id.ac.ui.cs.advprog.gametime.transaction.repository.UserRepository;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.SearchStrategy;
import id.ac.ui.cs.advprog.gametime.transaction.service.strategy.SearchStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    UserRepository userRepository;

    @Mock
    private SearchStrategyFactory searchStrategyFactory;

    @Mock
    private SearchStrategy searchStrategy;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        CreateProductDTO createProductDTO = new CreateProductDTO();
        createProductDTO.setName("Boneka doraemon");
        createProductDTO.setSellerId("1");
        createProductDTO.setDescription("Boneka bagus");
        createProductDTO.setCategory("Boneka");
        createProductDTO.setPrice("100000");

        User seller = new User();
        Product product = new Product();
        when(userRepository.findById(1)).thenReturn(Optional.of(seller));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product productFromCreate = productService.createProduct(createProductDTO);
        assertEquals(product, productFromCreate);
        verify(userRepository, times(1)).findById(1);
        verify(productRepository, times(1)).save(any(Product.class));

    }

    @Test
    void testCreateProductUserNotFound() {
        CreateProductDTO createProductDTO = new CreateProductDTO();
        createProductDTO.setName("Boneka doraemon");
        createProductDTO.setSellerId("1");
        createProductDTO.setDescription("Boneka bagus");
        createProductDTO.setCategory("Boneka");
        createProductDTO.setPrice("100000");

        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                productService.createProduct(createProductDTO));
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        assertEquals(product, productService.getProductById(id));
    }

    @Test
    void testGetProductsBySeller() {
        User seller1 = new User();
        seller1.setId(1);
        User seller2 = new User();
        seller2.setId(2);

        Product product1 = new Product();
        product1.setSeller(seller1);
        Product product2 = new Product();
        product2.setSeller(seller2);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> productsFromGet = productService.getProductsBySeller(1);
        assertEquals(product1, productsFromGet.getFirst());
        assertEquals(1,  productsFromGet.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductsBySellerEmpty() {
        User seller1 = new User();
        seller1.setId(1);
        User seller2 = new User();
        seller2.setId(2);

        Product product1 = new Product();
        product1.setSeller(seller1);
        Product product2 = new Product();
        product2.setSeller(seller2);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> productsFromGet = productService.getProductsBySeller(3);
        assertTrue(productsFromGet.isEmpty());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> productsFromGet = productService.getAllProducts();
        assertEquals(product1, productsFromGet.get(0));
        assertEquals(product2, productsFromGet.get(1));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();

        UUID id = UUID.randomUUID();
        product.setId(id);

        UpdateProductDTO updateProductDTO = new UpdateProductDTO();
        updateProductDTO.setName("Boneka mickey mouse");
        updateProductDTO.setDescription("Boneka sangat bagus");
        updateProductDTO.setCategory("Dolls");
        updateProductDTO.setPrice("50000");

        Product updatedProduct = new Product();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        Product productFromUpdate = productService.updateProduct(id, updateProductDTO);
        assertEquals(updatedProduct, productFromUpdate);
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdateProductNotFound() {
        UpdateProductDTO updateProductDTO = new UpdateProductDTO();

        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                productService.updateProduct(UUID.randomUUID(), updateProductDTO));
        verify(productRepository, times(1)).findById(any(UUID.class));
        verify(productRepository, times(0)).save(any(Product.class));
    }

    @Test
    void testUpdateProductPrice0() {
        Product product = new Product();

        UUID id = UUID.randomUUID();
        product.setId(id);

        UpdateProductDTO updateProductDTO = new UpdateProductDTO();
        updateProductDTO.setName("Boneka mickey mouse");
        updateProductDTO.setDescription("Boneka sangat bagus");
        updateProductDTO.setCategory("Dolls");
        updateProductDTO.setPrice("0");

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        assertThrows(IllegalArgumentException.class, () ->
                productService.updateProduct(id, updateProductDTO))   ;
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(0)).save(any(Product.class));
    }

    @Test
    void testUpdateProductPriceNegative() {
        Product product = new Product();

        UUID id = UUID.randomUUID();
        product.setId(id);

        UpdateProductDTO updateProductDTO = new UpdateProductDTO();
        updateProductDTO.setName("Boneka mickey mouse");
        updateProductDTO.setDescription("Boneka sangat bagus");
        updateProductDTO.setCategory("Dolls");
        updateProductDTO.setPrice("-10");

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        assertThrows(IllegalArgumentException.class, () ->
                productService.updateProduct(id, updateProductDTO))   ;
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(0)).save(any(Product.class));
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