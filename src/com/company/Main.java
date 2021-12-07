package com.company;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import java.io.IOException;

import static java.time.temporal.ChronoUnit.DAYS;

public class Main {
    public Main() {
    }

    public static void main(String[] args){
        LocalDate init,end;
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
            String area = scanner.next();



        //Type1
            init = init_date();
        end = end_date();
        int days = (int) DAYS.between(init, end);








        Data data = new Data(area,days);
        data.Data_division();

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
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        System.out.print("End date: ");
        endDate1 = sc.nextLine();
        end = LocalDate.parse(endDate1, df);

        return end;
    }

    public static void Day_type(){

    }


    public static void Groups_type(){

    }

    public static void Nums_of_days_type(){

    }



}
