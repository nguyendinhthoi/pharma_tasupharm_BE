package com.example.pharma_tasupharm_be.service.order;

public interface IOrderService {
    void createOrder(Long userId) throws Exception;

    void createOrderDetail(Long userId) throws Exception;

    void updateTotalMoney(Long userId) throws Exception;
}
