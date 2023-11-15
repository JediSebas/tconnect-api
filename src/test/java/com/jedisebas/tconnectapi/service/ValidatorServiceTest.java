package com.jedisebas.tconnectapi.service;

import com.jedisebas.tconnectapi.dto.ProductDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorServiceTest {

    private final ValidatorService validator = new ValidatorService();

    @Test
    void givenValidProductDto() {
        ProductDto dto = ProductDto.builder()
                .code(5900059254827L)
                .name("Produkt3")
                .nW(99233)
                .wN("W3")
                .numberT(5)
                .dateTime(LocalDateTime.parse("2023-11-14T14:05:21"))
                .build();
        assertDoesNotThrow(() -> validator.validateProductDtoFields(dto));
    }

    @Test
    void givenNotValidProductDto() {
        ProductDto dto = ProductDto.builder()
                .code(null)
                .name(null)
                .nW(null)
                .wN(null)
                .numberT(null)
                .dateTime(null)
                .build();
        assertThrows(IllegalArgumentException.class, () -> validator.validateProductDtoFields(dto));
    }

    @Test
    void givenValidValues_whenCheckDateTimeFormat() {
        assertDoesNotThrow(() -> validator.checkDateTimeFormat("2023-01-01T00:00:00"));
        assertDoesNotThrow(() -> validator.checkDateTimeFormat("2023-11-14T14:05:21"));
        assertDoesNotThrow(() -> validator.checkDateTimeFormat("2020-12-31T12:31:00"));
        assertDoesNotThrow(() -> validator.checkDateTimeFormat("2020-10-10T01:59:59"));
    }

    @Test
    void givenNotValidValues_whenCheckDateTimeFormat() {
        assertThrows(IllegalArgumentException.class, () -> validator.checkDateTimeFormat(""));
        assertThrows(IllegalArgumentException.class, () -> validator.checkDateTimeFormat("2020-14-01"));
    }

    @Test
    void givenNotExistingDayOrSomething_whenCheckDateTimeFormat() {
        assertDoesNotThrow(() -> validator.checkDateTimeFormat("2020-14-01T12:00:00"));
        assertDoesNotThrow(() -> validator.checkDateTimeFormat("2020-14-01T12:00:00"));
        assertDoesNotThrow(() -> validator.checkDateTimeFormat("2020-12-32T12:00:00"));
        assertDoesNotThrow(() -> validator.checkDateTimeFormat("2020-12-31T25:00:00"));
        assertDoesNotThrow(() -> validator.checkDateTimeFormat("2020-12-31T23:61:00"));
        assertDoesNotThrow(() -> validator.checkDateTimeFormat("2020-12-31T23:59:61"));
    }

    @Test
    void givenMissingDigit_whenCheckDateTimeFormat() {
        assertThrows(IllegalArgumentException.class, () -> validator.checkDateTimeFormat("2020-12-31T3:00:00"));
        assertThrows(IllegalArgumentException.class, () -> validator.checkDateTimeFormat("2020-1-1T03:00:00"));
        assertThrows(IllegalArgumentException.class, () -> validator.checkDateTimeFormat("2020-1-01T03:00:00"));
        assertThrows(IllegalArgumentException.class, () -> validator.checkDateTimeFormat("2020-01-1T03:00:00"));
        assertThrows(IllegalArgumentException.class, () -> validator.checkDateTimeFormat("2020-01-01T03:01"));
    }
}
