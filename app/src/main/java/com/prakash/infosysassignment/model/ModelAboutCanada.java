package com.prakash.infosysassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/* Model for About Canada json data. Implements Parcelable to be on safer side if we need to
 * transfer data between different components */

public class ModelAboutCanada implements Parcelable {

    private String title = "";
    private List<AboutCanadaObject> rows = new ArrayList<>();

    public ModelAboutCanada() {
    }

    public ModelAboutCanada(String title, List<AboutCanadaObject> rows) {
        this.title = title;
        this.rows = rows;
    }

    protected ModelAboutCanada(Parcel in) {
        title = in.readString();
        rows = in.createTypedArrayList(AboutCanadaObject.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeTypedList(rows);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelAboutCanada> CREATOR = new Creator<ModelAboutCanada>() {
        @Override
        public ModelAboutCanada createFromParcel(Parcel in) {
            return new ModelAboutCanada(in);
        }

        @Override
        public ModelAboutCanada[] newArray(int size) {
            return new ModelAboutCanada[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AboutCanadaObject> getRows() {
        return rows;
    }

    public void setRows(List<AboutCanadaObject> rows) {
        this.rows = rows;
    }
}
