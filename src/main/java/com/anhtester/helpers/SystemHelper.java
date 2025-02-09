package com.anhtester.helpers;

import java.io.File;
import java.text.Normalizer;

public class SystemHelper {
    public static String getCurrentDir() {
        return System.getProperty("user.dir") + File.separator;
    }

    public static String removeAccent(String text) {
        // Chuẩn hóa chuỗi thành dạng Unicode tổ hợp (NFD)
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        // Loại bỏ các ký tự dấu (dấu thanh, dấu móc, ...)
        String accentRemoved = normalized.replaceAll("\\p{M}", "");
        return accentRemoved;
    }

}
