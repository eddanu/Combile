package com.android.herry.combile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;



public class FragmentHome extends Fragment {
    private RecyclerView listevent;
    private LinearLayoutManager linearLayoutManager;
    private EventAdapter eventListAdapter;
    protected Context context;

    public static FragmentHome newInstance(){
        return new FragmentHome();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        listevent = (RecyclerView) rootView.findViewById(R.id.listEvent);

        return rootView;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        linearLayoutManager = new LinearLayoutManager(context);
        eventListAdapter = new EventAdapter();


        listevent.setLayoutManager(linearLayoutManager);
        listevent.setAdapter(eventListAdapter);


        loadData();
    }

    private void loadData(){
        List<Event> memberList = new ArrayList<>();
        Event member;

        int thumb[] = {R.drawable.ic_account, R.drawable.ic_account, R.drawable.ic_account,
                R.drawable.ic_account, R.drawable.ic_account, R.drawable.ic_account,
                R.drawable.ic_account, R.drawable.ic_account, R.drawable.ic_account,
                R.drawable.ic_account, R.drawable.ic_account, R.drawable.ic_account,
                R.drawable.ic_account, R.drawable.ic_account, R.drawable.ic_account,
                R.drawable.ic_account, R.drawable.ic_account, R.drawable.ic_account,
                R.drawable.ic_account, R.drawable.ic_account};

        String name[] = {"Ayana Shahab", "Beby Chaesara Anadila", "Delima Rizky", "Dena Siti Rohyati",
                "Elaine Hartanto", "Frieska Anastasia Laksani", "Gabriella M. W.", "Ghaida Farisya",
                "Jennifer Rachel Natasya", "Jessica Vania Widjaja", "Jessica Veranda", "Melody Nurramdhani Laksani",
                "Nabilah Ratna Ayu Azalia", "Rezky Wiranti Dhike", "Sendy Ariani", "Shania Junianatha",
                "Shinta Naomi", "Sofia Meifaliani", "Sonia Natalia", "Thalia Ivanka Elizabeth"};

        String team = "Team J";

        for(int i=0; i < thumb.length; i++){
            member = new Event();


            member.setId(i+1);
            member.setTitle(name[i]);
            member.setDetail(team);
            member.setPic(thumb[i]);


            memberList.add(member);

        }

        eventListAdapter.addAll(memberList);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
