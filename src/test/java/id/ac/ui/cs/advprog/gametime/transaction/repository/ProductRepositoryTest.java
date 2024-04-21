package id.ac.ui.cs.advprog.gametime.transaction.repository;

import id.ac.ui.cs.advprog.gametime.transaction.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {
    ProductRepository productRepository;
    List<Product> products;

    @BeforeEach
    void setUp() {
        this.productRepository = new ProductRepository();
        this.products = new ArrayList<>();

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

        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreate() {
        Product product = this.products.getFirst();
        Product productFromCreate = this.productRepository.create(product.getSellerId(), product.getName(),
                product.getDescription(), product.getCategory(), product.getRating(),
                product.getPrice(), product.getReviewsId());

        assertEquals(product.getSellerId(), productFromCreate.getSellerId());
        assertEquals(product.getName(), productFromCreate.getName());
        assertEquals(product.getDescription(), productFromCreate.getDescription());
        assertEquals(product.getCategory(), productFromCreate.getCategory());
        assertEquals(product.getPrice(), productFromCreate.getPrice());
        assertEquals(product.getRating(), productFromCreate.getRating());
        assertTrue(productFromCreate.getReviewsId().isEmpty());
    }

    @Test
    void testGetByIdValid() {
        Product product1 = this.products.get(0);
        Product product2 = this.products.get(1);
        this.productRepository.create(product1.getSellerId(), product1.getName(),
                product1.getDescription(), product1.getCategory(), product1.getRating(),
                product1.getPrice(), product1.getReviewsId());
        Product productFromCreate = this.productRepository.create(product2.getSellerId(), product2.getName(),
                product2.getDescription(), product2.getCategory(), product2.getRating(),
                product2.getPrice(), product2.getReviewsId());

        Product productFromGet = this.productRepository.getById(productFromCreate.getId());

        assertEquals(product2.getSellerId(), productFromGet.getSellerId());
        assertEquals(product2.getName(), productFromGet.getName());
        assertEquals(product2.getDescription(), productFromGet.getDescription());
        assertEquals(product2.getCategory(), productFromGet.getCategory());
        assertEquals(product2.getPrice(), productFromGet.getPrice());
        assertEquals(product2.getRating(), productFromGet.getRating());
        assertTrue(productFromGet.getReviewsId().isEmpty());
    }

    @Test
    void testGetByIdNotExist() {
        Product product1 = this.products.get(0);
        Product product2 = this.products.get(1);
        this.productRepository.create(product1.getSellerId(), product1.getName(),
                product1.getDescription(), product1.getCategory(), product1.getRating(),
                product1.getPrice(), product1.getReviewsId());
        this.productRepository.create(product2.getSellerId(), product2.getName(),
                product2.getDescription(), product2.getCategory(), product2.getRating(),
                product2.getPrice(), product2.getReviewsId());

        Product productFromGet = this.productRepository.getById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3460");
        assertNull(productFromGet);
    }

    @Test
    void testGetByIdEmpty() {
        Product productFromGet = this.productRepository.getById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455");
        assertNull(productFromGet);
    }

    @Test
    void testGetBySellerIdValid() {
        Product product1 = this.products.get(0);
        Product product2 = this.products.get(1);
        this.productRepository.create(product1.getSellerId(), product1.getName(),
                product1.getDescription(), product1.getCategory(), product1.getRating(),
                product1.getPrice(), product1.getReviewsId());
        this.productRepository.create(product2.getSellerId(), product2.getName(),
                product2.getDescription(), product2.getCategory(), product2.getRating(),
                product2.getPrice(), product2.getReviewsId());

        Product productFromGet = this.productRepository.getBySellerId(product2.getSellerId()).getFirst();

        assertEquals(product2.getSellerId(), productFromGet.getSellerId());
        assertEquals(product2.getName(), productFromGet.getName());
        assertEquals(product2.getDescription(), productFromGet.getDescription());
        assertEquals(product2.getCategory(), productFromGet.getCategory());
        assertEquals(product2.getPrice(), productFromGet.getPrice());
        assertEquals(product2.getRating(), productFromGet.getRating());
        assertTrue(productFromGet.getReviewsId().isEmpty());
    }

    @Test
    void testGetBySellerIdNotExist() {
        Product product1 = this.products.get(0);
        Product product2 = this.products.get(1);
        this.productRepository.create(product1.getSellerId(), product1.getName(),
                product1.getDescription(), product1.getCategory(), product1.getRating(),
                product1.getPrice(), product1.getReviewsId());
        this.productRepository.create(product2.getSellerId(), product2.getName(),
                product2.getDescription(), product2.getCategory(), product2.getRating(),
                product2.getPrice(), product2.getReviewsId());

        List<Product> productsFromGet = this.productRepository.getBySellerId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3460");
        assertTrue(productsFromGet.isEmpty());
    }

    @Test
    void testGetBySellerIdEmpty() {
        List<Product> productsFromGet = this.productRepository.getBySellerId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455");
        assertTrue(productsFromGet.isEmpty());
    }

    @Test
    void testGetAllProductsValid() {
        Product product1 = this.products.get(0);
        Product product2 = this.products.get(1);
        this.productRepository.create(product1.getSellerId(), product1.getName(),
                product1.getDescription(), product1.getCategory(), product1.getRating(),
                product1.getPrice(), product1.getReviewsId());
        this.productRepository.create(product2.getSellerId(), product2.getName(),
                product2.getDescription(), product2.getCategory(), product2.getRating(),
                product2.getPrice(), product2.getReviewsId());

        List<Product> productsFromGet = this.productRepository.getAllProducts();
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

    @Test
    void testGetAllProductsEmpty() {
        List<Product> productsFromGet = this.productRepository.getAllProducts();
        assertTrue(productsFromGet.isEmpty());
    }

    @Test
    void testUpdate() {
        Product product1 = this.products.getFirst();
        Product productFromCreate = this.productRepository.create(product1.getSellerId(), product1.getName(),
                product1.getDescription(), product1.getCategory(), product1.getRating(),
                product1.getPrice(), product1.getReviewsId());

        Product productFromUpdate = this.productRepository.update(productFromCreate.getId(),
                "Boneka mickey mouse",
                "Boneka doraemon asli 100% reject pabrik warna pink",
                "Dolls",
                50000);

        assertEquals(productFromCreate.getId(), productFromUpdate.getId());
        assertEquals(productFromCreate.getSellerId(), productFromUpdate.getSellerId());
        assertEquals("Boneka mickey mouse", productFromUpdate.getName());
        assertEquals("Boneka doraemon asli 100% reject pabrik warna pink", productFromUpdate.getDescription());
        assertEquals("Dolls", productFromUpdate.getCategory());
        assertEquals(50000, productFromUpdate.getPrice());
        assertEquals(productFromCreate.getRating(), productFromUpdate.getRating());
        assertTrue(productFromUpdate.getReviewsId().isEmpty());
    }
}
