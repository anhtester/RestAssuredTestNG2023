package com.anhtester.globals;

import com.anhtester.helpers.PropertiesHelper;

public class ConfigsGlobal {
    public static String AUTHOR = PropertiesHelper.getValue("AUTHOR");
    public static String BASE_URI = PropertiesHelper.getValue("BASE_URI");
    public static String BASE_PATH = PropertiesHelper.getValue("BASE_PATH");
    public static String USERNAME = PropertiesHelper.getValue("USERNAME");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static int TCS_TOTAL;
    public static int PASSED_TOTAL;
    public static int FAILED_TOTAL;
}
