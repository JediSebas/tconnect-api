package com.jedisebas.tconnectapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jedisebas.tconnectapi.constants.ProductConst;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@ToString
public class ProductDto {

    @JsonProperty(ProductConst.CODE)
    private Long code;

    @JsonProperty(ProductConst.NAME)
    private String name;

    @JsonProperty(ProductConst.NW)
    private Integer nW;

    @JsonProperty(ProductConst.WN)
    private String wN;

    @Nullable
    @JsonProperty(ProductConst.NUMBER_T)
    private Integer numberT;

    @Nullable
    @JsonProperty(ProductConst.DATETIME)
    private LocalDateTime dateTime;
}
