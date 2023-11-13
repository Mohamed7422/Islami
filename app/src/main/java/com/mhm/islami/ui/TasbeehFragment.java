package com.mhm.islami.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

 
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mhm.islami.R;

import java.util.ArrayList;
import java.util.List;


public class TasbeehFragment extends Fragment  {



    public TasbeehFragment() {
        // Required empty public constructor
    }



    int counter =0;
    int sumCounter =0;


    TextView tasbeehNumber;
    TextView tasbeehTotal;
    Spinner douaaspinner;
    ImageView reset;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tasbeeh, container, false);
        ImageView tasbihImV =  (ImageView) view.findViewById(R.id.sebhaic);
        tasbeehNumber= (TextView) view.findViewById(R.id.taseehaNumber);
        tasbeehTotal = (TextView) view.findViewById(R.id.total);
        tasbeehTotal.setText(0+"");
        douaaspinner = (Spinner) view.findViewById(R.id.douaaspinner);
        reset = (ImageView) view.findViewById(R.id.reset);


        //save the current state of the counter
        if (savedInstanceState!=null){
            counter = savedInstanceState.getInt("counter", 0);
            sumCounter = savedInstanceState.getInt("sumCounter", 0);
        }else{
            counter = 0;
            sumCounter= 0;
        }


        tasbihImV.setOnClickListener(onClickListener);
       douaaspinner.setOnItemSelectedListener(onItemSelectedListener);

       //Create a list of values for Spinner
        List<Zekr> zekrList= new ArrayList<>();
        String[]  zekrNameList = {"الله أكبر", "سبحان الله", "الحمد لله", "لا اله اللا الله"};
        zekrList.add(new Zekr(1,zekrNameList[0],0));
        zekrList.add(new Zekr(2,zekrNameList[1],0));
        zekrList.add(new Zekr(3,zekrNameList[2],0));
        zekrList.add(new Zekr(4,zekrNameList[3],0));

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1
                                                                      ,zekrNameList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        douaaspinner.setAdapter(spinnerAdapter);

       reset.setOnClickListener(onResetClickListener);

        return view;

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            counter++;
            sumCounter++;

            tasbeehNumber.setText(counter+"");
            tasbeehTotal.setText(sumCounter+"");



            }
    };

    AdapterView.OnItemSelectedListener onItemSelectedListener =  new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            if (counter==0){
                tasbeehNumber.setText(counter+"");

            }else {
                counter=0;
                tasbeehNumber.setText(counter+"");

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    View.OnClickListener onResetClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sumCounter=0;
            counter=0;
         tasbeehNumber.setText(0+"");
         tasbeehTotal.setText(sumCounter+"");
        }
    };

    public static class Zekr {

        int id;
        String text;
        int counter;
        public Zekr(int id,String text, int counter) {
            this.id=id;
            this.text= text;
            this.counter=counter;
        }
    }




    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter",counter);
        outState.putInt("sumCounter",sumCounter);
    }
}