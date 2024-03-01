package com.anhtester.globals;

import com.anhtester.helpers.PropertiesHelper;

public class ConfigsGlobal {
    public static String URI = PropertiesHelper.getValue("BASE_URI");
    public static String USERNAME = PropertiesHelper.getValue("USERNAME");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static int TCS_TOTAL;
    public static int PASSED_TOTAL;
    public static int FAILED_TOTAL;
}
