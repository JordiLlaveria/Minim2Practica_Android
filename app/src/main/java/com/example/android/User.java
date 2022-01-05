package com.example.android;

import java.util.LinkedList;

public class User {

    private String username;
    private String name;
    private String avatar;
    private String surname;
    private LinkedList<Badge> badges;

    public User(){}
    public User(String username, String name, String avatar, String surname, LinkedList<Badge> badges){
        this();
        setName(name);
        setUsername(username);
        setAvatar(avatar);
        setSurname(surname);
        setBadges(badges);
    }

    public void setName(String name){this.name=name;}
    public void setUsername(String username){this.username=username;}
    public void setAvatar(String avatar){this.avatar=avatar;}
    public void setSurname(String surname){this.surname=surname;}
    public void setBadges(LinkedList<Badge> badges){this.badges=badges;}

    public String getName(){return this.name;}
    public String getUsername(){return this.username;}
    public String getAvatar(){return this.avatar;}
    public String getSurname(){return this.surname;}
    public LinkedList<Badge> getBadges(){return this.badges;}
}
