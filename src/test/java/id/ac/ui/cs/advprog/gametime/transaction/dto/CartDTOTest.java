package id.ac.ui.cs.advprog.gametime.transaction.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CartDTOTest {

    @Test
    public void testCartDTO_HappyPath() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(1);

        List<CartItemDTO> items = new ArrayList<>();
        CartItemDTO item = new CartItemDTO();
        item.setId(UUID.randomUUID());
        item.setProductId(UUID.randomUUID());
        items.add(item);

        cartDTO.setItems(items);

        assertEquals(1, cartDTO.getUserId());
        assertEquals(items, cartDTO.getItems());
    }

    @Test
    public void testCartDTO_UnhappyPath_NullItems() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(1);
        cartDTO.setItems(null);

        assertNull(cartDTO.getItems());
    }

    @Test
    public void testCartDTO_UnhappyPath_NullUserId() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(null);

        assertNull(cartDTO.getUserId());
    }
}
