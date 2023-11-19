package com.example.pharma_tasupharm_be.service.order;

import com.example.pharma_tasupharm_be.dto.cart.ICartDto;
import com.example.pharma_tasupharm_be.dto.order.IOrderDetailDto;
import com.example.pharma_tasupharm_be.model.order.Order;
import com.example.pharma_tasupharm_be.model.order.OrderDetail;
import com.example.pharma_tasupharm_be.model.product.Product;
import com.example.pharma_tasupharm_be.model.user.AppUser;
import com.example.pharma_tasupharm_be.repository.cart.ICartRepository;
import com.example.pharma_tasupharm_be.repository.order.IOrderRepository;
import com.example.pharma_tasupharm_be.repository.product.IProductRepository;
import com.example.pharma_tasupharm_be.repository.user.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IAppUserRepository appUserRepository;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private IProductRepository productRepository;
    @Override
    public void createOrder(Long userId) throws Exception {
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        if (appUser == null){
            throw new Exception("Không tìm thấy user");
        }
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        Order order = new Order();
        order.setDateOfOrder(String.valueOf(localDate));
        order.setTimeOfOrder(String.valueOf(localTime));
        order.setTotalMoney(0.0);
        order.setAppUser(appUser);
        orderRepository.createOrder(order);
    }

    @Override
    public void createOrderDetail(Long userId) throws Exception {
        Order order = orderRepository.findOrderById(userId);
        List<ICartDto> cartDto = cartRepository.getAllCart(userId);
        if (order ==  null){
            throw new Exception("Không tìm thấy order");
        }
        for (ICartDto cartDto1 : cartDto){
            OrderDetail orderDetail = new OrderDetail();
            Product product = productRepository.findById(cartDto1.getIdProduct()).orElse(null);
            if (product == null){
                throw new Exception("Không tìm thấy sản phẩm");
            }
            orderDetail.setQuantity(cartDto1.getQuantity());
            orderDetail.setProduct(product);
            if (product.getPriceSale() != null){
                orderDetail.setPriceOrder(product.getPriceSale());
            }else {
                orderDetail.setPriceOrder(product.getPrice());
            }
            orderDetail.setOrder(order);
            Integer isOrderDetailCreated = orderRepository.createOrderDetail(orderDetail);
            if (isOrderDetailCreated > 0){
                Integer quantityOfProductAfterPayment = product.getQuantity() - orderDetail.getQuantity();
                productRepository.updateQuantityOfProduct(product.getId(),quantityOfProductAfterPayment);
            }else {
                orderRepository.deleteById(order.getId());
            }
        }
        cartRepository.deleteCart(userId);
    }

    @Override
    public void updateTotalMoney(Long userId) throws Exception {
        Order order = orderRepository.findOrderById(userId);
        List<IOrderDetailDto> orderDetails = orderRepository.findOrderDetailById(order.getId());
        if (orderDetails.isEmpty()){
            throw new Exception("Không tìm thấy order chi tiết");
        }
        double total = 0;
        for (IOrderDetailDto orderDetail: orderDetails) {
            total += orderDetail.getPriceProduct() * orderDetail.getQuantity();
        }
        orderRepository.updateTotalMoney(total,order.getId());
    }
}
