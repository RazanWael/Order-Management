package com.springboot.blog.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data


@Entity
@Table(name = "order_table")
public class Order {
    //declare the primary key
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY //indicates that the persistence provider must assign primary keys for the entity using a database identity column.This means they are auto-incremented
    )
    private Integer orderId;
    //declare needed columns
    @Column(name = "orderedAt")
    private Date orderedAt;
    //Defining the many-to-one relationship between the customer and the order
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    //Defining the one-to-many relationship between the order and the product_order
    @OneToMany(mappedBy = "order")
    @Valid
    private List<ProductOrder> orderProducts = new ArrayList<>();

    public Order(int orderId, Date orderedAt, Customer customer, List<ProductOrder> orderProducts) {
        this.orderId = orderId;
        this.orderedAt = orderedAt;
        this.customer = customer;
        this.orderProducts = orderProducts;
    }

    public Order() {
    }

    public Order(int order_id) {
        this.orderId = order_id;
    }

    @Transient
    public Double getTotalOrderPrice(){
        double total = 0;
        List<ProductOrder> orderProducts = getOrderProducts();
        for (ProductOrder o: orderProducts){
            total += o.getTotalPrice();
        }
        return total;
    }

    public List<ProductOrder> getOrderProducts(){ return orderProducts;}



}