package com.example.deepgandhi.ngoapp.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deepgandhi.ngoapp.R;
import com.example.deepgandhi.ngoapp.models.Response.NGOModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NGORecyclerAdapter extends RecyclerView.Adapter<NGORecyclerAdapter.NGOViewHolder> {
    private List<NGOModel> list;
    private Context context;

    public NGORecyclerAdapter(List<NGOModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NGOViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nog_item_view,parent,false);
        return new NGOViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NGOViewHolder holder, int position) {
        holder.ngoName.setText(list.get(position).getName());
        holder.ngoLocation.setText(list.get(position).getAddress().toString());
        holder.ngoDescription.setText(list.get(position).getEmail());
        Glide.with(context).load(list.get(position).getId()).into(holder.ngoLogo);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NGOViewHolder extends RecyclerView.ViewHolder{
        private ImageView ngoLogo;
        private TextView ngoName,ngoLocation,ngoDescription;

        public NGOViewHolder(@NonNull View itemView) {
            super(itemView);

            ngoLogo = itemView.findViewById(R.id.ngoLogo);
            ngoName = itemView.findViewById(R.id.ngoName);
            ngoLocation = itemView.findViewById(R.id.ngoLocation);
            ngoDescription = itemView.findViewById(R.id.ngoDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
