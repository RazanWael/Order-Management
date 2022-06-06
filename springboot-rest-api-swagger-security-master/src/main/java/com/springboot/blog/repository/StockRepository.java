package com.springboot.blog.repository;


import com.springboot.blog.entity.Stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
