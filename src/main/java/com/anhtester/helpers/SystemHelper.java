package com.anhtester.helpers;

import java.io.File;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class SystemHelper {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String makeSlug(String input) {
        if (input == null)
            throw new IllegalArgumentException();

        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("_");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

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

    public static void createFolder(String path) {
        // File is a class inside java.io package
        File file = new File(path);

        String result = null;

        int lengthSum = path.length();
        int lengthSub = path.substring(0, path.lastIndexOf('/')).length();

        result = path.substring(lengthSub, lengthSum);

        if (!file.exists()) {
            file.mkdir();  // mkdir is used to create folder
            System.out.println("Folder " + file.getName() + " created: " + path);
        } else {
            System.out.println("Folder already created");
        }
    }

    public static ArrayList<String> splitString(String str, String valueSplit) {
        ArrayList<String> arrayListString = new ArrayList<>();
        for (String s : str.split(valueSplit, 0)) {
            arrayListString.add(s);
        }
        return arrayListString;
    }

}
