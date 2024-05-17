package com.anhtester.reports;

import io.qameta.allure.Attachment;

public class AllureManager {
    //Add text to report
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }
}