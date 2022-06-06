package com.springboot.blog.service.impl;

import com.springboot.blog.entity.ProductOrder;
import com.springboot.blog.repository.ProductOrderRepository;
import com.springboot.blog.service.ProductOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}





