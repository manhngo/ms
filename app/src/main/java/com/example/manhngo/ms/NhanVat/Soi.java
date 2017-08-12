package com.example.manhngo.ms.NhanVat;

import android.graphics.drawable.Drawable;

/**
 * Created by NgoXuanManh on 3/14/2017.
 */

public class Soi {
    String title;
    Drawable image;

    public Soi(String title, Drawable image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
