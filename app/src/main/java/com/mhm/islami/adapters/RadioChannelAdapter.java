package com.mhm.islami.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mhm.islami.R;
import com.mhm.islami.model.RadiosItem;

import java.util.List;

public class RadioChannelAdapter extends RecyclerView.Adapter<RadioChannelAdapter.ViewHolder> {

    List<RadiosItem> channels;

    public RadioChannelAdapter(List<RadiosItem> channels) {
        this.channels = channels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.item_radio_channel,parent,false);
       return new ViewHolder(view);

    }

    public void changeData(List<RadiosItem> radiosItems){
        this.channels = radiosItems;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RadiosItem item = channels.get(position);
        holder.name.setText(item.getName());

        if (onPlayClickListener!=null)
            holder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPlayClickListener.onItemClick(position,item);
                }
            });
        if (onStopClickListener!=null)
            holder.stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStopClickListener.onItemClick(position,item);
                }
            });




    }
    public OnItemClickListener onPlayClickListener;
    public OnItemClickListener onStopClickListener;

    public void setOnStopClickListener (OnItemClickListener onStopClickListener) {
        this.onStopClickListener = onStopClickListener;
    }
    public void setOnPlayClickListener(OnItemClickListener onPlayClickListener) {
        this.onPlayClickListener = onPlayClickListener;
    }



    public interface OnItemClickListener{
        void onItemClick(int pos, RadiosItem item);
    }

    @Override
    public int getItemCount() {
        //ternary Operator
        return  channels ==null?0:channels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView play,stop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.radio_name);
            play = itemView.findViewById(R.id.play);
            stop= itemView.findViewById(R.id.stop);



        }
    }
}
