package id.ac.ui.cs.advprog.gametime.transaction.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CartItemDTOTest {

    @Test
    public void testCartItemDTO_HappyPath() {
        CartItemDTO cartItemDTO = new CartItemDTO();
        UUID itemId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        cartItemDTO.setId(itemId);
        cartItemDTO.setProductId(productId);

        assertEquals(itemId, cartItemDTO.getId());
        assertEquals(productId, cartItemDTO.getProductId());
    }

    @Test
    public void testCartItemDTO_UnhappyPath_NullId() {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(null);

        assertNull(cartItemDTO.getId());
    }

    @Test
    public void testCartItemDTO_UnhappyPath_NullProductId() {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setProductId(null);

        assertNull(cartItemDTO.getProductId());
    }
}
