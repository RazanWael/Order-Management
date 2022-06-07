package com.springboot.blog.service;

import com.springboot.blog.Dto.ProductOrderDto;
import com.springboot.blog.entity.ProductOrder;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface ProductOrderService {
    ProductOrder create(ProductOrder orderProduct);

    void deleteProductOrderById(int product_id, int order_id);
}







