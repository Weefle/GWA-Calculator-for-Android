package com.example.miles.gwacalculator;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomList extends BaseAdapter {
    private final Activity context;
    private final ArrayList<Values> values;
    public CustomList(Activity context,
                      ArrayList<Values> values) {
        this.context = context;
        this.values = values;

    }

    public Activity getContext() {
        return context;
    }

    public ArrayList<Values> getValues() {

        return values;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //LayoutInflater inflater = context.getLayoutInflater();
        //View rowView= inflater.inflate(R.layout.list, null, true);
        View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, null);

        Spinner spin_units = (Spinner) retval.findViewById(R.id.units);
        Spinner spin_grade = (Spinner) retval.findViewById(R.id.grade);

        ArrayList<String> units = values.get(position).getUnits();
        ArrayList<String> grades = values.get(position).getGrades();

        ArrayAdapter<String> ad_units = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, units);
        spin_units.setAdapter(ad_units);
        ArrayAdapter<String> ad_grades = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, grades);
        spin_grade.setAdapter(ad_grades);

        return retval;
    }

    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public Object getItem(int pos){
        return values.get(pos);
    }

    @Override
    public long getItemId(int pos){
        return pos;
    }

}
