package com.jedisebas.tconnectapi.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConst {
    public static final String TABLE = "produkty";
    public static final String ID = "id";
    public static final String CODE = "Kod";
    public static final String NAME = "Nazwa";
    public static final String NW = "N_W";
    public static final String WN = "W_N";
    public static final String NUMBER_T = "NumerT";
    public static final String DATETIME = "Data Godzina Z";

    public static final int CODE_LENGTH = 13;
    public static final int NAME_LENGTH = 50;
    public static final int NW_LENGTH = 5;
    public static final int WN_LENGTH = 3;
    public static final int NUMBER_T_LENGTH = 11;
}
