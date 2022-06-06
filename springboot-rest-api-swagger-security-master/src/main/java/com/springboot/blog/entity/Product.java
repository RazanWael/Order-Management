package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "product_table")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "PorderProducts")
public class Product {
        //declare the primary key
        @Id
        @GeneratedValue(
                strategy = GenerationType.IDENTITY //indicates that the persistence provider must assign primary keys for the entity using a database identity column.This means they are auto-incremented
        )
        private Integer productId;
        public Product(Integer productId) {
                this.productId = productId;
        }
        @Column(name = "slug", nullable = false, columnDefinition = "TINYTEXT")
        private String slug;
        @Column(name = "name", nullable = false, columnDefinition = "TINYTEXT")
        private String name;
        @Column(name = "reference", nullable = false, columnDefinition = "TINYTEXT")
        private String reference;
        @Column(name = "price", nullable = false, precision = 10)
        private double price;
        @Column(name = "vat", nullable = false, precision = 10)
        private double vat;
        @Column
        private boolean stockable;

//        @OneToMany(mappedBy = "product_id")
//        @Valid
//        private List<Stock> productStocks = new ArrayList<>();
//
//        @Transient
//        public int getTotalProductQuantityInAllStocks(){
//                int total = 0;
//                List<Stock> productStocks = getProductStocks();
//                for (Stock s : productStocks){
//                        total += s.getQuantity();
//                }
//                return total;
//        }
//
//        public List<Stock> getProductStocks(){ return productStocks;}
//
//        @OneToMany(mappedBy = "product")
//        @Valid
//        private List<ProductOrder> PorderProducts = new ArrayList<>();
//
//        public List<ProductOrder> getOrderProducts(){ return PorderProducts;}
}
