package com.example.miles.gwacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Values> values = new ArrayList<Values>();
    ArrayList<Integer> pos_unit = new ArrayList<Integer>();
    ArrayList<Integer> pos_grade = new ArrayList<Integer>();
    CustomList adapter;
    Button compute, add, remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Values v = new Values();
        values.add(v);

        adapter = new CustomList(this, values);
        ListView list = (ListView) findViewById(R.id.subjects);
        list.setAdapter(adapter);

        add = (Button) findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(values.size() < 8){
                    addSubject();
                }else{
                    Toast.makeText(MainActivity.this, "Cannot add any more subjects!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        remove = (Button) findViewById(R.id.btn_remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(values.size() > 0){
                    removeSubject();
                }else{
                    Toast.makeText(MainActivity.this, "No subjects to remove!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        compute = (Button) findViewById(R.id.btn_compute);
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computeGWA();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Sorry, doesn't do anything yet!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addSubject(){
        savePositions();
        Values v = new Values();
        values.add(v);

        adapter.notifyDataSetChanged();
        loadPositions();
    }

    public void removeSubject(){
        savePositions();
        values.remove(values.size() - 1);

        adapter.notifyDataSetChanged();
        loadPositions();
    }

    public void savePositions(){
        pos_unit.clear();
        pos_grade.clear();

        View view;
        ListView list = (ListView) findViewById(R.id.subjects);
        for(int i=0; i<values.size(); i++){
            view = list.getChildAt(i);
            Spinner spin_unit = (Spinner) view.findViewById(R.id.units);
            Spinner spin_grade = (Spinner) view.findViewById(R.id.grade);

            pos_unit.add(spin_unit.getSelectedItemPosition());
            pos_grade.add(spin_grade.getSelectedItemPosition());
        }
    }

    public void loadPositions(){
        View view;
        ListView list = (ListView) findViewById(R.id.subjects);
        for(int i=0; i<pos_unit.size(); i++){
            view = list.getChildAt(i);

            Spinner spin_unit = (Spinner) view.findViewById(R.id.units);
            Spinner spin_grade = (Spinner) view.findViewById(R.id.grade);

            /*
            ArrayAdapter<String> ad_u = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, adapter.getValues().get(i).getUnits());
            ArrayAdapter<String> ad_g = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, adapter.getValues().get(i).getGrades());
            spin_unit.setAdapter(ad_u);
            spin_grade.setAdapter(ad_g);
            adapter.getValues().get(i).getUnits().add(0, adapter.getValues().get(i).getUnits().get(pos_unit.get(i)));
            adapter.getValues().get(i).getGrades().add(0, adapter.getValues().get(i).getGrades().get(pos_grade.get(i)));
            */

            spin_unit.setSelection(pos_unit.get(i), true);
            spin_grade.setSelection(pos_grade.get(i), true);

            /*
            ad_u.notifyDataSetChanged();
            ad_g.notifyDataSetChanged();
            */
        }

    }

    public void computeGWA(){
        View v;
        float units=0, sum=0, gwa;
        ListView list = (ListView) findViewById(R.id.subjects);
        TextView text = (TextView) findViewById(R.id.gwa);

        for(int i=0; i<values.size(); i++){
            v = list.getChildAt(i);
            Spinner spin_unit = (Spinner) v.findViewById(R.id.units);
            Spinner spin_grade = (Spinner) v.findViewById(R.id.grade);
            units += Float.parseFloat(spin_unit.getSelectedItem().toString());
            sum += (Float.parseFloat(spin_grade.getSelectedItem().toString())) * (Float.parseFloat(spin_unit.getSelectedItem().toString()));
        }
        gwa = sum/units;
        String actualGWA = String.valueOf(gwa);
        text.setText(actualGWA);
    }
}
