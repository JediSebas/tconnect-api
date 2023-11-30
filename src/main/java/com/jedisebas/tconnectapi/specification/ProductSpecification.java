package com.jedisebas.tconnectapi.specification;

import com.jedisebas.tconnectapi.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSpecification {

    public static Specification<Product> withCode(Long code) {
        return (root, query, builder) ->
                code == null ? builder.isTrue(builder.literal(true)) :
                        builder.equal(root.get("code"), code);
    }

    public static Specification<Product> withCodePart(Long codePart) {
        return (root, query, builder) ->
                codePart == null ? builder.isTrue(builder.literal(true)) :
                        builder.like(root.get("code").as(String.class), "%" + codePart);
    }

    public static Specification<Product> withNumberT(Integer numberT) {
        return (root, query, builder) ->
                numberT == null ? builder.isTrue(builder.literal(true)) :
                        builder.equal(root.get("numberT"), numberT);
    }

    public static Specification<Product> withDate(LocalDate date) {
        return (root, query, builder) ->
                date == null ? builder.isTrue(builder.literal(true)) :
                        builder.equal(root.get("dateTime"), date);
    }

    public static Specification<Product> withWN(String wN) {
        return (root, query, builder) ->
                wN == null || wN.isEmpty() ? builder.isTrue(builder.literal(true)) :
                        builder.like(root.get("wN"), wN);
    }
}
