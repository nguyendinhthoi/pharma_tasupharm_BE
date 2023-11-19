package com.example.pharma_tasupharm_be.repository.order;

import com.example.pharma_tasupharm_be.dto.order.IOrderDetailDto;
import com.example.pharma_tasupharm_be.model.order.Order;
import com.example.pharma_tasupharm_be.model.order.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into `order` (date_of_order,time_of_order,total_money,payment_status,id_user) " +
            "values (:#{#order.dateOfOrder},:#{#order.timeOfOrder},:#{#order.totalMoney},0,:#{#order.appUser.id})",nativeQuery = true)
    void createOrder(Order order);

    @Query(value = "select * from `order` where id_user = :id and payment_status = 0",nativeQuery = true)
    Order findOrderById(@Param("id") Long userId);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO  order_detail  ( price_order , quantity, id_order,id_product ) " +
            "VALUES (:#{#orderDetail.priceOrder},:#{#orderDetail.quantity},:#{#orderDetail.order.id}," +
            ":#{#orderDetail.product.id})",nativeQuery = true)
    Integer createOrderDetail(OrderDetail orderDetail);

    @Query(value = "select price_order as priceProduct, quantity as quantity " +
            "from order_detail " +
            "where id_order = :id",nativeQuery = true)
    List<IOrderDetailDto> findOrderDetailById(@Param("id") Long orderId);

    @Modifying
    @Transactional
    @Query(value = "update `order` set payment_status = 1, total_money = :total where id = :id", nativeQuery = true)
    void updateTotalMoney(@Param("total") double total,@Param("id") Long id);
}
