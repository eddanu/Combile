package com.android.herry.combile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> eventList;
    private Context context;
    FirebaseDataListener listener;


    public EventAdapter() {
        eventList = new ArrayList<>();
       // eventList=events;
        //context=ctx;
        // listener=(fragment_home)ctx ;
    }

    private void add(Event item) {
        eventList.add(item);
        notifyItemInserted(eventList.size() - 1);
    }

    public void addAll(List<Event> eventList) {
        for (Event event : eventList) {
            add(event);
        }
    }

    public void remove(Event item) {
        int position = eventList.indexOf(item);
        if (position > -1) {
            eventList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Event getItem(int position) {
        return eventList.get(position);
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list, parent, false);
        EventViewHolder memberViewHolder = new EventViewHolder(view);
        return memberViewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        final Event event = eventList.get(position);

        holder.EventPic.setImageResource(event.getPic());
        holder.EventTitle.setText(event.getTitle());
        holder.EventDetail.setText(event.getDetail());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {

        ImageView EventPic;
        TextView EventTitle;
        TextView EventDetail;

        public EventViewHolder(View itemView) {
            super(itemView);

            EventPic = (ImageView) itemView.findViewById(R.id.Event_pic);
            EventTitle = (TextView) itemView.findViewById(R.id.Event_Title);
            EventDetail = (TextView) itemView.findViewById(R.id.Event_Detail);
        }
    }

    public interface FirebaseDataListener {
        void onDeleteData(Event event, int position);
    }
}
