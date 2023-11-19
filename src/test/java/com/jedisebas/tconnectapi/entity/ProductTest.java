package com.jedisebas.tconnectapi.entity;

import com.jedisebas.tconnectapi.TestConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void givenTwoEntity_whenToString() {
        final Product product = Product.builder()
                .id(1)
                .code(TestConstants.CODE)
                .name(TestConstants.NAME)
                .nW(TestConstants.N_W)
                .wN(TestConstants.W_N)
                .numberT(TestConstants.NUMBER_T)
                .dateTime(TestConstants.DATE_TIME)
                .build();

        final String productToString = "Product(id=1, code=5900059254827, name=Produkt3, nW=99233, wN=W3, numberT=5, dateTime=2023-11-14T14:05:21)";
        assertEquals(productToString, product.toString());
    }

    @Test
    void givenNoArgsConstructor_whenSetValue_thenEqualsAndHashCode() {
        final Product product1 = new Product();
        product1.setId(1);
        product1.setCode(TestConstants.CODE);
        product1.setName(TestConstants.NAME);
        product1.setNW(TestConstants.N_W);
        product1.setWN(TestConstants.W_N);
        product1.setNumberT(TestConstants.NUMBER_T);
        product1.setDateTime(TestConstants.DATE_TIME);

        final Product product2 = Product.builder()
                .id(1)
                .code(TestConstants.CODE)
                .name(TestConstants.NAME)
                .nW(TestConstants.N_W)
                .wN(TestConstants.W_N)
                .numberT(TestConstants.NUMBER_T)
                .dateTime(TestConstants.DATE_TIME)
                .build();

        assertEquals(product2, product1);
        assertEquals(product2.hashCode(), product1.hashCode());
    }
}
