package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

import static java.time.temporal.ChronoUnit.DAYS;

public class Main {
    public static void main(String[] args){
        int days = 0;
        LocalDate init , end;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to start the program (Y/N) ?");



        char user =scanner.next().charAt(0);
        if(user == 'Y'){
            System.out.println("Welcome!");
        }
        else if (user =='N'){
            System.out.println("Thank you for using our program!");

        }
        else {
            System.out.println("Please enter again");
        }

        System.out.println("Please choose 1 to input Continent, 2 to input Country ");
        int user2 = scanner.nextInt();
        if (user2 ==1){
            System.out.println("Please input the Continent  "); }
        else if(user2 == 2) {
            System.out.println("Please input the country"); }

        //Area
        String area = scanner.next();


        System.out.println("Please choose the time rage type");
        System.out.println("Choose 1 for A pair of start date and end date (inclusive)");
        System.out.println("Choose 2 for A number of days or weeks from a particular date ");
        System.out.println("Choose 3 for A number of days or weeks to a particular date");










        //Type1
        int user3 = scanner.nextInt();
        if (user3 ==1) {
            init = init_date();
            end = end_date();
            days = (int) DAYS.between(init, end);
        }


        //Type2
        else if(user3 == 2){
            init = init_date();
            System.out.println("Enter 1 for days , 2 for weeks");
            int user4 = scanner.nextInt();
            if(user4 ==1){
                System.out.println("Enter number of days");
                int days_input = scanner.nextInt() ;
                days = days_input;
                LocalDate day_diff1 = init.plusDays(days_input);

            }
            else if (user4 == 2){
                System.out.println("Enter number of weeks");
                int weeks_input = scanner.nextInt();
                int final_days = weeks_input*7;
                days = final_days;
                LocalDate day_diff2 = init.plusDays(final_days);
            }
        }



        //Type3
        else {
            end = end_date();
            System.out.println("Enter 1 for days , 2 for weeks");
            int user5 = scanner.nextInt();
            if(user5 == 1){
                System.out.println("Enter number of days");
                int days_input = scanner.nextInt() ;
                LocalDate day_diff1 = end.minusDays(days_input);
            }
            else if (user5 ==2){
                System.out.println("Enter number of weeks");
                int weeks_input = scanner.nextInt();
                int final_days = weeks_input*7;
                LocalDate day_diff2 = end.minusDays(final_days);

            }
        }





        //Data reader:
        String address = "covid-data.csv";
        String line ;
        int count = 0;
        try {
            BufferedReader br  = new BufferedReader(new FileReader(address));
            while ((line = br.readLine()) !=null ){
                String [] values = line.split(",");
                List<String > al = Arrays.asList(values);
                if (al.contains("Asia")){
                    System.out.println(al.get(2));
                    count +=1;
                }
            }
            System.out.println(count);
        }
        catch (IOException e) {
            e.printStackTrace(); }


//
//        Data data = new Data(area,days);
//        data.Data_division();

    }









    public static LocalDate init_date(){
        String startDate1;
        LocalDate init;

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        System.out.print("Start date: ");
        startDate1 = sc.nextLine();
        init = LocalDate.parse(startDate1, format);
        return init;
    }


    public static LocalDate end_date() {
        String endDate1;
        LocalDate end;

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        System.out.print("End date: ");
        endDate1 = sc.nextLine();
        end = LocalDate.parse(endDate1, format);

        return end;
    }




    public static void Day_type(){

    }


    public static void Groups_type(){

    }

    public static void Nums_of_days_type(){

    }



}
