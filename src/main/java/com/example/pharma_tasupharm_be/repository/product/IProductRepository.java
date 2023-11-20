package com.example.pharma_tasupharm_be.repository.product;

import com.example.pharma_tasupharm_be.dto.product.ICategoriesDto;
import com.example.pharma_tasupharm_be.dto.product.IImageDto;
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
    @Query(value = "select p.name as name, p.price as price , i.name as image " +
            "from image as i " +
            "join product as p on p.id = i.id_product " +
            "where p.name like :name", nativeQuery = true)
    Page<IProductDto> findAllProduct(Pageable pageable, @Param("name") String s);

    @Query(value = "SELECT p.id AS id, " +
            "       p.name AS name, " +
            "       p.price AS price," +
            "       p.price_sale AS priceSale," +
            "       p.id_category AS idCategory, " +
            "       p.quantity AS quantity_in_stock, " +
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
            "       p.id_category AS idCategory, " +
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
            "       p.id_category AS idCategory, " +
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

    @Query(value = "select p.id, p.name as name, p.price as price , MIN(i.name) AS image " +
            "from image as i " +
            "join product as p on p.id = i.id_product " +
            "where p.id_category = :id " +
            "GROUP BY p.id", nativeQuery = true)
    List<IProductDto> findAllByCategory(@Param("id") Long id);
}
