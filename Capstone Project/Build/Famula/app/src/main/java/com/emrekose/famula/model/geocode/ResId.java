package com.emrekose.famula.model.geocode;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResId implements Serializable {

    @SerializedName("res_id")
    private int resId;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

}
