package com.jedisebas.tconnectapi.repository;

import com.jedisebas.tconnectapi.entity.Product;
import com.jedisebas.tconnectapi.specification.ProductSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    Sort defaultSort = Sort.by(Sort.Order.desc("dateTime"));

    @Query("SELECT p FROM Product p " +
            "WHERE p.code LIKE ?1 AND p.name LIKE ?2 AND p.nW LIKE ?3 AND p.wN LIKE ?4")
    Optional<Product> findByCode(Long code, String name, Integer nW, String wN);

    @Query("SELECT p FROM Product p " +
            "WHERE p.numberT IS NULL")
    List<Product> findAllWithNullNumberT();

    @Query("SELECT p FROM Product p " +
            "WHERE p.numberT IS NOT NULL ORDER BY p.dateTime DESC")
    List<Product> findAllWithNumberTOrdered();

    @Query("SELECT p FROM Product p " +
            "WHERE p.code = ?1 AND p.numberT IS NULL AND p.dateTime IS NULL")
    List<Product> findAllByCodeWithNullNumberT(Long code);

    default List<Product> findAllByCodePartAndWn(Long codePart, String wN) {
        return findAll(
                Specification.where(ProductSpecification.withCodePart(codePart))
                        .and(ProductSpecification.withWN(wN)),
                defaultSort
        );
    }

    default List<Product> findAllByParamsCode(Long code, Integer numberT, LocalDate dateTime, String wN) {
        return findAll(
                Specification.where(ProductSpecification.withCode(code))
                        .and(ProductSpecification.withNumberT(numberT))
                        .and(ProductSpecification.withDate(dateTime))
                        .and(ProductSpecification.withWN(wN)),
                defaultSort
        );
    }

    default List<Product> findAllByParamsCodePart(Long codePart, Integer numberT, LocalDate dateTime, String wN) {
        return findAll(
                Specification.where(ProductSpecification.withCodePart(codePart))
                        .and(ProductSpecification.withNumberT(numberT))
                        .and(ProductSpecification.withDate(dateTime))
                        .and(ProductSpecification.withWN(wN)),
                defaultSort
        );
    }

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE Product p SET p.numberT = ?1, p.dateTime = ?2 WHERE p.code LIKE ?3 AND p.name LIKE ?4 AND p.nW LIKE ?5 AND p.wN LIKE ?6")
    void update(Integer numberT, LocalDateTime dateTime, Long code, String name, Integer nW, String wN);
}
