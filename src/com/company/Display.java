package com.company;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

class Display extends Data {
    static String[][] Oxy = new String[24][80];
    public Display(ArrayList<Integer> cases_list) {
        super(cases_list);
    }
    public static void tabular(LocalDate startDate, LocalDate endDate, List<List<String>> finalList, ArrayList<Integer> cases_list) {
        LocalDate update_date = startDate;
        // set up a loop break ( while it reaches last date)
        LocalDate breaker = endDate.plusDays(1);
        //k is used for looping the cases_list
        int k = 0;
        // counter used for decoration
        int counter = 0;
        //loop a finalList and update date at the same time
        for (List<String> csv : finalList) {
            if (update_date.getDayOfMonth() == breaker.getDayOfMonth()) {
                break;}
            else {
                if (csv.toArray().length == 1 && counter == 0) {
                    System.out.print("\n--Tabular Display--");
                    System.out.println("\n|Date ranges|Cases|");}
                else if (csv.toArray().length > 1 && counter == 0){
                    System.out.print("\n---------Tabular Display--------");
                    System.out.println("\n|-------Date ranges------|Cases|");}
                //print at the beginning of the chart only ( just a decoration purpose)
                counter += 1;
                System.out.print("|" + update_date);
                for (int j = 0; j < csv.toArray().length; j++) {
                    // update the date continuously
                    update_date = update_date.plusDays(1);
                    if (update_date.getDayOfMonth() == breaker.getDayOfMonth()) {
                        System.out.println("|______________________________|");
                    }
                    // this is for the case each date is 1 group
                    while (csv.toArray().length == 1) {
                        // decorations
                        if (cases_list.get(k) >= 10) {
                            System.out.print(" :  " + cases_list.get(k) + " |");
                        } else if (cases_list.get(k) < 10) {
                            System.out.print(" :  " + cases_list.get(k) + "  |");
                        }
                        System.out.println();
                        if (update_date.getDayOfMonth() == breaker.getDayOfMonth()) {
                            System.out.print("|_________________|");
                            break;
                        }
                        System.out.print("|" + update_date);
                        update_date = update_date.plusDays(1);
                        k += 1;
                        j++;}

                    // date range cases ( 22/3 - 25/3)
                    if (j == csv.toArray().length - 2) {
                        System.out.print(" - " + update_date + " : " + cases_list.get(k) + "  |");
                        k += 1;
                        System.out.println();
                    }
                }
            }
        }
    }


    public static void chart( ArrayList <Integer>cases_list){
        Chart_theme();
        Solving_the_data(cases_list);
        display_chart_data();
    }


    public static void Chart_theme() {
        for (int x = 0; x < 24; x++) {
            for (int y = 0; y < 80; y++) {
                if (y == 0) {
                    Oxy[x][y] = "|";
                } else if (x == 23) {
                    Oxy[x][y] = "_";
                } else {
                    Oxy[x][y] = " ";
                }
                if (x ==23&& y ==0){
                    Oxy[x][y] = "|";}
            }
        }
    }

    public static void display_chart_data() {
        for (int x = 0; x < 24; x++) {
            for (int y = 0; y < 80; y++) {
                System.out.print(Oxy[x][y]);
            }
            System.out.println();
        }
    }


    // Display Data
    public static void Solving_the_data(ArrayList<Integer> cases_list) {
        Collections.sort(cases_list);
        double max = Collections.max(cases_list);
        double min = Collections.min(cases_list);
        double value_range = 22 / (max -min +1) ;
        int distance_between_column = 78 / (cases_list.toArray().length);
        int y;
        int x = 1;
        for (int k = 0; k < cases_list.toArray().length; k++) {
            if (cases_list.get(k) == min) {
                Oxy[22][x] = "*";}
            else if ( cases_list.get(k) < min){
                Oxy[22][x] = "*";}
            else{
                double valuePoint = (cases_list.get(k) * value_range) - min * value_range;
                if (valuePoint < 1) {
                    valuePoint = 1;}
                y = (int) Math.ceil(23 - valuePoint);
                Oxy[y][x] = "*";}
            x += distance_between_column;
        }
        System.out.println("------------------------------------ CHART TABLE ------------------------------------");
    }
}