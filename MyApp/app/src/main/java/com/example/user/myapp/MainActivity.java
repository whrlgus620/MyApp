package com.example.user.myapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1;
    Button btn2;
    Button btn3;

    FragmentManager manager;
    UserListFragment ulfManager;
    UserAddFragment uafManager;
    RecyclerFragment rfManager;
    UserEditFragment uef;

    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.main_btn1);
        btn2 = (Button) findViewById(R.id.main_btn2);
        btn3 = (Button) findViewById(R.id.main_btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        manager = getSupportFragmentManager();

        ArrayList<String> strArray = new ArrayList<>();

       /* Realm mRealm = Realm.getDefaultInstance();
        RealmResults<MemoVO> rMVO = mRealm.where(MemoVO.class).findAll().sort("_id", Sort.DESCENDING);
        for (int i = 0; i < rMVO.size(); i++){
            strArray.add(new MemberItems(
                    rMVO.get(i)._id,
                    rMVO.get(i).phone,
                    rMVO.get(i).name, rMVO.get(i).address, rMVO.get(i).Job, rMVO.get(i).team));
        }*/

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select name from tb_data order by _id desc ", null);

        while(cursor.moveToNext()) {
            strArray.add(cursor.getString(0));
        }  //sqlite

        ulfManager = new UserListFragment();
        uafManager = new UserAddFragment();
        rfManager = new RecyclerFragment();
        ulfManager.SetterDatas(strArray); //sqlite

        onClick(btn1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void AddUser(String sPhone, String sName, String sAddress, String sJob, String sGroup)
    {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into tb_data (Phone, Name, Address, Job, Team) values (?,?,?,?,?)", new String[]{sPhone, sName, sAddress, sJob, sGroup});

        Cursor cursor = db.rawQuery("select name from tb_data order by _id desc ", null);

        ArrayList<String> strArray = new ArrayList<>();
        while(cursor.moveToNext()) {
            strArray.add(cursor.getString(0));
        }
        ulfManager.SetterDatas(strArray);
        db.close();

        onClick(btn1);

    }

    //Modify
    public void EditUserInfo(String sID,String sPhone, String sName,String sAddress, String sJob, String sGroup)
    {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("update tb_data Set  phone = ?, name = ?, address = ?, Job = ?, team = ? where _id = ?"  , new String[]{sPhone, sName, sAddress, sJob, sGroup,sID});

        Cursor cursor = db.rawQuery("select name from tb_data order by _id desc ", null);

        ArrayList<String> strArray = new ArrayList<>();
        while(cursor.moveToNext()) {
            strArray.add(cursor.getString(0));
        }
        ulfManager.SetterDatas(strArray);
        db.close();
        onClick(btn1);
    }

    //Delete
    public void DeleteUserInfo(String sID)
    {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("Delete from tb_data where _id = ?", new String[]{sID});

        Cursor cursor = db.rawQuery("select name from tb_data order by _id desc ", null);

        ArrayList<String> strArray = new ArrayList<>();
        while(cursor.moveToNext()) {
            strArray.add(cursor.getString(0));
        }
        ulfManager.SetterDatas(strArray);
        db.close();

        onClick(btn1);
    }

    public void Search(String sName, View v)
    {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select _id,phone,name,address,job,team from tb_data  where name = ?" , new String[]{ sName});

        String[] strArray = new String[6];

        if(cursor.moveToFirst()) {
            strArray[0] = cursor.getString(0);
            strArray[1] = cursor.getString(1);
            strArray[2] = cursor.getString(2);
            strArray[3] = cursor.getString(3);
            strArray[4] = cursor.getString(4);
            strArray[5] = cursor.getString(5);
        }
        UserEditFragment uefManager;
        uefManager = new UserEditFragment();
        uefManager.SetterDatas(strArray);
        uef = uefManager;
        db.close();
        onClick(v);

    }

    public List<String> SearchAll()
    {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select team from tb_data " , new String[]{ });

        List<String> strArray = new ArrayList<>();
        while(cursor.moveToNext()) {
            strArray.add(cursor.getString(0));
        }

        return strArray;
    }

    public List<String> SearchTeam(String sTeam)
    {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select name from tb_data where team= ?" , new String[]{sTeam });

        List<String> strArray = new ArrayList<>();
        while(cursor.moveToNext()) {
            strArray.add(cursor.getString(0));
        }
        return strArray;
    }

    @Override
    public void onClick(View v)
    {
        if(v==btn1){
            if(!ulfManager.isVisible()){
                FragmentTransaction ft=manager.beginTransaction();
                ft.replace(R.id.main_container, ulfManager);
                ft.commit();
            }
        }else if(v==btn2){
            if(!uafManager.isVisible()){
                FragmentTransaction ft=manager.beginTransaction();
                ft.replace(R.id.main_container, uafManager);
                ft.commit();
            }
        }else if(v==btn3) {
            if(!rfManager.isVisible()){
                FragmentTransaction ft=manager.beginTransaction();
                ft.replace(R.id.main_container, rfManager);
                ft.commit();
            }
        }
        else {
            if(!uef.isVisible()){
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.main_container, uef);
                ft.commit();
            }
        }
    }
}
