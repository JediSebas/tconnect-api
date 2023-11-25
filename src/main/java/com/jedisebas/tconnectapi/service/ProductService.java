package com.jedisebas.tconnectapi.service;

import com.jedisebas.tconnectapi.dto.ProductDto;
import com.jedisebas.tconnectapi.entity.Product;
import com.jedisebas.tconnectapi.mapper.ProductMapper;
import com.jedisebas.tconnectapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ValidatorService validator;

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

    public List<ProductDto> fetchByNumberTAndDate(Integer numberT, LocalDate date) {
        List<ProductDto> toReturn = new ArrayList<>();
        repository.findAllByNumberTAndDate(numberT.toString(), date).forEach(product -> toReturn.add(mapper.entityToDto(product)));

        return toReturn;
    }

    public List<ProductDto> fetchByNumberTAndDateAndWN(Integer numberT, LocalDate date, String nW) {
        List<ProductDto> toReturn = new ArrayList<>();
        repository.findAllByNumberTAndDateAndWN(numberT.toString(), date, nW).forEach(product -> toReturn.add(mapper.entityToDto(product)));

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
