package com.mhm.islami.ui;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mhm.islami.API.APIManager;
import com.mhm.islami.R;
import com.mhm.islami.adapters.RadioChannelAdapter;
import com.mhm.islami.model.RadioResponse;
import com.mhm.islami.model.RadiosItem;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RadioFragment extends Fragment {



    public RadioFragment() {
        // Required empty public constructor
    }

     RecyclerView recyclerView;
     ProgressBar progressBar;
     RadioChannelAdapter adapter;
     RecyclerView.LayoutManager layoutManager;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout   for this fragment
        return inflater.inflate(R.layout.fragment_radio, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        getRadioChannels();
        initRecyclerView();


    }

    private void initRecyclerView() {
        adapter = new RadioChannelAdapter(null);
        adapter.setOnPlayClickListener(new RadioChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem item) {
                    playRadioChannel(item.getRadioUrl());
            }
        });

        adapter.setOnStopClickListener(new RadioChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem item) {
                 stopRadio();
            }
        });

        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
    }

    MediaPlayer mediaPlayer;
    public void stopRadio(){
        if (mediaPlayer !=null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer=null;
        }

    }


    public void playRadioChannel(String url){
       stopRadio();
       mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(getContext(),Uri.parse(url));
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getRadioChannels() {
        APIManager.getAPIs().getRadioChannel()
                .enqueue(new Callback<RadioResponse>() {
                    @Override
                    public void onResponse(Call<RadioResponse> call, Response<RadioResponse> response) {
                        progressBar.setVisibility(View.GONE);
                        adapter.changeData(response.body().getRadios());



                    }

                    @Override
                    public void onFailure(Call<RadioResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}