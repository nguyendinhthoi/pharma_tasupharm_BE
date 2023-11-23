package com.example.pharma_tasupharm_be.service.customer;

import com.example.pharma_tasupharm_be.model.customer.Customer;

public interface ICustomerService {
    Customer findCustomerByIdUser(Long idUser);

    void updateCustomer(Customer customer);

    void createCustomer(Customer customer);
}
