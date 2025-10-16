package com.example.EcommerceSpring.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class FakeStoreCartResponse {
    private Long id;
    private Long userId;
    private String date;
    private List<CartItem> products;

    @Getter
    @Setter
    public static class CartItem {
        private Long productId;
        private Integer quantity;
    }
}
