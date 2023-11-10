package com.example.pharma_tasupharm_be.repository.user;

import com.example.pharma_tasupharm_be.model.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUser, Long> {
    @Query(value = "select * from app_user where user_name = :name", nativeQuery = true)
    AppUser findAppUserByName(@Param("name") String userName);

    @Transactional
    @Modifying
    @Query(value = "insert into app_user (user_name,password,email) " +
            "values (:#{#appUser.userName},:#{#appUser.password},:#{#appUser.email})", nativeQuery = true)
    Integer addNewAppUser(AppUser appUser);

    @Query(value = "select r.id from app_role r where r.name = :role", nativeQuery = true)
    Long findAppRoleIdByName(@Param("role") String roleAdmin);

    @Transactional
    @Modifying
    @Query(value = "insert into user_role (id_role,id_user) values (:idRole,:idUser)", nativeQuery = true)
    void addRoleForCustomer(@Param("idUser") Long id, @Param("idRole") Long roleId);

    @Query(value = "select * from app_user where email = :name", nativeQuery = true)
    AppUser findByEmail(@Param("name") String email);
}
