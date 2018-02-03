package com.example.user.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 2018-01-26.
 */

public class UserAddFragment extends Fragment {

    EditText Name;
    EditText Phone;
    EditText address;
    EditText Job;
    EditText Group;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_info_member,container,false);
        Name = (EditText) view.findViewById(R.id.add_name);
        Phone = (EditText)  view.findViewById(R.id.add_phone);
        address = (EditText)  view.findViewById(R.id.add_address);
        Job = (EditText)  view.findViewById(R.id.add_job);
        Group = (EditText)  view.findViewById(R.id.add_team);
        Button btn_add = (Button)view.findViewById(R.id.add_btn);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sName = Name.getText().toString();
                String sPhone = Phone.getText().toString();
                String sAddress = address.getText().toString();
                String sJob = Job.getText().toString();
                String sGroup = Group.getText().toString();
                ((MainActivity)getActivity()).AddUser(sPhone,sName,sAddress,sJob,sGroup);
            }
        });
        return view;
    }
}
