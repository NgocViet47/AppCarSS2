package com.example.mypc.appcarss2.object;

/**
 * Created by MyPC on 5/10/2017.
 */

public class UserCommentPost {
    private String idAccount;
    private String fullName;
    private String comment;
    private String time;

    public UserCommentPost(String idAccount, String fullName, String comment, String time) {
        this.idAccount = idAccount;
        this.fullName = fullName;
        this.comment = comment;
        this.time = time;
    }

    public UserCommentPost() {
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
