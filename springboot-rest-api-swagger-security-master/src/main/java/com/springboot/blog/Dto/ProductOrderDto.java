package com.springboot.blog.Dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Razan Yassin
//1182226
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDto {
    //table column
    private int product;
    private int order;
    private int quantity;
    //Setters ang Getters
    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
