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
 * Created by user on 2018-01-27.
 */

public class UserEditFragment extends Fragment {

    EditText Name;
    EditText Phone;
    EditText address;
    EditText Job;
    EditText Group;
    EditText ID;
   String[] datas = null;

    public void SetterDatas(String[] strArray)
    {
        datas = strArray;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.member_view,container,false);
        ID = (EditText) view.findViewById(R.id.add_ID);
        Name = (EditText) view.findViewById(R.id.add_Ename);
        Phone = (EditText)  view.findViewById(R.id.add_Ephone);
        address = (EditText)  view.findViewById(R.id.add_Eaddress);
        Job = (EditText)  view.findViewById(R.id.add_Ejob);
        Group = (EditText)  view.findViewById(R.id.add_Eteam);

        ID.setText(datas[0]);
        Phone.setText(datas[1]);
        Name.setText(datas[2]);
        address.setText(datas[3]);
        Job.setText(datas[4]);
        Group.setText(datas[5]);
        Button btn_Edit = (Button)view.findViewById(R.id.add_Edit);
        Button btn_Delete = (Button)view.findViewById(R.id.add_Delete);

        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sID = ID.getText().toString();
                String sName = Name.getText().toString();
                String sPhone = Phone.getText().toString();
                String sAddress = address.getText().toString();
                String sJob = Job.getText().toString();
                String sGroup = Group.getText().toString();
                ((MainActivity)getActivity()).EditUserInfo(sID,sPhone,sName,sAddress,sJob,sGroup);
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sID = ID.getText().toString();
                ((MainActivity)getActivity()).DeleteUserInfo(sID);
            }
        });

        return view;
    }

}
