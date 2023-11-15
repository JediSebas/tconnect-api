package com.jedisebas.tconnectapi.entity;

import com.jedisebas.tconnectapi.constants.ProductConst;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ProductConst.ID)
    private Integer id;

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
