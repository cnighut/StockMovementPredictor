package com.example.qawbecrdteyf.st_war;

public class DataModel {

    String name;
    String data;
    //String version_number;

    public DataModel(String name, String data) {
        this.name=name;
        this.data=data;
        //this.version_number=version_number;

    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    //public String getVersion_number() {
      //  return version_number;
    //}

}