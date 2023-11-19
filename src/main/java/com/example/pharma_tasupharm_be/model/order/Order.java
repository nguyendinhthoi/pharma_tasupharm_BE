package com.example.pharma_tasupharm_be.model.order;

import com.example.pharma_tasupharm_be.model.user.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "date")
    private String dateOfOrder;
    @Column(columnDefinition = "time")
    private String timeOfOrder;
    private Double totalMoney;
    private Integer paymentStatus;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private AppUser appUser;
}
