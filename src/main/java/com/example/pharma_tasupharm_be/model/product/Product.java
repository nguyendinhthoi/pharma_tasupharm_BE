package com.example.pharma_tasupharm_be.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Double priceSale;
    private Integer quantity;
    private String description;// mô tả ngắn
    private String ingredients;// thành phần
    private String medicalUses;// công dụng
    private String howToUse;// cách sử dụng
    private String intendedUsers;// đối tượng sử dụng
    private String precautions;// thận trọng
    private String storage;// bảo quản
    private String distributionFacility;// cơ sở phân phối
    private String manufacturedBy;// sản xuất bởi
    private String caution;// lưu ý
    private String packaging;//quy cách đóng gói



    @ManyToOne
    @JoinColumn(name = "idCategory", referencedColumnName = "id")
    private Category category;
}
