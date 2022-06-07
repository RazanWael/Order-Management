package com.springboot.blog.Dto;

import lombok.Data;

//Razan Yassin
//1182226
@Data
public class ProductDto {
    //table column
    private int productId;
    private String slug;
    private String name;
    private String reference;
    private double price;
    private double vat;
    private boolean stockable;

}
