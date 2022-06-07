package com.springboot.blog.service;



import com.springboot.blog.Dto.OrderDto;


import java.util.List;

public interface OrderService {
 OrderDto createOrder(OrderDto OrderDto);

 List<OrderDto> getAllOrders();

 OrderDto getOrderById(int id);

 OrderDto updateOrder(OrderDto OrderDto, int id);

 void deleteOrderById(int id);

 List<OrderDto> getOrdersByCustomerID(int customer_id);
}