package com.springboot.blog.Dto;

import com.springboot.blog.entity.Product;

import lombok.Data;

import java.util.Date;


@Data
public class StockDto {
    private int stockId;
    private int product_id;
    private int quantity;
    private Date updateAt;


}
