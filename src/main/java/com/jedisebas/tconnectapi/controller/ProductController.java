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

    @GetMapping(value = "/main", params = {"part", "nw"})
    public List<ProductDto> getAllByCodePartAndNw(@RequestParam(required = false) Long part, @RequestParam(required = false) Integer nw) {
        return service.fetchAllByCodePartAndNw(part, nw);
    }

    @GetMapping(value = "/extra", params = {"code", "number", "date", "nw"})
    public List<ProductDto> getAllByParamsCode(@RequestParam(required = false) Long code, @RequestParam(required = false) Integer number,
                                               @RequestParam(required = false) LocalDate date, @RequestParam(required = false) Integer nw) {
        return service.fetchAllByParamsCode(code, number, date, nw);
    }

    @GetMapping(value = "/extra", params = {"part", "number", "date", "nw"})
    public List<ProductDto> getAllByParamsCodePart(@RequestParam(required = false) Long part, @RequestParam(required = false) Integer number,
                                                   @RequestParam(required = false) LocalDate date, @RequestParam(required = false) Integer nw) {
        System.out.println("DUPAAA");
        System.out.println(part);
        System.out.println(number);
        System.out.println(date);
        System.out.println(nw);
        return service.fetchAllByParamsCodePart(part, number, date, nw);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateOne(@RequestBody ProductDto dto) {
        return service.updateProduct(dto);
    }
}
