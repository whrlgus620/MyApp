package com.example.user.myapp;

/**
 * Created by user on 2018-02-01.
 */

public class MemberItems {

    public String _id;
    public String phone;
    public String name;
    public String address;
    public String Job;
    public String team;

    public MemberItems(String id, String _phone, String _name, String _address, String _Job, String _team)
    {
        _id = id;
        phone = _phone;
        name = _name;
        address = _address;
        Job = _Job;
        team = _team;
    }
}
