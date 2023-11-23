package com.example.pharma_tasupharm_be.repository.customer;

import com.example.pharma_tasupharm_be.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "select * from customer where id_user = :id",nativeQuery = true)
    Customer findCustomerByIdUser(@Param("id") Long idUser);

//    @Modifying
//    @Transactional
//    @Query(value = "update customer set address = :#{#customer.address},birthday = :#{#customer.birthday},email = :#{#customer.email}, " +
//            "gender = :#{#customer.gender},name =:#{#customer.name},phone_number=:#{#customer.phoneNumber} where id = :#{#customer.id}", nativeQuery = true)
//    void updateCustomer(Customer customer);

    @Modifying
    @Transactional
    @Query(value = "insert into customer (id_user,name) value (:#{#customer.appUser.id},:#{#customer.name})",nativeQuery = true)
    void createCustomer(Customer customer);
}
