package com.prakash.infosysassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

/* Model for row item. Implements Parcelable to be on safer side if we need to
 * transfer data between different components */

public class AboutCanadaObject implements Parcelable {

    private String title = "";
    private String description = "";
    private String imageHref = "";

    public AboutCanadaObject() {
    }

    public AboutCanadaObject(String title, String description, String imageHref) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    protected AboutCanadaObject(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageHref = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageHref);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AboutCanadaObject> CREATOR = new Creator<AboutCanadaObject>() {
        @Override
        public AboutCanadaObject createFromParcel(Parcel in) {
            return new AboutCanadaObject(in);
        }

        @Override
        public AboutCanadaObject[] newArray(int size) {
            return new AboutCanadaObject[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
