package com.example.digitalvideostoreapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;;

@Document("movies")

public class MovieModel {
    @Id
    private String id;
    private String title;
    private String synopsis;
    private String category;
    private Boolean featured;
    private String small_poster;
    private String large_poster;
    private double rent_price;
    private double purchase_price;


    public MovieModel(){

    }

    public MovieModel(String id, String title, String synopsis, String category, Boolean featured, String small_poster, String large_poster, double rent_price, double purchase_price) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.category = category;
        this.featured = featured;
        this.small_poster = small_poster;
        this.large_poster = large_poster;
        this.rent_price = rent_price;
        this.purchase_price = purchase_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getSmall_poster() {
        return small_poster;
    }

    public void setSmall_poster(String small_poster) {
        this.small_poster = small_poster;
    }

    public String getLarge_poster() {
        return large_poster;
    }

    public void setLarge_poster(String large_poster) {
        this.large_poster = large_poster;
    }

    public double getRent_price() {
        return rent_price;
    }

    public void setRent_price(double rent_price) {
        this.rent_price = rent_price;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", category='" + category + '\'' +
                ", featured=" + featured +
                ", small_poster='" + small_poster + '\'' +
                ", large_poster='" + large_poster + '\'' +
                ", rent_price=" + rent_price +
                ", purchase_price=" + purchase_price +
                '}';
    }
}
