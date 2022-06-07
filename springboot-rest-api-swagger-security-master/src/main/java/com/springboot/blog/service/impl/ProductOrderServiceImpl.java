package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Product;
import com.springboot.blog.entity.ProductOrder;
import com.springboot.blog.entity.ProductOrderPK;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.ProductOrderRepository;
import com.springboot.blog.service.ProductOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Razan Yassin
//1182226
@Service
@Transactional
public class ProductOrderServiceImpl implements ProductOrderService {

        private ProductOrderRepository productOrderRepository;

    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public ProductOrder create(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }

//        @Override
//        public List<ProductOrderDto> getAllProductOrders() {
//            List<ProductOrder> productsOrder = (List<ProductOrder>) productOrderRepository.findAll();
//            return productsOrder.stream().map(productOrder -> mapToDTO(productOrder))
//                    .collect(Collectors.toList());
//        }

        @Override
        public void deleteProductOrderById(int product_id, int order_id) {
            ProductOrderPK entity = new ProductOrderPK(order_id,product_id);
            ProductOrder productOrder = productOrderRepository.findById(entity).orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "id", product_id));
            productOrderRepository.delete(productOrder);
        }

//        // convert Entity into DTO
//        public ProductOrderDto mapToDTO(ProductOrder productOrder) {
//            ProductOrderDto productDto = new ProductOrderDto();
//            productDto.setProduct(productOrder.getProduct().getProductId());
//            productDto.setOrder(productOrder.getOrder().getOrderId());
//            productDto.setQuantity(productOrder.getQuantity());
//            return productDto;
//        }
//
//        // convert DTO to entity
//        public Product mapToEntity(ProductDto productDto) {
//            Product product = new Product();
//            product.setName(productDto.getName());
//            product.setSlug(productDto.getSlug());
//            product.setVat(productDto.getVat());
//            product.setReference(productDto.getReference());
//            product.setPrice(productDto.getPrice());
//
//            return product;
//        }



}





