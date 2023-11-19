package com.example.pharma_tasupharm_be.controller;

import com.example.pharma_tasupharm_be.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @PostMapping("/payment/{userId}")
    public ResponseEntity<Object> paymentOrder(@PathVariable Long userId){
        try {
            orderService.createOrder(userId);
            orderService.createOrderDetail(userId);
            orderService.updateTotalMoney(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("Bạn đã thanh toán thành công", HttpStatus.OK);
    }
}
