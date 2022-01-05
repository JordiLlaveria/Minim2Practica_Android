package com.example.android;

public class Badge {

    private String name;
    private String url;

    public Badge(){}
    public Badge(String name, String url){
        this();
        setName(name);
        setUrl(url);
    }

    public void setName(String name){this.name=name;}
    public void setUrl(String url){this.url=url;}

    public String getName(){return this.name;}
    public String getUrl(){return this.url;}
}
