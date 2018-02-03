package com.example.user.myapp;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_recycler, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.lab3_recycler);

        List<String> GroupList = new ArrayList<>();
        GroupList = ((MainActivity)getActivity()).SearchAll();
        int n = GroupList.size();
        List<String> GroupUserList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for(int i = 0;i<n;i++)
        {
            GroupUserList = ((MainActivity)getActivity()).SearchTeam(GroupList.get(i));
            int k = GroupUserList.size();
            for(int j = 0 ; j < k; j++) {
                list.add(GroupUserList.get(j));
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapter(list));
        recyclerView.addItemDecoration(new MyItemDecoration());
        return rootView;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public MyViewHolder(View itemView){
            super(itemView);
            title=(TextView)itemView.findViewById(android.R.id.text1);
        }
    }
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        private List<String> list;
        public MyAdapter(List<String> list){
            this.list=list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            String text=list.get(position);
            holder.title.setText(text);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class MyItemDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            outRect.set(20, 20, 20, 20);

            view.setBackgroundColor(0xFFECE9E9);
            ViewCompat.setElevation(view, 20.0f);
        }
    }
}


