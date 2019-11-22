package com.example.deepgandhi.ngoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deepgandhi.ngoapp.R;
import com.example.deepgandhi.ngoapp.models.Response.EventModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NGOdetailEventAdapter extends RecyclerView.Adapter<NGOdetailEventAdapter.NGODetailEventViewHolder>{
    Context context;
    List<EventModel> list;

    @NonNull
    @Override
    public NGODetailEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.events_recycler_items,parent,false);

        return new NGODetailEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NGODetailEventViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NGODetailEventViewHolder extends RecyclerView.ViewHolder{
        private ImageView eventImage;
        private TextView eventName;
        private TextView eventDescription;
        private TextView eventVolunteer;
        private TextView eventDonation;


        public NGODetailEventViewHolder(@NonNull View itemView) {
            super(itemView);

            eventImage = itemView.findViewById(R.id.eventImage);
            eventName = itemView.findViewById(R.id.eventName);
            eventDescription = itemView.findViewById(R.id.eventDescription);
            eventVolunteer = itemView.findViewById(R.id.eventVolunteer);
            eventDonation = itemView.findViewById(R.id.eventDonation);
        }
    }
}
