package com.example.miles.gwacalculator;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

public class Values implements Parcelable{
    ArrayList<String> units = new ArrayList<String>(Arrays.asList(
            "1.00",
            "2.00",
            "3.00",
            "4.00",
            "5.00"
    ));
    ArrayList<String> grades = new ArrayList<String>(Arrays.asList(
            "1.00",
            "1.25",
            "1.50",
            "1.75",
            "2.00",
            "2.25",
            "2.50",
            "2.75",
            "3.00",
            "4.00",
            "5.00"
    ));

    String select_unit = "";
    String select_grade = "";

    public Values(){

    }

    public Values(Parcel in){
        this.units = in.createStringArrayList();
        this.grades = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Values> CREATOR = new Parcelable.Creator<Values>(){

        @Override
        public Values createFromParcel(Parcel source){
            return new Values(source);
        }

        @Override
        public Values[] newArray(int size){
            return new Values[size];
        }
    };

    public ArrayList<String> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<String> units) {
        this.units = units;
    }

    public ArrayList<String> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<String> grades) {
        this.grades = grades;
    }

    public String getSelect_unit() {
        return select_unit;
    }

    public void setSelect_unit(String select_unit) {
        this.select_unit = select_unit;
    }

    public String getSelect_grade() {
        return select_grade;
    }

    public void setSelect_grade(String select_grade) {
        this.select_grade = select_grade;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(units);
        dest.writeStringList(grades);
    }
}
