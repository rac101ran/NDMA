package com.example.disastermanagementfinal;

public class Alldetailsinfo  {
    String title;
    String description;
    int images;

    public Alldetailsinfo(String title, String description, int images) {
        this.title = title;
        this.description = description;
        this.images = images;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImages() {
        return images;
    }
}
