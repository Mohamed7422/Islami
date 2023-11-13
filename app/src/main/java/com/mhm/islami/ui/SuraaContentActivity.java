package com.mhm.islami.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.mhm.islami.Constants;
import com.mhm.islami.R;
import com.mhm.islami.adapters.VersesRecyclerAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SuraaContentActivity extends AppCompatActivity {

    int pos;
    String name;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    VersesRecyclerAdapter adapter;
    TextView suratitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setContentView(R.layout.activity_suraa_content);
        recyclerView = findViewById(R.id.recycler);
        suratitle = findViewById(R.id.sura_name);

        layoutManager = new LinearLayoutManager(this);

        pos = getIntent().getIntExtra(Constants.EXTRA_POSITION,-1);
        name = getIntent().getStringExtra(Constants.EXTRA_TITLE);
        suratitle.setText(name);
        List<String> content = readSuraFromFile((pos + 1) + ".txt");

            adapter = new VersesRecyclerAdapter(content);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
    }

    public List<String> readSuraFromFile(String fileName){
        List<String> data = new ArrayList<>();

        BufferedReader reader = null;
         try {
             reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));
         //do reading, usually loop until end of file reading
             String mline;
             while ((mline = reader.readLine())!=null){
                 //add the line to the list
                 data.add(mline);
             }
         }
          catch (IOException e) {
             e.printStackTrace();
         } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
         }
      return data;
    };
}