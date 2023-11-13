package com.mhm.islami.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mhm.islami.R;

import java.util.List;

public class VersesRecyclerAdapter extends RecyclerView.Adapter<VersesRecyclerAdapter.ViewHolder> {

    List<String>data;
    public VersesRecyclerAdapter(List<String> data) {
        this.data = data;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_verse, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String name = data.get(position);
        holder.content.setText(name);

    }



    @Override
    public int getItemCount() {
        if (data==null)return 0;
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content= itemView.findViewById(R.id.content);

        }
    }


}
