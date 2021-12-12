package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Data extends Main{
    private final String area;
    private final LocalDate init;
    private final LocalDate end;
    ArrayList <Integer > cases = new ArrayList<>();
    ArrayList <Integer > deaths = new ArrayList<>();
    ArrayList <Long> vaccinated = new ArrayList<>();

    public Data(String area, LocalDate init, LocalDate end ) {
        this.area = area;
        this.init = init;
        this.end = end;
    }


    public void  Querying_method(String area, LocalDate init, LocalDate end){
        // Reading a file
        String address = "covid-data.csv";
        String line ;
        try {
            BufferedReader br = new BufferedReader(new FileReader(address));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            //skip first line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                LocalDate compare = LocalDate.parse(values[3],formatter);
                //since the user is going to input continent or country so i will define those into same variable
                if (values[2].equals(area) | values[1].equals(area)) {
                    // Convert value at column 4 to Integer;
                    int case_num = Integer.parseInt(values[4]);
                    if (compare.isAfter(init.minusDays(1)) && compare.isBefore(end.plusDays(1))) {
                        //if the cells in csv file is blank
                        if (values[5] == null || values[5].equals("")) {
                            values[5] = String.valueOf(0);
                        }
                        if (values[6] == null || values[6].equals("")) {
                            values[6] = String.valueOf(0);
                        }
                        /* convert value from column 5 and 6 to int and long
                        to add it to an array */
                        int death_num = Integer.parseInt(values[5]);
                        long vaccinated_num = Long.parseLong(values[6]);
                        cases.add(case_num);
                        deaths.add(death_num);
                        vaccinated.add(vaccinated_num);
                    }
                }
                else {
                    br.readLine();
                }
            }
            System.out.println( "Infected cases : " + cases);
            System.out.println("Death cases : " + deaths);
            System.out.println("Vaccinated : " + vaccinated);
        }

        catch (IOException e) {
            e.printStackTrace(); }


    }





    public Integer get_cases(){
        int count= 0;
        List<Integer> nums =new ArrayList<>(cases);
        for (Integer num: nums  ){
            count += num;
        }

        return count;
    }


    public Integer get_deaths(){
        int count= 0;
        List<Integer> nums =new ArrayList<>(deaths);
        for (Integer num: nums ){
            count += num;
        }

        return count;
    }


    public Long get_vaccinated(){
        long count= 0;
        List<Long> nums =new ArrayList<>(vaccinated);
        for (Long num: nums  ){
            count += num;
        }

        return count;
    }




    public  void Data_division(){

    }

    public String continent (){

        return area;
    }


    public LocalDate getInit() {
        return init;
    }

    public LocalDate getEnd() {
        return end;
    }

}
