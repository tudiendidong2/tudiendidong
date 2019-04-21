package com.example.noce30.cardview;

public class Food {
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public Food(int id, String title, String desc, double rating, double price, int image) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }

    private int id;
    private String title, desc;
    private double rating;
    private double price;
    private int image;
}
