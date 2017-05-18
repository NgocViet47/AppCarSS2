package com.example.mypc.appcarss2.singleton;

import com.example.mypc.appcarss2.object.User;

/**
 * Created by MyPC on 4/26/2017.
 */

public class DataSGT {
    private static DataSGT instance = new DataSGT();

    //User
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //User item post
    private User userITP;

    public User getUserITP() {
        return userITP;
    }

    public void setUserITP(User userITP) {
        this.userITP = userITP;
    }

    //SGT
    private DataSGT(){}

    public static DataSGT getInstance() {
        if (instance == null)
            instance = new DataSGT();
        return instance;
    }
}
