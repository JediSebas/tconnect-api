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

    public List<ProductDto> fetchAllWithNullNumberT() {
        List<ProductDto> toReturn = new ArrayList<>();
        repository.findAllWithNullNumberT().forEach(product -> toReturn.add(mapper.entityToDto(product)));

        return toReturn;
    }

    public List<ProductDto> fetchAllWithNumberT() {
        List<ProductDto> toReturn = new ArrayList<>();
        repository.findAllWithNumberTOrdered().forEach(product -> toReturn.add(mapper.entityToDto(product)));

        return toReturn;
    }

    public List<ProductDto> fetchAllByCodeWithNullNumberT(Long code) {
        List<ProductDto> toReturn = new ArrayList<>();
        repository.findAllByCodeWithNullNumberT(code).forEach(product -> toReturn.add(mapper.entityToDto(product)));

        return toReturn;
    }

    public List<ProductDto> fetchAllByCodePartAndWn(Long codePart, String wN) {
        List<ProductDto> toReturn = new ArrayList<>();
        repository.findAllByCodePartAndWn(codePart, wN).forEach(product -> toReturn.add(mapper.entityToDto(product)));

        return toReturn;
    }

    public List<ProductDto> fetchAllByParamsCode(Long code, Integer numberT, LocalDate dateTime, String wN) {
        List<ProductDto> toReturn = new ArrayList<>();
        repository.findAllByParamsCode(code, numberT, dateTime, wN).forEach(product -> toReturn.add(mapper.entityToDto(product)));

        return toReturn;
    }

    public List<ProductDto> fetchAllByParamsCodePart(Long codePart, Integer numberT, LocalDate dateTime, String wN) {
        List<ProductDto> toReturn = new ArrayList<>();
        repository.findAllByParamsCodePart(codePart, numberT, dateTime, wN).forEach(product -> toReturn.add(mapper.entityToDto(product)));

        return toReturn;
    }

    public ProductDto updateProduct(ProductDto dto) {
        try {
            validator.validateProductDtoFields(dto);

            Product product = repository.findByCode(dto.getCode(), dto.getName(), dto.getNW(), dto.getWN())
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
            repository.update(product.getNumberT(), product.getDateTime(), product.getCode(),
                    product.getName(), product.getNW(), product.getWN());

        }
    }
}
