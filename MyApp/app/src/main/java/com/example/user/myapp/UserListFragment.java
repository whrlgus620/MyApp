package com.example.user.myapp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 2018-01-26.
 */

public class UserListFragment extends ListFragment {

    ArrayList<String> datas = null;

    public void SetterDatas(ArrayList<String> strArray)
    {
        datas = strArray;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        if(datas.isEmpty())
            datas.add("Test");
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,datas);
        setListAdapter(aa);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        String sName = (String) l.getItemAtPosition(position) ;
        ((MainActivity)getActivity()).Search(sName, v);

    }

}
