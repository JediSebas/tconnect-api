package com.jedisebas.tconnectapi.mapper;

import com.jedisebas.tconnectapi.TestConstants;
import com.jedisebas.tconnectapi.dto.ProductDto;
import com.jedisebas.tconnectapi.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {

    private final ProductMapper mapper = new ProductMapper();

    @Test
    void givenProduct_whenMapping_thenReturnDto() {
        final Product product = Product.builder()
                .id(1)
                .code(TestConstants.CODE)
                .name(TestConstants.NAME)
                .nW(TestConstants.N_W)
                .wN(TestConstants.W_N)
                .numberT(TestConstants.NUMBER_T)
                .dateTime(TestConstants.DATE_TIME)
                .build();

        ProductDto dto1 = ProductDto.builder()
                .code(TestConstants.CODE)
                .name(TestConstants.NAME)
                .nW(TestConstants.N_W)
                .wN(TestConstants.W_N)
                .numberT(TestConstants.NUMBER_T)
                .dateTime(TestConstants.DATE_TIME)
                .build();

        final ProductDto dto2 = mapper.entityToDto(product);

        assertEquals(dto1, dto2);
    }
}
