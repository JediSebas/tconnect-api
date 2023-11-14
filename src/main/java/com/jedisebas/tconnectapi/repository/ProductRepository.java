package com.jedisebas.tconnectapi.repository;

import com.jedisebas.tconnectapi.entity.Product;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.code LIKE ?1")
    List<Product> findAllByCode(String code);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE Product p SET p.numberT = ?1, p.dateTime = ?2 WHERE p.code LIKE ?3 AND p.name LIKE ?4 AND p.nW LIKE ?5 AND p.wN LIKE ?6")
    void update(String numberT, LocalDateTime dateTime, String code, String name, String nW, String wN);
}
