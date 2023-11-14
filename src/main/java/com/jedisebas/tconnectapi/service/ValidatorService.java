package com.jedisebas.tconnectapi.service;

import com.jedisebas.tconnectapi.constants.ProductConst;
import com.jedisebas.tconnectapi.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidatorService {

    public void validateProductDtoFields(ProductDto dto) {
        checkIfNull(dto);
        checkIfNull(dto.getCode());
        checkIfNullOrEmpty(dto.getName());
        checkIfNull(dto.getNW());
        checkIfNullOrEmpty(dto.getWN());
        checkIfNull(dto.getNumberT());
        checkIfNull(dto.getDateTime());

        checkLength(ProductConst.CODE_LENGTH, dto.getCode());
        checkLength(ProductConst.NAME_LENGTH, dto.getName());
        checkLength(ProductConst.NW_LENGTH, dto.getNW());
        checkLength(ProductConst.WN_LENGTH, dto.getWN());

        if (notNull(dto.getNumberT())) {
            checkLength(ProductConst.NUMBER_T_LENGTH, dto.getNumberT());
        }

        if (notNull(dto.getDateTime())) {
            checkDateTimeFormat(dto.getDateTime().toString());
        }
    }

    private void checkIfNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkIfNullOrEmpty(String... strings) {
        for (final String string : strings) {
            if (string == null || string.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkLength(int length, Long number) {
        checkLength(length, number.toString());
    }

    private void checkLength(int length, Integer number) {
        checkLength(length, number.toString());
    }

    private void checkLength(int length, String string) {
        if (string.length() > length) {
            throw new IllegalArgumentException();
        }
    }

    private boolean notNull(Object object) {
        return object != null;
    }

    void checkDateTimeFormat(CharSequence dateTime) {
        String regex = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(dateTime).matches()) {
            throw new IllegalArgumentException();
        }
    }
}
