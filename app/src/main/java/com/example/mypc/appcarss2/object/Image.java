package com.example.mypc.appcarss2.object;

/**
 * Created by MyPC on 5/8/2017.
 */

public class Image {
    private String link;
    private boolean checked = false;

    public Image(String link, boolean checked) {
        this.link = link;
        this.checked = checked;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Image() {

    }
}
