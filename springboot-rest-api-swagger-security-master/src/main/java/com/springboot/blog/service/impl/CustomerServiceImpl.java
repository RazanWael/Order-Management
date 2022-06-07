package com.springboot.blog.service.impl;

import com.springboot.blog.Dto.CustomerDto;
import com.springboot.blog.entity.Customer;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CustomerRepository;
import com.springboot.blog.service.CustomerService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Razan Yassin
//1182226
//It is used to mark the class as a service provider.
@Service
public class CustomerServiceImpl implements CustomerService {

        private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer=mapToEntity(customerDto);
        Customer newCustomer = customerRepository.save(customer);
        CustomerDto customerResponse =mapToDTO(newCustomer);
        return customerResponse;
    }


    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> cutomers = customerRepository.findAll();
        return cutomers.stream().map(customer -> mapToDTO(customer)).collect(Collectors.toList());
    }
    @Override
    public CustomerDto getCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer","id",id));
        return mapToDTO(customer);
    }


    @Override
    public CustomerDto updateCustomer(CustomerDto CustomerDto, int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customer.setFirstName(CustomerDto.getFirstName());
        customer.setLastName(CustomerDto.getLastName());
        customer.setBornAt(CustomerDto.getBornAt());
        Customer updatedCustomer = customerRepository.save(customer);

        return mapToDTO(updatedCustomer);
    }


    @Override
    public void deleteCustomerById(int id) {
     Customer customer=customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer","id",id));
     customerRepository.delete(customer);
    }

    // convert Entity into DTO
    private CustomerDto mapToDTO(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCutomerId(customer.getCustomerId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setBornAt(customer.getBornAt());

        return customerDto;
    }

    // convert DTO to entity
    private Customer mapToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBornAt(customerDto.getBornAt());

        return customer;
    }


}
