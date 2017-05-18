package com.example.mypc.appcarss2.object;

import android.net.Uri;

/**
 * Created by MyPC on 5/3/2017.
 */

public class ImgPost {
    private Uri uri;
    private boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
