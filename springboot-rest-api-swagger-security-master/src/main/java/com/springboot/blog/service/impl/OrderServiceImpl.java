package com.springboot.blog.service.impl;

import com.springboot.blog.Dto.OrderDto;
import com.springboot.blog.Dto.ProductDto;
import com.springboot.blog.Dto.ProductOrderDto;
import com.springboot.blog.entity.Customer;
import com.springboot.blog.entity.Order;
import com.springboot.blog.entity.Product;
import com.springboot.blog.entity.ProductOrder;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.OrderRepository;
import com.springboot.blog.service.OrderService;

import com.springboot.blog.service.ProductOrderService;
import com.springboot.blog.service.ProductService;
//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import com.springboot.blog.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductService productService;

    private StockService stockService;
    private ProductOrderService productOrderService;
    ArrayList<ProductOrderDto> allProductOrders = new ArrayList<ProductOrderDto>();

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService,StockService stockService, ProductOrderService productOrderService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.stockService = stockService;
        this.productOrderService = productOrderService;
    }


    @Override
    public OrderDto createOrder(OrderDto orderDto) {
//        Order order = mapToEntity(OrderDto);
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setCustomer(new Customer(orderDto.getCustomerId()));
        order.setOrderedAt(new Date());
        Order newOrder = orderRepository.save(order);
        List<ProductOrder> toSave = new ArrayList<ProductOrder>();
        for(int i=0;i<orderDto.getProducts_orders().size();i++){
            int product_id = orderDto.getProducts_orders().get(i).getProduct();
            ProductDto temp = productService.getProductById(product_id);
            if (temp!=null) {
                double price = temp.getPrice();
                double vat = temp.getVat();
                String slug = temp.getSlug();
                String name = temp.getName();
                String reference = temp.getReference();
                Boolean stockable = temp.isStockable();
                int quantity = orderDto.getProducts_orders().get(i).getQuantity();
                if (quantity < stockService.quantityOfProductID(product_id)) {
                    toSave.add(new ProductOrder(newOrder, new Product(product_id, slug, name, reference, price, vat, stockable), quantity, price, vat));
                    productOrderService.create(new ProductOrder(newOrder, new Product(product_id, slug, name, reference, price, vat, stockable), quantity, price, vat));
                }
            }
        }
        newOrder.setOrderProducts(toSave);
        return mapToDTO(newOrder);


    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders =orderRepository.findAll();
        for(int i=0; i<orders.size();i++){
            for (int j=0;j<allProductOrders.size();j++){
               if(allProductOrders.get(j).getOrder()==orders.get(i).getOrderId()){
                   int product_id = allProductOrders.get(j).getProduct();
                   int order_id = allProductOrders.get(j).getOrder();
                   int quantity = allProductOrders.get(j).getQuantity();
                   orders.get(i).getOrderProducts().add(new ProductOrder(new Order(order_id),new Product(product_id),quantity,0,0));
               }
            }

        }
        return orders.stream().map(order -> mapToDTO(order))
                .collect(Collectors.toList());
    }


    @Override
    public OrderDto getOrderById(int id) {
        Order order =orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        allProductOrders = removeDuplicates(allProductOrders);
            for (int j=0;j<allProductOrders.size();j++){
                if(allProductOrders.get(j).getOrder()==order.getOrderId()){
                    int product_id = allProductOrders.get(j).getProduct();
                    int order_id = allProductOrders.get(j).getOrder();
                    int quantity = allProductOrders.get(j).getQuantity();
                    order.getOrderProducts().add(new ProductOrder(new Order(order_id),new Product(product_id),quantity,0,0));
                }
            }
        return mapToDTO(order);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, int id) {
        deleteOrderById(id);
        return createOrder(orderDto);
    }

    @Override
    public void deleteOrderById(int id) {
        Order order = orderRepository.findById( id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        for (int i=0;i<allProductOrders.size();i++){
            if (allProductOrders.get(i).getOrder()==id){
                allProductOrders.remove(i);
            }
        }
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getOrdersByCustomerID(int customer_id) {
        List<Order> orders =orderRepository.findAll();
        List<Order> output = new ArrayList<Order>();
        allProductOrders = removeDuplicates(allProductOrders);
        for(int i=0; i<orders.size();i++){
            if (orders.get(i).getCustomer().getCustomerId() != customer_id){
                continue;
            }
            for (int j=0;j<allProductOrders.size();j++){
                if(allProductOrders.get(j).getOrder()==orders.get(i).getOrderId()){
                    int product_id = allProductOrders.get(j).getProduct();
                    int order_id = allProductOrders.get(j).getOrder();
                    int quantity = allProductOrders.get(j).getQuantity();
                    orders.get(i).getOrderProducts().add(new ProductOrder(new Order(order_id),new Product(product_id),quantity,0,0));

                }
            }
            output.add(orders.get(i));

        }
        return output.stream().map(order -> mapToDTO(order))
                .collect(Collectors.toList());
    }


    private OrderDto mapToDTO(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setCustomerId(order.getCustomer().getCustomerId());
        orderDto.setOrderedAt(new Date());
        List<ProductOrderDto> toSave = new ArrayList<ProductOrderDto>();
        allProductOrders = removeDuplicates(allProductOrders);
        for (int i=0;i<order.getOrderProducts().size();i++){
            int product_id = order.getOrderProducts().get(i).getProduct().getProductId();
            int order_id = order.getOrderProducts().get(i).getOrder().getOrderId();
            int quantity = order.getOrderProducts().get(i).getQuantity();
            toSave.add(new ProductOrderDto(product_id,order_id,quantity));
            allProductOrders.add(new ProductOrderDto(product_id,order_id,quantity));
        }
        orderDto.setProducts_orders(toSave);
        return orderDto;
    }
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {
        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();
        // Traverse through the first list
        for (T element : list) {
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        // return the new list
        return newList;
    }
    private Order mapToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setCustomer(new Customer(orderDto.getCustomerId()));
        order.setOrderedAt(new Date());
        List<ProductOrder> toSave = new ArrayList<ProductOrder>();
        for(int i=0;i<orderDto.getProducts_orders().size();i++){
            int product_id = orderDto.getProducts_orders().get(i).getProduct();
            ProductDto temp = productService.getProductById(product_id);
            if (temp!=null) {
                double price = temp.getPrice();
                double vat = temp.getVat();
                String slug = temp.getSlug();
                String name = temp.getName();
                String reference = temp.getReference();
                Boolean stockable = temp.isStockable();
                int quantity = orderDto.getProducts_orders().get(i).getQuantity();
                if (quantity < stockService.quantityOfProductID(product_id)) {
                    toSave.add(new ProductOrder(order, new Product(product_id, slug, name, reference, price, vat, stockable), quantity, price, vat));
                 }
                }
        }
        order.setOrderProducts(toSave);
        return order;
    }

}
