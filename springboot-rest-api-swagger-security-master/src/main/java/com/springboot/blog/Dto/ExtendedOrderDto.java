package com.springboot.blog.Dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ExtendedOrderDto {
    //table column
    private int orderId;
    private int customerId;
    private Date orderedAt;
    private List<ProductOrderDto> products_orders=new ArrayList<ProductOrderDto>();
}
