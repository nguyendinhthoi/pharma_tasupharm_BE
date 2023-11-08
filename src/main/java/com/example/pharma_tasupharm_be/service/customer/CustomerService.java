package com.example.pharma_tasupharm_be.service.customer;

import com.example.pharma_tasupharm_be.model.customer.Customer;
import com.example.pharma_tasupharm_be.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Customer findCustomerByIdUser(Long idUser) {
        return customerRepository.findCustomerByIdUser(idUser);
    }
}
