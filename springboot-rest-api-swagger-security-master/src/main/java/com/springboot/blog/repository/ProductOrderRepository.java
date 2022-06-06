package com.springboot.blog.repository;

import com.springboot.blog.entity.ProductOrder;
import com.springboot.blog.entity.ProductOrderPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderPK> {



}
