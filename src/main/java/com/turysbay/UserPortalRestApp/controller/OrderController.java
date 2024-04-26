package com.turysbay.UserPortalRestApp.controller;

import com.turysbay.UserPortalRestApp.entity.Order;
import com.turysbay.UserPortalRestApp.entity.User;
import com.turysbay.UserPortalRestApp.service.OrderService;
import com.turysbay.UserPortalRestApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Tag(name = "ORDERS")
    @Operation(summary = "Create orders")
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

    @Tag(name = "ORDERS")
    @Operation(summary = "Get all orders",
            description = "Get the list of all orders")
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder(){
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @Tag(name = "ORDERS")
    @Operation(summary = "Get orders by Id",
            description = "Get the orders by Id")
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){return orderService.getOrderById(id);}

    @Tag(name = "ORDERS")
    @Operation(summary = "Update Orders by Id")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrdersById(@PathVariable Long id, @RequestBody Order order){
        order.setId(id);
        orderService.updateOrderById(order);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "ORDERS")
    @Operation(summary = "Delete Orders by Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "ORDERS")
    @Operation(summary = "Get Orders by Id of Creator")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok().body(orders);
    }
}
