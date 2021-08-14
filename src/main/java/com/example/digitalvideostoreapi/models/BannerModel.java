package com.example.digitalvideostoreapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;;

@Document("banners")
public class BannerModel {
    @Id
    private String id;
    private String image;

    public BannerModel() {
    }

    public BannerModel(String id, String image) {
        this.id = id;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BannerModel{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
