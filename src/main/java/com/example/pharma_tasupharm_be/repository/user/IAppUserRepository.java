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
public interface IAppUserRepository extends JpaRepository<AppUser,Long> {
    @Transactional()
    @Query(value = "select * from app_user where user_name = :name", nativeQuery = true)
    AppUser findAppUserByName(@Param("name") String userName);

}
