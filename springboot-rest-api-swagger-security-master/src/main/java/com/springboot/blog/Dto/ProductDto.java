package com.springboot.blog.Dto;

import lombok.Data;

@Data
public class ProductDto {
    private int productId;
    private String slug;
    private String name;
    private String reference;
    private double price;
    private double vat;
    private boolean stockable;

}
