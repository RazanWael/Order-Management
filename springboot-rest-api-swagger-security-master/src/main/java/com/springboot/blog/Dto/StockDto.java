package com.springboot.blog.Dto;



import lombok.Data;

import java.util.Date;

//Razan Yassin
//1182226

@Data
public class StockDto {
    //table column
    private int stockId;
    private int product_id;
    private int quantity;
    private Date updateAt;


}
