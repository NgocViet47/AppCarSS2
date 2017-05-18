package com.example.mypc.appcarss2.object;

/**
 * Created by MyPC on 5/3/2017.
 */

public class ItemPost {
    private String title;
    private int price;
    private int idCity;
    private int idMaker;
    private int idSpecie;
    private int idGear;
    private String idAccount;
    private String linkImageFirst;
    private int idAge;
    private String inFormation;
    private String dateTime;

    public ItemPost(String title, int price, int idCity, int idMaker, int idSpecie, int idGear, String idAccount, String linkImageFirst, int idAge, String inFormation, String dateTime) {
        this.title = title;
        this.price = price;
        this.idCity = idCity;
        this.idMaker = idMaker;
        this.idSpecie = idSpecie;
        this.idGear = idGear;
        this.idAccount = idAccount;
        this.linkImageFirst = linkImageFirst;
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

    public ItemPost() {
    }


    public String getLinkImageFirst() {
        return linkImageFirst;
    }

    public void setLinkImageFirst(String linkImageFirst) {
        this.linkImageFirst = linkImageFirst;
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
}
