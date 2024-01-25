package com.anhtester.model;

import java.util.ArrayList;

public class BookPOJO {
    private String name;
    private int category_id;
    private int price; //2 tỷ mấy
    private String release_date;
    private ArrayList<Integer> image_ids;
    private Boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public ArrayList<Integer> getImage_ids() {
        return image_ids;
    }

    public void setImage_ids(ArrayList<Integer> image_ids) {
        this.image_ids = image_ids;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
