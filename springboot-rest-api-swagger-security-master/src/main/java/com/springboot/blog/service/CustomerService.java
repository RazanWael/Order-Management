package com.springboot.blog.service;



import com.springboot.blog.Dto.CustomerDto;


import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto CustomerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(int id);

    CustomerDto updateCustomer(CustomerDto CustomerDto, int id);

    void deleteCustomerById(int id);
}
