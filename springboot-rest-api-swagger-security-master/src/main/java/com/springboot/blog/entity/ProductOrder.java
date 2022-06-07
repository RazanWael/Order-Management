package com.springboot.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Razan Yassin
//1182226
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
//@IdClass(ProductOrderPK.class)
@Table(name = "product_order_table")
public class ProductOrder {
    @EmbeddedId
    private ProductOrderPK id;
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("orderId")
    private Order order;
    //Defining the many-to-one relationship between the productorder table and the product table
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;
    //declare needed columns
    @Column(name = "quantity", nullable = false, precision = 10)
    private int quantity;

    @Column(name = "price", nullable = false, precision = 10)
    private double price;

    @Column(name = "vat", nullable = false, precision = 10)
    private double vat;

    public ProductOrder(Order order, Product product, int quantity, double price, double vat) {
        this.id = new ProductOrderPK(order.getOrderId(),product.getProductId());
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.vat = vat;
    }


    //    public ProductOrder(Order order, Product product, int quantity, double price, double vat) {
////        pk = new ProductOrderPK();
////        pk.setOrder(order);
////        pk.setProduct(product);
//        this.order = order;
//        this.product = product;
//        this.quantity = quantity;
//        this.price = price;
//        this.vat = vat;
//    }
//
////    @Transient
////    public Product getProduct() {
////        return this.pk.getProduct();
////    }
//
    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }
//
////    //Need to override them for the embeddedID to work
////    @Override
////    public int hashCode() {
////        final int prime = 31;
////        int result = 1;
////        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
////
////        return result;
////    }
////
////    @Override
////    public boolean equals(Object obj) {
////        if (this == obj) {
////            return true;
////        }
////        if (obj == null) {
////            return false;
////        }
////        if (getClass() != obj.getClass()) {
////            return false;
////        }
////        ProductOrder other = (ProductOrder) obj;
////        if (pk == null) {
////            if (other.pk != null) {
////                return false;
////            }
////        } else if (!pk.equals(other.pk)) {
////            return false;
////        }
////
////        return true;
////    }

}
