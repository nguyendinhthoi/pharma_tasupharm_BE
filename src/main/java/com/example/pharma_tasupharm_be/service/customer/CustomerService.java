package com.example.pharma_tasupharm_be.service.customer;

import com.example.pharma_tasupharm_be.model.customer.Customer;
import com.example.pharma_tasupharm_be.model.user.AppUser;
import com.example.pharma_tasupharm_be.repository.customer.ICustomerRepository;
import com.example.pharma_tasupharm_be.repository.user.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IAppUserRepository appUserRepository;

    @Override
    public Customer findCustomerByIdUser(Long idUser) {
        return customerRepository.findCustomerByIdUser(idUser);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void createCustomer(Customer customer) {
        AppUser appUser = appUserRepository.findAppUserNewest();
        customer.setName("Khách hàng");
        customer.setEmail(appUser.getEmail());
        customer.setAppUser(appUser);
        customerRepository.createCustomer(customer);
    }
}
