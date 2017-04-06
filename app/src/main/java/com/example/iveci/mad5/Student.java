package com.example.iveci.mad5;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by iveci on 2017-04-06.
 */

public class Student implements Parcelable {
    String name = "";
    String hak = "";
    private int age = 0;
    private int isman = 1;

    public Student(String name, String hakno, int age, int isman) {
        this.name = name;
        this.hak = hakno;
        this.age = age;
        this.isman = isman;
    }

    protected Student(Parcel in) {
        name = in.readString();
        hak = in.readString();
        age = in.readInt();
        isman = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public void setStudent(String name, String hakno, int age, int isman){
        this.name = name;
        this.hak = hakno;
        this.age = age;
        this.isman = isman;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(hak);
        dest.writeInt(age);
        dest.writeInt(isman);
    }

    @Override
    public String toString() {
        return name + "," + hak + "(" + age + ")" + (isman == 1 ? "MAN" : "WOMAN");
    }
}
