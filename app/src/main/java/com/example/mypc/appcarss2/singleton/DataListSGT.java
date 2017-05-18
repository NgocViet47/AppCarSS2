package com.example.mypc.appcarss2.singleton;

import com.example.mypc.appcarss2.object.Age;
import com.example.mypc.appcarss2.object.City;
import com.example.mypc.appcarss2.object.Gear;
import com.example.mypc.appcarss2.object.ItempostLoadKey;
import com.example.mypc.appcarss2.object.Maker;
import com.example.mypc.appcarss2.object.Specie;
import com.example.mypc.appcarss2.object.User;

import java.util.ArrayList;

/**
 * Created by MyPC on 5/4/2017.
 */

public class DataListSGT {
    private static DataListSGT instance = new DataListSGT();

    //list City
    private ArrayList<City> cityArrayList;

    public ArrayList<City> getCityArrayList() {
        return cityArrayList;
    }

    public void setCityArrayList(ArrayList<City> cityArrayList) {
        this.cityArrayList = cityArrayList;
    }
    //list Gear
    private ArrayList<Gear> gearArrayList;

    public ArrayList<Gear> getGearArrayList() {
        return gearArrayList;
    }

    public void setGearArrayList(ArrayList<Gear> gearArrayList) {
        this.gearArrayList = gearArrayList;
    }
    //list Age
    private ArrayList<Age> ageArrayList;

    public ArrayList<Age> getAgeArrayList() {
        return ageArrayList;
    }

    public void setAgeArrayList(ArrayList<Age> ageArrayList) {
        this.ageArrayList = ageArrayList;
    }
    //list Maker
    private ArrayList<Maker> makerArrayList;

    public ArrayList<Maker> getMakerArrayList() {
        return makerArrayList;
    }

    public void setMakerArrayList(ArrayList<Maker> makerArrayList) {
        this.makerArrayList = makerArrayList;
    }
    //list key Image
    private ArrayList<ItempostLoadKey> arrayLisItems ;

    public ArrayList<ItempostLoadKey> getArrayLisItems() {
        return arrayLisItems;
    }

    public void setArrayLisItems(ArrayList<ItempostLoadKey> arrayLisItems) {
        this.arrayLisItems = arrayLisItems;
    }
    //list Specie
    private ArrayList<Specie> specieArrayList;

    public ArrayList<Specie> getSpecieArrayList() {
        return specieArrayList;
    }

    public void setSpecieArrayList(ArrayList<Specie> specieArrayList) {
        this.specieArrayList = specieArrayList;
    }
    //List User
    private ArrayList<User> userArrayList;

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    private DataListSGT(){}

    public static DataListSGT getInstance() {
        if (instance == null)
            instance = new DataListSGT();
        return instance;
    }
}
