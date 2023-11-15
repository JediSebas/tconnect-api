package com.jedisebas.tconnectapi.service;

import com.jedisebas.tconnectapi.dto.ProductDto;
import com.jedisebas.tconnectapi.entity.Product;
import com.jedisebas.tconnectapi.mapper.ProductMapper;
import com.jedisebas.tconnectapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired private ProductRepository repository;
    @Autowired private ProductMapper mapper;
    @Autowired private ValidatorService validator;

    public List<ProductDto> fetchAllProducts() {
        List<ProductDto> toReturn = new ArrayList<>();
        repository.findAll().forEach(product -> toReturn.add(mapper.entityToDto(product)));

        return toReturn;
    }

    public List<ProductDto> fetchByCode(Long code) {
        List<ProductDto> toReturn = new ArrayList<>();
        repository.findAllByCode(code.toString()).forEach(product -> toReturn.add(mapper.entityToDto(product)));

        return toReturn;
    }

    public ProductDto updateProduct(ProductDto dto) {
        try {
            validator.validateProductDtoFields(dto);

            Product product = repository.findByCode(dto.getCode().toString(), dto.getName(), dto.getNW().toString(), dto.getWN())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));

            product.setCode(dto.getCode());
            product.setName(dto.getName());
            product.setNW(dto.getNW());
            product.setWN(dto.getWN());
            product.setNumberT(dto.getNumberT());
            product.setDateTime(dto.getDateTime());

            update(product);

            return mapper.entityToDto(product);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong request data provided");
        }
    }

    private void update(Product product) {
        if (product.getNumberT() != null && (product.getDateTime() != null)) {
            repository.update(product.getNumberT().toString(), product.getDateTime(),
                    product.getCode().toString(), product.getName(), product.getNW().toString(), product.getWN());

        }
    }
}
