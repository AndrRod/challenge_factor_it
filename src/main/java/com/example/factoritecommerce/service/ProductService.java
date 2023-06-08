package com.example.factoritecommerce.service;

import com.example.factoritecommerce.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductDto createProduct(ProductDto productInput);
    ProductDto updateProductById(Long id, ProductDto productInput);
    Map<String, String> deleteProductById(Long id);
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts(Integer page);
}
