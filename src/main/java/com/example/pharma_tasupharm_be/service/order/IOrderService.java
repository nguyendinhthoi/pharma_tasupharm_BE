package com.example.pharma_tasupharm_be.service.order;

import com.example.pharma_tasupharm_be.dto.order.IDetailHistory;
import com.example.pharma_tasupharm_be.dto.order.IOrderHistory;

import java.util.List;

public interface IOrderService {
    void createOrder(Long userId) throws Exception;

    void createOrderDetail(Long userId) throws Exception;

    void updateTotalMoney(Long userId) throws Exception;

    List<IOrderHistory> getAllHistory(Long userId);

    List<IDetailHistory> getAllOrderDetail(Long id);
}
