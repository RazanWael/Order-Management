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
@Table(name = "customer_table")
public class Customer {
    //declare the primary key
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY //indicates that the persistence provider must assign primary keys for the entity using a database identity column.This means they are auto-incremented
    )
    private int customerId;
    //declare the needed columns
    @Column(name = "firstName", nullable = false, columnDefinition = "TINYTEXT")
    private String firstName;
    @Column(name = "lastName", columnDefinition = "TINYTEXT")
    private String lastName;
    @Column(name = "bornAt", columnDefinition = "Date")
    private Date bornAt;

    public Customer(int customerId, String firstName, String lastName, Date bornAt) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornAt = bornAt;
    }

    public Customer() {
    }

    public Customer(int customerId) {
        this.customerId = customerId;
    }
    //These were commented out, to make unidirectional relationship instead of adding extra complexity by the
//bidirectional relationships.
//    @OneToMany(mappedBy = "customer_id")
//    @Valid
//    private List<Order> customerOrders = new ArrayList<>();
//
//    public List<Order> getCustomerOrders(){ return customerOrders;}
//
//    @Transient
//    public int getNumberOfOrders(){return this.customerOrders.size();}


}
