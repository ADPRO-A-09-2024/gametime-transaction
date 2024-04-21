package id.ac.ui.cs.advprog.gametime.transaction.service;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import id.ac.ui.cs.advprog.gametime.transaction.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    ProductServiceImpl productService;
    @Mock
    ProductRepository productRepository;

    List<Product> products;

    @BeforeEach
    void setUp() {
        Product product1 = new Product("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454",
                "Boneka doraemon",
                "Boneka doraemon asli 100% reject pabrik warna kuning",
                "Boneka",
                100000,
                0,
                new ArrayList<>());
        Product product2 = new Product("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3457",
                "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456",
                "Action figure spiderman",
                "Action figure spiderman kw",
                "Action figure",
                200000,
                4,
                new ArrayList<>());

        this.products = new ArrayList<>();
        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreate() {
        Product product1 = this.products.getFirst();
        doReturn(product1).when(this.productRepository).create(product1.getSellerId(),
                product1.getName(), product1.getDescription(),
                product1.getCategory(), 0,
                product1.getPrice(), new ArrayList<>());

        Product productFromCreate = this.productService.create(product1.getSellerId(),
                product1.getName(), product1.getDescription(),
                product1.getCategory(), product1.getPrice());

        assertEquals(product1.getSellerId(), productFromCreate.getSellerId());
        assertEquals(product1.getName(), productFromCreate.getName());
        assertEquals(product1.getDescription(), productFromCreate.getDescription());
        assertEquals(product1.getCategory(), productFromCreate.getCategory());
        assertEquals(product1.getPrice(), productFromCreate.getPrice());
        assertEquals(product1.getRating(), productFromCreate.getRating());
        assertTrue(productFromCreate.getReviewsId().isEmpty());

        verify(this.productRepository, times(1)).create(product1.getSellerId(),
                product1.getName(), product1.getDescription(),
                product1.getCategory(), 0,
                product1.getPrice(), new ArrayList<>());
    }

    @Test
    void testGetById() {
        Product product1 = this.products.getFirst();
        doReturn(product1).when(this.productRepository).getById(product1.getId());

        Product productFromGet = this.productService.getById(product1.getId());

        assertEquals(product1.getSellerId(), productFromGet.getSellerId());
        assertEquals(product1.getName(), productFromGet.getName());
        assertEquals(product1.getDescription(), productFromGet.getDescription());
        assertEquals(product1.getCategory(), productFromGet.getCategory());
        assertEquals(product1.getPrice(), productFromGet.getPrice());
        assertEquals(product1.getRating(), productFromGet.getRating());
        assertTrue(productFromGet.getReviewsId().isEmpty());

        verify(this.productRepository, times(1)).getById(product1.getId());
    }

    @Test
    void testEdit() {
        Product product1 = this.products.get(0);
        Product product2 = new Product(product1.getId(),
                product1.getSellerId(),
                "Boneka mickey mouse",
                "Boneka doraemon asli 100% reject pabrik warna pink",
                "Dolls",
                50000,
                product1.getRating(),
                product1.getReviewsId());

        doReturn(product2).when(this.productRepository).update(product1.getId(),
                product2.getName(),
                product2.getDescription(),
                product2.getCategory(),
                product2.getPrice());

        Product productFromEdit = this.productService.edit(product1.getId(),
                product2.getName(),
                product2.getDescription(),
                product2.getCategory(),
                product2.getPrice());

        assertEquals(product1.getId(), productFromEdit.getId());
        assertEquals(product1.getSellerId(), productFromEdit.getSellerId());
        assertEquals(product2.getName(), productFromEdit.getName());
        assertEquals(product2.getDescription(), productFromEdit.getDescription());
        assertEquals(product2.getCategory(), productFromEdit.getCategory());
        assertEquals(product2.getPrice(), productFromEdit.getPrice());
        assertEquals(product1.getRating(), productFromEdit.getRating());
        assertTrue(productFromEdit.getReviewsId().isEmpty());

        verify(this.productRepository, times(1)).update(product1.getId(),
                product2.getName(),
                product2.getDescription(),
                product2.getCategory(),
                product2.getPrice());
    }

    @Test
    void testGetBySellerId() {
        Product product1 = this.products.getFirst();
        List<Product> product1List = new ArrayList<>();
        product1List.add(product1);
        doReturn(product1List).when(this.productRepository).getBySellerId(product1.getSellerId());

        Product productFromGet = this.productService.getBySellerId(product1.getSellerId()).getFirst();

        assertEquals(product1.getId(), productFromGet.getId());
        assertEquals(product1.getSellerId(), productFromGet.getSellerId());
        assertEquals(product1.getName(), productFromGet.getName());
        assertEquals(product1.getDescription(), productFromGet.getDescription());
        assertEquals(product1.getCategory(), productFromGet.getCategory());
        assertEquals(product1.getPrice(), productFromGet.getPrice());
        assertEquals(product1.getRating(), productFromGet.getRating());
        assertTrue(productFromGet.getReviewsId().isEmpty());

        verify(this.productRepository, times(1)).getBySellerId(product1.getSellerId());
    }

    @Test
    void testGetAllProducts() {
        Product product1 = this.products.get(0);
        Product product2 = this.products.get(1);
        doReturn(this.products).when(this.productRepository).getAllProducts();

        List<Product> productsFromGet = this.productService.getAllProducts();
        Product product1FromGet = productsFromGet.get(0);
        Product product2FromGet = productsFromGet.get(1);

        assertEquals(product1.getSellerId(), product1FromGet.getSellerId());
        assertEquals(product1.getName(), product1FromGet.getName());
        assertEquals(product1.getDescription(), product1FromGet.getDescription());
        assertEquals(product1.getCategory(), product1FromGet.getCategory());
        assertEquals(product1.getPrice(), product1FromGet.getPrice());
        assertEquals(product1.getRating(), product1FromGet.getRating());
        assertTrue(product1FromGet.getReviewsId().isEmpty());

        assertEquals(product2.getSellerId(), product2FromGet.getSellerId());
        assertEquals(product2.getName(), product2FromGet.getName());
        assertEquals(product2.getDescription(), product2FromGet.getDescription());
        assertEquals(product2.getCategory(), product2FromGet.getCategory());
        assertEquals(product2.getPrice(), product2FromGet.getPrice());
        assertEquals(product2.getRating(), product2FromGet.getRating());
        assertTrue(product2FromGet.getReviewsId().isEmpty());
    }
}
