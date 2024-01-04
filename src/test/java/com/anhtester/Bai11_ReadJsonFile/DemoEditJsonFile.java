package com.anhtester.Bai11_ReadJsonFile;

import com.anhtester.helpers.JsonHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DemoEditJsonFile {

    @Test
    public void testUpdateDataJson() {
        JsonHelper.updateValueJsonFile("src/test/resources/testdata/TestJsonFile01.json", "totalprice", 333);
        JsonHelper.updateValueJsonFile("src/test/resources/testdata/TestJsonFile01.json", "depositpaid", false);
    }

    @Test
    public void testUpdateValueInJson() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty("additionalneeds", "Update New Value");

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile01.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateValueInJson02() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            // Cập nhật giá trị của trường "checkout"
            jsonObject.getAsJsonObject("bookingdates").addProperty("checkout", "2024-04-10");

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to old file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile01.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateValueInJson03_ArrayObject() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile03.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to JsonArray
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);

            System.out.println("Original JSON: " + jsonArray);

            // Cập nhật giá trị của trường "lastname" trong phần tử đầu tiên
            if (jsonArray.size() > 0) {
                //Lấy vị trí object thứ nhất
                JsonObject firstObject = jsonArray.get(0).getAsJsonObject();
                firstObject.addProperty("lastname", "NewLastName");
            }

            System.out.println("Modified JSON: " + jsonArray);

            File jsonFile = new File("src/test/resources/testdata/TestJsonFile03.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            //Lưu đối tượng jsonArray chứ không phải jsonObject
            outputStream.write(gson.toJson(jsonArray).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddNewPropertyInJson() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Create array value
            JsonArray objectArray = new JsonArray();
            objectArray.add("Support");
            objectArray.add("ERP");

            //Add array value to key "department"
            jsonObject.add("department", objectArray);

            //Add simple key:value
            jsonObject.addProperty("key1", "Value for Key1");

            //Add key:{object}
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("name", "Anh Tester");
            objectMap.put("age", 27);
            JsonElement jsonElement = gson.toJsonTree(objectMap);
            jsonObject.add("student", jsonElement);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to old file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile01Edited.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRemovePropertyInJson() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile02.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Xoá key ngoài cùng có tên là "age"
            jsonObject.remove("age");

            //Xác định vị trí của property cần xoá
            JsonObject positionObject = jsonObject
                    .get("department").getAsJsonObject();

            //Xoá key "years" trong cấu trúc propert
            positionObject.remove("name");

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile02Edited.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}