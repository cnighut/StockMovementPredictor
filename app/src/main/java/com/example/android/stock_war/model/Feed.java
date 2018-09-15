package com.example.android.stock_war.model;

public class Feed {

    private String company, category, imageUrl, open, close, low, high;
    private int dayOne, dayTwo, dayThree, dayFour, dayFive;

    public Feed(String company, String category, String open, String close, String low, String high, String imageUrl, int dayOne, int dayTwo, int dayThree, int dayFour, int dayFive){
        this.company = company;
        this.category = category;
        this.open = open;
        this.close = close;
        this.low = low;
        this.high = high;
        this.imageUrl = imageUrl;
        this.dayOne = dayOne;
        this.dayTwo = dayTwo;
        this.dayThree = dayThree;
        this.dayFour = dayFour;
        this.dayFive = dayFive;
    }

    public String getCompany(){
        return company;
    }
    public void setCompany(String company){
        this.company = company;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public String getOpen(){
        return  open;
    }
    public void setOpen(String open){
        this.open = open;
    }
    public String getClose(){
        return close;
    }
    public void setClose(String close){
        this.close = close;
    }
    public String getLow(){
        return low;
    }
    public void setLow(String low){
        this.low = low;
    }
    public String getHigh(){
        return high;
    }
    public void setHigh(String high){
        this.high = high;
    }
    public String getImgUrl(){
        return imageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public int getDayOne(){
        return dayOne;
    }
    public void setDayOne(int dayOne){
        this.dayOne = dayOne;
    }
    public int getDayTwo(){
        return dayTwo;
    }
    public void setDayTwo(int dayTwo){
        this.dayTwo = dayTwo;
    }
    public int getDayThree(){
        return dayThree;
    }
    public void setDayThree(int dayThree){
        this.dayThree = dayThree;
    }
    public int getDayFour(){
        return dayFour;
    }
    public void setDayFour(int dayFour){
        this.dayFour = dayFour;
    }
    public int getDayFive(){
        return dayFive;
    }
    public void setDayFive(int dayFive){
        this.dayFive = dayFive;
    }
}
