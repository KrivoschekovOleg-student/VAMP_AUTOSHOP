package com.example.vemp_ofiicial;

public class Auto {
    private String name;
    private String opisanie;
    private int imageResourse;
    private int count;
    private String unit;

    public Auto(String name, String opisanie, int imageResourse, String unit){
        this.name = name;
        this.opisanie = opisanie;
        this.imageResourse = imageResourse;
        this.count = 1;
        this.unit = unit;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getOpisanie(){
        return this.opisanie;
    }
    public void setOpisanie(String opisanie){
        this.opisanie = opisanie;
    }
    public int getImageResourse(){
        return  this.imageResourse;
    }
    public void setImageResourse(int imageResourse){
        this.imageResourse = imageResourse;
    }
    public int getCount(){
        return this.count;
    }
    public void setCount(int count){
        this.count = count;
    }
    public String getUnit(){
        return this.unit;
    }

}
