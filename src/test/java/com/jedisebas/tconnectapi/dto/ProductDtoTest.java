package com.jedisebas.tconnectapi.dto;

import com.jedisebas.tconnectapi.TestConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDtoTest {

    @Test
    void givenTwoDto_whenSetNewValue_thenEqualToString() {
        final ProductDto dto = ProductDto.builder()
                .code(TestConstants.CODE)
                .name(TestConstants.NAME)
                .nW(TestConstants.N_W)
                .wN(TestConstants.W_N)
                .numberT(TestConstants.NUMBER_T)
                .dateTime(TestConstants.DATE_TIME)
                .build();

        dto.setWN("W1");
        final String dtoToString = "ProductDto(code=5900059254827, name=Produkt3, nW=99233, wN=W1, numberT=5, dateTime=2023-11-14T14:05:21)";

        assertEquals(dtoToString, dto.toString());
    }
}
