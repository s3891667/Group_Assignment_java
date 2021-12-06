package com.company;


import java.util.ArrayList;

public class Data extends Main{
    protected String continent;
    protected String time_range;
    protected String country;

    public Data (String continent, String time_range, String country){
        this.continent = continent;
        this.time_range = time_range;
        this.country = country;
    }

    public  void Data_division(){
            ArrayList <String> data_list = new ArrayList<String>();
            data_list.add(continent);
            data_list.add(time_range);
            data_list.add(country);
            System.out.println(data_list);



    }




    public String continent (){
        return continent;
    }
    public String time_rage(){
        return time_range;
    }
    public String country(){
        return country;
    }


}
