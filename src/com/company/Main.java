package com.company;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args){
        LocalDate init = null, end = null;
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
        if (user3 == 1) {
            init = init_date();
            end = end_date();
        }

        //Type2
        else if(user3 == 2){
            init = init_date();
            System.out.println("Enter 1 for days , 2 for weeks");
            int user4 = scanner.nextInt();
            if(user4 ==1){
                System.out.println("Enter number of days");
                int days_input = scanner.nextInt() ;
                end = init.plusDays(days_input);
            }

            else if (user4 == 2){
                System.out.println("Enter number of weeks");
                int weeks_input = scanner.nextInt();
                int final_days = weeks_input*7;
                end = init.plusDays(final_days);
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
                init= end.minusDays(days_input);
            }
            else if (user5 ==2){
                System.out.println("Enter number of weeks");
                int weeks_input = scanner.nextInt();
                int final_days = weeks_input*7;
                init = end.minusDays(final_days);

            }
        }

        Data start = new Data(area,init,end);
        System.out.println(start.Quering_method(area,init,end));
        System.out.println(start.get_cases());
        System.out.println(start.get_deaths());
        System.out.println(start.get_vaccinated());






        //Data reader:










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
