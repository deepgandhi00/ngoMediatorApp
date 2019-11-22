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

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.EventViewHolder> {
    private List<EventModel> list;
    private Context context;

    public EventsRecyclerAdapter(List<EventModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.events_item_view,parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getTitle()).into(holder.eventLogo);
        holder.eventName.setText(list.get(position).getTitle());
        holder.eventDateTime.setText(list.get(position).getEvent_on().toString());
        holder.eventLocation.setText(list.get(position).getTitle());
        holder.eventDescription.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{
        private TextView eventName,eventLocation,eventDateTime,eventDescription;
        private ImageView eventLogo;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            eventLogo = itemView.findViewById(R.id.eventLogo);
            eventName = itemView.findViewById(R.id.eventName);
            eventLocation = itemView.findViewById(R.id.eventLocation);
            eventDateTime = itemView.findViewById(R.id.eventDateTime);
            eventDescription = itemView.findViewById(R.id.eventDescription);
        }
    }
}
