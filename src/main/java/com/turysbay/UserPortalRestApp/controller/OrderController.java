package com.turysbay.UserPortalRestApp.controller;

import com.turysbay.UserPortalRestApp.entity.Order;
import com.turysbay.UserPortalRestApp.entity.User;
import com.turysbay.UserPortalRestApp.service.OrderService;
import com.turysbay.UserPortalRestApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        Optional<User> createdBy = userService.findByUserId(order.getCreatedBy());

        if (createdBy == null) {
            return ResponseEntity.badRequest().body("User with provided ID does not exist.");
        }

        Order savedOrder = orderService.createOrder(order);
        if(savedOrder != null){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder(){
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){return orderService.getOrderById(id);}

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrdersById(@PathVariable Long id, @RequestBody Order order){
        order.setId(id);
        orderService.updateOrderById(order);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok().body(orders);
    }
}
