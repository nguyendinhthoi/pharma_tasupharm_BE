package com.example.pharma_tasupharm_be.repository.product;

import com.example.pharma_tasupharm_be.dto.product.ICategoriesDto;
import com.example.pharma_tasupharm_be.dto.product.IImageDto;
import com.example.pharma_tasupharm_be.dto.product.IProductDetail;
import com.example.pharma_tasupharm_be.dto.product.IProductDto;
import com.example.pharma_tasupharm_be.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p.id,p.quantity ,p.name AS name,c.name as nameCategory, p.price AS price, p.price_sale as priceSale, MIN(i.name) AS image " +
            "FROM image AS i " +
            "JOIN product AS p " +
            "ON p.id = i.id_product " +
            "JOIN category AS c " +
            "ON p.id_category = c.id " +
            "WHERE p.price BETWEEN :min AND :max " +
            "GROUP BY p.id", nativeQuery = true)
    Page<IProductDto> findAllProduct(Pageable pageable,@Param("min") double min,@Param("max") double max);

    @Query(value = "SELECT p.id AS id, " +
            "       p.name AS name, " +
            "       p.price AS price," +
            "       p.price_sale AS priceSale," +
            "       p.id_category AS idCategory, " +
            "       p.quantity, " +
            "       COALESCE(MIN(i.name), 'No Image') AS image, " +
            "       COALESCE(SUM(od.quantity), 0) AS total_quantity_sold " +
            "FROM product p " +
            "LEFT JOIN order_detail od ON p.id = od.id_product " +
            "LEFT JOIN image i ON p.id = i.id_product " +
            "GROUP BY p.id, p.name, p.price, p.quantity " +
            "ORDER BY total_quantity_sold DESC " +
            "LIMIT 6", nativeQuery = true)
    List<IProductDto> findAllBestSeller();

    @Query(value = "SELECT c.id as id, c.name as name FROM category c", nativeQuery = true)
    List<ICategoriesDto> findAllCategories();
    @Query(value = "SELECT p.id AS id, " +
            "       p.name AS name, " +
            "       p.price AS price," +
            "       p.price_sale AS priceSale," +
            "       p.id_category AS idCategory," +
            "       p.quantity ," +
            "       COALESCE(MIN(i.name), 'No Image') AS image " +
            "FROM product p " +
            "LEFT JOIN image i ON p.id = i.id_product " +
            "GROUP BY p.id " +
            "ORDER BY p.id DESC " +
            "LIMIT 9", nativeQuery = true)
    List<IProductDto> findAllNewProduct();

    @Query(value = "SELECT p.id AS id, " +
            "       p.name AS name, " +
            "       p.price AS price," +
            "       p.price_sale AS priceSale," +
            "       p.id_category AS idCategory," +
            "       p.quantity , " +
            "       MIN(i.name) AS image " +
            "FROM product p " +
            "LEFT JOIN image i ON p.id = i.id_product " +
            "WHERE p.name like :name " +
            "GROUP BY p.id ", nativeQuery = true)
    Page<IProductDto> findAllByName(Pageable pageable, @Param("name") String s);

    @Modifying
    @Transactional
    @Query(value = "update product set quantity = :quantity where id = :id", nativeQuery = true)
    void updateQuantityOfProduct(@Param("id") Long id,@Param("quantity") Integer quantityOfProductAfterPayment);


    @Query(value = "SELECT id, name from image where id_product = :id", nativeQuery = true)
    List<IImageDto> findImageByProduct(@Param("id") Long idProduct);

    @Query(value = "select p.id,p.quantity, p.name as name, p.price as price , MIN(i.name) AS image " +
            "from image as i " +
            "join product as p on p.id = i.id_product " +
            "where p.id_category = :id " +
            "GROUP BY p.id", nativeQuery = true)
    List<IProductDto> findAllByCategory(@Param("id") Long id);

    @Query(value = "select * from cart where id_product = :id",nativeQuery = true)
    Product findProductInCart(@Param("id") Long idProduct);
    @Query(value = "SELECT p.id AS id, " +
            "       p.name AS name, " +
            "       p.price AS price," +
            "       p.price_sale AS priceSale," +
            "       p.quantity AS quantity, " +
            "       p.description AS description, " +
            "       p.ingredients AS ingredients, " +
            "       p.medical_uses AS medicalUses, " +
            "       p.how_to_use AS howToUse, " +
            "       p.intended_users AS intendedUsers, " +
            "       p.precautions AS precautions, " +
            "       p.storage AS storage, " +
            "       p.distribution_facility AS distributionFacility, " +
            "       p.manufactured_by AS manufacturedBy, " +
            "       p.caution AS caution, " +
            "       p.packaging AS packaging, " +
            "       p.id_category AS idCategory, " +
            "       c.name AS nameCategory, " +
            "       COALESCE(MIN(i.name), 'No Image') AS image " +
            "FROM product p " +
            "JOIN image i ON p.id = i.id_product " +
            "JOIN category c ON p.id_category = c.id " +
            "where p.id = :id " +
            "GROUP BY p.id ", nativeQuery = true)
    IProductDetail findProductDtoById(@Param("id") Long idProduct);


    @Query(value = "SELECT p.id,p.quantity, p.name AS name,c.name as nameCategory, p.price AS price, p.price_sale as priceSale, MIN(i.name) AS image " +
            "FROM image AS i " +
            "JOIN product AS p " +
            "ON p.id = i.id_product " +
            "JOIN category AS c " +
            "ON p.id_category = c.id " +
            "WHERE p.id_category = :id " +
            "AND p.price BETWEEN :min AND :max " +
            "GROUP BY p.id", nativeQuery = true)
    Page<IProductDto> findProductByCategory(Pageable pageable, @Param("id") Long idCategory,@Param("min") double min,@Param("max") double max);
}
