package com.springboot.blog.service;

import com.springboot.blog.Dto.ProductDto;
import com.springboot.blog.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto ProductDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(int id);

    ProductDto updateProduct(ProductDto ProductDto, int id);

    void deleteProductById(int id);

    Product mapToEntity(ProductDto product);
}
