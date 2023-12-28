package com.anhtester.Bai10_ReadPropertiesFile;

import com.anhtester.helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class DemoReadProperties {
    @Test
    public void testReadFile() {
        //Gọi hàm loadAllFiles trước tiên để load tất cả các file properties vào chung bộ nhớ
        PropertiesHelper.loadAllFiles();

        //Sau đó gọi hàm getValue để lấy giá trị theo tên key
        System.out.println("URI: " + PropertiesHelper.getValue("URI"));
        System.out.println("USERNAME: " + PropertiesHelper.getValue("USERNAME"));
        System.out.println("PASSWORD: " + PropertiesHelper.getValue("PASSWORD"));
    }

    @Test
    public void testWriteValue() {
        //Trước tiên chỉ định file cần set giá trị vào
        //Dùng đường dẫn tương đối
        //Ví dụ file configs.properties

        PropertiesHelper.setFile("src/test/resources/config/demo.properties");

        //Gọi hàm setValue để gán giá trị theo key
        PropertiesHelper.setValue("author", "Anh Tester");
        PropertiesHelper.setValue("project", "API Automation");
    }
}
