package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Product;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.Dto.ProductDto;
import com.springboot.blog.repository.ProductRepository;
import com.springboot.blog.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ProductServiceimpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceimpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
        public ProductDto createProduct(ProductDto productDto) {

            // convert DTO to entity
            Product product = mapToEntity(productDto);
            Product newProduct = productRepository.save(product);
            // convert entity to DTO
        ProductDto productResponse = mapToDTO(newProduct);
            return productResponse;
        }

        @Override
        public List<ProductDto> getAllProducts() {
            List<Product> products = productRepository.findAll();
            return products.stream().map(product -> mapToDTO(product))
                    .collect(Collectors.toList());
        }

        @Override
        public ProductDto getProductById(int id) {
            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
            return mapToDTO(product);
        }

        @Override
        public ProductDto updateProduct(ProductDto productDto, int id) {
            // get category by id from the database
            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setReference(productDto.getReference());
            product.setSlug(productDto.getSlug());
            product.setVat(productDto.getVat());


            Product updatedProduct = productRepository.save(product);
            return mapToDTO(updatedProduct);
        }

        @Override
        public void deleteProductById(int id) {

            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
            productRepository.delete(product);
        }

        // convert Entity into DTO
        public ProductDto mapToDTO(Product product) {
            ProductDto productDto = new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setName(product.getName());
            productDto.setPrice(product.getPrice());
            productDto.setReference(product.getReference());
            productDto.setVat(product.getVat());
            productDto.setSlug(product.getSlug());
            return productDto;
        }

        // convert DTO to entity
        public Product mapToEntity(ProductDto productDto) {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setSlug(productDto.getSlug());
            product.setVat(productDto.getVat());
            product.setReference(productDto.getReference());
            product.setPrice(productDto.getPrice());

            return product;
        }
}
