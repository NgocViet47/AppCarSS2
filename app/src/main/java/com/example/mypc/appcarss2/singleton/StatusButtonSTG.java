package com.example.mypc.appcarss2.singleton;

/**
 * Created by MyPC on 4/27/2017.
 */

public class StatusButtonSTG {
    private static StatusButtonSTG instance = new StatusButtonSTG();

    private boolean btnLogin = false;

    public boolean isBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(boolean btnLogin) {
        this.btnLogin = btnLogin;
    }


    private StatusButtonSTG(){}
    public static StatusButtonSTG getInstance() {
        if (instance == null)
            instance = new StatusButtonSTG();
        return instance;
    }
}
