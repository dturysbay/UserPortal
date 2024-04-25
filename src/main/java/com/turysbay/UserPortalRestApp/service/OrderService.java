package com.turysbay.UserPortalRestApp.service;

import com.turysbay.UserPortalRestApp.entity.Order;
import com.turysbay.UserPortalRestApp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){return orderRepository.findAll();}

    public Order getOrderById(Long id) { return  orderRepository.getOrderById(id);}

    public void updateOrderById(Order order){
         orderRepository.updateOrder(
                order.getDescription(),
                order.getNumber(),
                order.getAddress(),
                order.getPhoneNumber(),
                order.getId());
    }

    public void deleteOrderById(Long id){
        orderRepository.deleteById(id);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByCreatedBy(userId);
    }
}
