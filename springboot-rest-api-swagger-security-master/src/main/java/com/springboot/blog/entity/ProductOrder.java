package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@IdClass(ProductOrderPK.class)
@Table(name = "product_order_table")
public class ProductOrder {
//    @EmbeddedId
//    @JsonIgnore
    @Id
    private Product product;
    @Id
    private Order order;

    @Column(name = "quantity", nullable = false, precision = 10)
    private int quantity;

    @Column(name = "price", nullable = false, precision = 10)
    private double price;

    @Column(name = "vat", nullable = false, precision = 10)
    private double vat;

    public ProductOrder(Order order, Product product, int quantity, double price, double vat) {
//        pk = new ProductOrderPK();
//        pk.setOrder(order);
//        pk.setProduct(product);
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.vat = vat;
    }

//    @Transient
//    public Product getProduct() {
//        return this.pk.getProduct();
//    }

    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

//    //Need to override them for the embeddedID to work
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
//
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        ProductOrder other = (ProductOrder) obj;
//        if (pk == null) {
//            if (other.pk != null) {
//                return false;
//            }
//        } else if (!pk.equals(other.pk)) {
//            return false;
//        }
//
//        return true;
//    }

}
