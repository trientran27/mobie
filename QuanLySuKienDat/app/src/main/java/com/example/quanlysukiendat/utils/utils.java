package com.example.quanlysukiendat.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class utils {
    public static final String baseUrl = "http://192.168.0.109:8081/";

    public static String convertToCurrency(int amount) {
        // Chuyển đổi số tiền sang chuỗi định dạng tiền tệ
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatter.format(amount);
    }
}
