package com.jedisebas.tconnectapi.entity;

import com.jedisebas.tconnectapi.constants.ProductConst;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = ProductConst.TABLE)
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @Column(name = ProductConst.CODE)
    private Long code;

    @Column(name = ProductConst.NAME)
    private String name;

    @Column(name = ProductConst.NW)
    private Integer nW;

    @Column(name = ProductConst.WN)
    private String wN;

    @Nullable
    @Column(name = ProductConst.NUMBER_T)
    private Integer numberT;

    @Nullable
    @Column(name = ProductConst.DATETIME)
    private LocalDateTime dateTime;
}
