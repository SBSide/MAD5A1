package com.example.iveci.mad5;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by iveci on 2017-04-06.
 */

public class Rest implements Parcelable {
    String name = "";
    String tel = "";
    ArrayList<String> menu = new ArrayList<>(3);
    String homepage = "";
    String date = "";
    int catnumber = 0;

    protected Rest(Parcel in) {
        name = in.readString();
        tel = in.readString();
        menu = in.createStringArrayList();
        homepage = in.readString();
        date = in.readString();
        catnumber = in.readInt();
    }

    public static final Creator<Rest> CREATOR = new Creator<Rest>() {
        @Override
        public Rest createFromParcel(Parcel in) {
            return new Rest(in);
        }

        @Override
        public Rest[] newArray(int size) {
            return new Rest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(tel);
        dest.writeStringList(menu);
        dest.writeString(homepage);
        dest.writeString(date);
        dest.writeInt(catnumber);
    }

    @Override
    public String toString() {
        return name;
    }
}
