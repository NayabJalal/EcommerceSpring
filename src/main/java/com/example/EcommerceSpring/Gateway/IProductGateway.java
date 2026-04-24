package com.example.EcommerceSpring.Gateway;

import com.example.EcommerceSpring.dto.ProductDTO;
import java.io.IOException;
import java.util.List;

public interface IProductGateway {
    List<ProductDTO> fetchAllProducts() throws IOException;
}
