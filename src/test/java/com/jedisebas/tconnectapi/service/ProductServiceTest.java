package com.jedisebas.tconnectapi.service;

import com.jedisebas.tconnectapi.TestConstants;
import com.jedisebas.tconnectapi.mapper.ProductMapper;
import com.jedisebas.tconnectapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock private ProductRepository repository;
    @Mock private ProductMapper mapper;
    @Mock private ValidatorService validator;

    @InjectMocks private ProductService service;

    @Test
    void fetchAllProductsEmpty() {
        when(repository.findAll()).thenReturn(List.of());
        assertEquals(List.of(), service.fetchAllProducts());
    }

    @Test
    void fetchProductByCodeEmpty() {
        when(repository.findAllByCode(String.valueOf(TestConstants.CODE))).thenReturn(List.of());
        assertEquals(List.of(), service.fetchByCode(TestConstants.CODE));
    }
}
