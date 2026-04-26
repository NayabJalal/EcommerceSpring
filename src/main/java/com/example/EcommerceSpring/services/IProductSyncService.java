package com.example.EcommerceSpring.services;

import com.example.EcommerceSpring.entity.Products;

import java.io.IOException;
import java.util.List;

public interface IProductSyncService {
    List<Products> syncAllProductsFromFakeStore() throws IOException;
    Products syncSingleProduct(Long fakeStoreProductId) throws IOException;
}
