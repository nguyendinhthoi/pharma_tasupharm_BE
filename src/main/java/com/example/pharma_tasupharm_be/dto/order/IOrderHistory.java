package com.example.pharma_tasupharm_be.dto.order;

public interface IOrderHistory {
    Long getId();
    String getDateOfOrder();
    String getTimeOfOrder();
    Double getTotalMoney();
    String getInfoBuy();
}
