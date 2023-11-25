package com.jedisebas.tconnectapi.controller;

import com.jedisebas.tconnectapi.dto.ProductDto;
import com.jedisebas.tconnectapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/products",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<ProductDto> getAll() {
        return service.fetchAllProducts();
    }

    @GetMapping("/{code}")
    public List<ProductDto> getByCode(@PathVariable Long code) {
        return service.fetchByCode(code);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateOne(@RequestBody ProductDto dto) {
        return service.updateProduct(dto);
    }
}
