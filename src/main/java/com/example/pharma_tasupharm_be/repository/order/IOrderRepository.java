package com.example.pharma_tasupharm_be.repository.order;

import com.example.pharma_tasupharm_be.dto.order.IDetailHistory;
import com.example.pharma_tasupharm_be.dto.order.IOrderDetailDto;
import com.example.pharma_tasupharm_be.dto.order.IOrderHistory;
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
    @Query(value =
            "SELECT " +
                    "    OB.id , " +
                    "    OB.date_of_order as dateOfOrder, " +
                    "    OB.time_of_order as timeOfOrder, " +
                    "    OB.total_money as totalMoney, " +
                    "    GROUP_CONCAT(CONCAT(P.name, ' x', OD.quantity) SEPARATOR ', \\n') as infoBuy " +
                    "FROM `order` OB " +
                    "LEFT JOIN order_detail OD ON OB.id = OD.id_order " +
                    "LEFT JOIN product P ON OD.id_product = P.id " +
                    "WHERE OB.id_user = :id AND OB.payment_status = 1 " +
                    "GROUP BY OB.id " +
                    "ORDER BY OB.date_of_order DESC, OB.time_of_order DESC", nativeQuery = true)
    List<IOrderHistory> findAllHistory(@Param("id") Long userId);

    @Query(value = "select od.id, od.price_order as priceOrder, od.quantity as quantity,p.name " +
            "from order_detail od " +
            "join product p on p.id = od.id_product " +
            "where id_order = :id",nativeQuery = true)
    List<IDetailHistory> findDetailHistory(@Param("id") Long id);
}
