package com.jedisebas.tconnectapi.controller;

import com.jedisebas.tconnectapi.dto.ProductDto;
import com.jedisebas.tconnectapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/v2/products",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<ProductDto> getAll() {
        return service.fetchAllProducts();
    }

    @GetMapping("/without-t")
    public List<ProductDto> getAllWithNullNumberT() {
        return service.fetchAllWithNullNumberT();
    }

    @GetMapping("/with-t")
    public List<ProductDto> getAllWithNumberT() {
        return service.fetchAllWithNumberT();
    }

    @GetMapping(value = "/main", params = "code")
    public List<ProductDto> getAllByCode(@RequestParam Long code) {
        return service.fetchAllByCodeWithNullNumberT(code);
    }

    @GetMapping(value = "/main", params = {"part", "wn"})
    public List<ProductDto> getAllByCodePartAndWn(@RequestParam(required = false) Long part, @RequestParam(required = false) String wn) {
        return service.fetchAllByCodePartAndWn(part, wn);
    }

    @GetMapping(value = "/extra", params = {"code", "number", "date", "wn"})
    public List<ProductDto> getAllByParamsCode(@RequestParam(required = false) Long code, @RequestParam(required = false) Integer number,
                                               @RequestParam(required = false) LocalDate date, @RequestParam(required = false) String wn) {
        return service.fetchAllByParamsCode(code, number, date, wn);
    }

    @GetMapping(value = "/extra", params = {"part", "number", "date", "wn"})
    public List<ProductDto> getAllByParamsCodePart(@RequestParam(required = false) Long part, @RequestParam(required = false) Integer number,
                                                   @RequestParam(required = false) LocalDate date, @RequestParam(required = false) String wn) {
        System.out.println("DUPAAA");
        System.out.println(part);
        System.out.println(number);
        System.out.println(date);
        System.out.println(wn);
        return service.fetchAllByParamsCodePart(part, number, date, wn);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateOne(@RequestBody ProductDto dto) {
        return service.updateProduct(dto);
    }
}
