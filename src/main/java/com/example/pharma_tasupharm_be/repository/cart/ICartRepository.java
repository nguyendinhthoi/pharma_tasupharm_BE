package com.example.pharma_tasupharm_be.repository.cart;

import com.example.pharma_tasupharm_be.dto.cart.ICartDto;
import com.example.pharma_tasupharm_be.model.order.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into cart (quantity_order,id_user,id_product) " +
            "values (:#{#cart.quantityOrder},:#{#cart.appUser.id},:#{#cart.product.id})",nativeQuery = true)
    void addToCart(Cart cart);
    @Query(value = "SELECT " +
            "    c.id_user AS idUser, " +
            "    c.id_product AS idProduct," +
            "    c.quantity_order as quantity, " +
            "    p.name, " +
            "    p.price, " +
            "    p.price_sale AS priceSale, " +
            "    MIN(i.name) AS image " +
            "FROM " +
            "    cart AS c " +
            "        JOIN " +
            "    product AS p ON p.id = c.id_product " +
            "        JOIN " +
            "    image AS i ON p.id = i.id_product " +
            "WHERE " +
            "        c.id_user = :id " +
            "GROUP BY " +
            "    c.id_user, c.id_product, c.quantity_order, p.name, p.price, p.price_sale;", nativeQuery = true)
    List<ICartDto> getAllCart(@Param("id") Long idUser);

    @Modifying
    @Transactional
    @Query(value = "delete from cart where id_user = :idUser and id_product = :idProduct",nativeQuery = true)
    Integer deleteProduct(@Param("idUser") Long idUser,@Param("idProduct") Long idProduct);
}
