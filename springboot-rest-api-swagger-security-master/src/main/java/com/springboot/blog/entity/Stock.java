package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//Razan Yassin
//1182226
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "stock_table")
public class Stock implements Serializable {
    //declare the primary key
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY //indicates that the persistence provider must assign primary keys for the entity using a database identity column.This means they are auto-incremented
    )
    private int stockId;
    //the needed columns
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "updateAt")
    private Date updateAt = new Date();
    //the many-to-one relationship between stock and product
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;



}
