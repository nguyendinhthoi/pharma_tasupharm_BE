package com.example.pharma_tasupharm_be.dto.product;

public interface IProductDetail {
    Long getId();
    String getName();
    Double getPrice();
    Double getPriceSale();
    Integer getQuantity();
    String getDescription();// mô tả ngắn
    String getIngredients();// thành phần
    String getMedicalUses();// công dụng
    String getHowToUse();// cách sử dụng
    String getIntendedUsers();// đối tượng sử dụng
    String getPrecautions();// thận trọng
    String getStorage();// bảo quản
    String getDistributionFacility();// cơ sở phân phối
    String getManufacturedBy();// sản xuất bởi
    String getCaution();// lưu ý
    String getPackaging();//quy cách đóng gói
    Long getIdCategory();
    String getNameCategory();
    String getImage();
}
