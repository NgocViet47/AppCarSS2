package com.example.mypc.appcarss2.object;

/**
 * Created by MyPC on 5/8/2017.
 */

public class ItempostLoadKey {
    private String title;
    private int price;
    private int idCity;
    private int idMaker;
    private int idSpecie;
    private int idGear;
    private String idAccount;
    private String idKeyPost;
    private String linkFirstImage;
    private int idAge;
    private String inFormation;
    private String dateTime;

    public ItempostLoadKey(String title, int price, int idCity, int idMaker, int idSpecie, int idGear, String idAccount, String idKeyPost, String linkFirstImage, int idAge, String inFormation, String dateTime) {
        this.title = title;
        this.price = price;
        this.idCity = idCity;
        this.idMaker = idMaker;
        this.idSpecie = idSpecie;
        this.idGear = idGear;
        this.idAccount = idAccount;
        this.idKeyPost = idKeyPost;
        this.linkFirstImage = linkFirstImage;
        this.idAge = idAge;
        this.inFormation = inFormation;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ItempostLoadKey() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public int getIdMaker() {
        return idMaker;
    }

    public void setIdMaker(int idMaker) {
        this.idMaker = idMaker;
    }

    public int getIdSpecie() {
        return idSpecie;
    }

    public void setIdSpecie(int idSpecie) {
        this.idSpecie = idSpecie;
    }

    public int getIdGear() {
        return idGear;
    }

    public void setIdGear(int idGear) {
        this.idGear = idGear;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getIdKeyPost() {
        return idKeyPost;
    }

    public void setIdKeyPost(String idKeyPost) {
        this.idKeyPost = idKeyPost;
    }

    public int getIdAge() {
        return idAge;
    }

    public void setIdAge(int idAge) {
        this.idAge = idAge;
    }

    public String getInFormation() {
        return inFormation;
    }

    public void setInFormation(String inFormation) {
        this.inFormation = inFormation;
    }

    public String getLinkFirstImage() {
        return linkFirstImage;
    }

    public void setLinkFirstImage(String linkFirstImage) {
        this.linkFirstImage = linkFirstImage;
    }
}
