package com.example.pharma_tasupharm_be.repository.customer;

import com.example.pharma_tasupharm_be.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "select * from customer where id_user = :id",nativeQuery = true)
    Customer findCustomerByIdUser(@Param("id") Long idUser);
}
