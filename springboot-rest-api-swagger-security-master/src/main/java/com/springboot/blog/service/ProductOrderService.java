package com.springboot.blog.service;

import com.springboot.blog.entity.ProductOrder;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface ProductOrderService {
    ProductOrder create(ProductOrder orderProduct);
}







