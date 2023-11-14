package com.jedisebas.tconnectapi.mapper;

import com.jedisebas.tconnectapi.dto.ProductDto;
import com.jedisebas.tconnectapi.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product dtoToEntity(ProductDto dto) {
        return Product.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .nW(dto.getNW())
                .wN(dto.getWN())
                .numberT(dto.getNumberT())
                .dateTime(dto.getDateTime())
                .build();
    }

    public ProductDto entityToDto(Product product) {
        return ProductDto.builder()
                .code(product.getCode())
                .name(product.getName())
                .nW(product.getNW())
                .wN(product.getWN())
                .numberT(product.getNumberT())
                .dateTime(product.getDateTime())
                .build();
    }
}
