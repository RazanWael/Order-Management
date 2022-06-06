package com.springboot.blog.controller;


import com.springboot.blog.Dto.OrderDto;
import com.springboot.blog.Dto.ProductDto;
import com.springboot.blog.entity.Product;
import com.springboot.blog.entity.ProductOrder;
import com.springboot.blog.service.ProductOrderService;
import com.springboot.blog.service.ProductService;
import com.springboot.blog.Dto.ProductOrderDto;
import com.springboot.blog.entity.Customer;
import com.springboot.blog.entity.Order;
import com.springboot.blog.exception.BadRequestException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.ProductRepository;
import com.springboot.blog.service.OrderService;
import com.springboot.blog.service.impl.ProductServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import sun.java2d.pipe.SpanShapeRenderer;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderResource {
        ProductService productService;
        ProductOrderService productOrderService;
        private final Logger log = LoggerFactory.getLogger(OrderResource.class);

        //    @Autowired //@Autowired annotation is used for dependency injection.In spring boot application, all loaded beans are eligible for auto wiring to another bean. The annotation @Autowired in spring boot is used to auto-wire a bean into another bean.
        private OrderService orderService; //the use of interface rather than class is important for loose coupling

    public OrderResource(OrderService orderService,ProductService productService, ProductOrderService productOrderService) {
        this.orderService = orderService;
        this.productService = productService;
        this.productOrderService = productOrderService;
    }

        @GetMapping
        public ResponseEntity<List<OrderDto>> getAllOrders() {
            return ResponseEntity.ok().body(orderService.getAllOrders()); //ResponseEntity represents an HTTP response, including headers, body, and status.
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderDto> getOrderById(
                @PathVariable(name = "id") int id) {
            return ResponseEntity.ok(orderService.getOrderById(id));
        }

        @GetMapping("/customer/{id}")
        public ResponseEntity<List<OrderDto>> getOrdersByCustomerID( @PathVariable(name = "id") int id ) {
            return ResponseEntity.ok().body(orderService.getOrdersByCustomerID(id)); //ResponseEntity represents an HTTP response, including headers, body, and status.
        }

        @PostMapping
        public ResponseEntity<OrderDto> createOrder
        (@Valid @RequestBody OrderDto orderDto){
            return new ResponseEntity(orderService.createOrder(orderDto), HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<OrderDto> updateOrder
                (@Valid @RequestBody OrderDto orderDto
                        , @PathVariable(name = "id") int id) {
            return new ResponseEntity<>(orderService.updateOrder(orderDto, id), HttpStatus.OK);
        }

        //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") int id) {
            orderService.deleteOrderById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
            return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
        }




}
