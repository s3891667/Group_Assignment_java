package com.company;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.*;

public class Main {
    private static LocalDate startDate;
    private static LocalDate endDate;

    public static void main(String[] args)  {
        int num;        // data object type (e.g, Country = 1, Continent = 2)
        int rangeNum;   // time range type
        int weekDays;   // the number of days or weeks
        int groupType;   // number of groups = 1, number of days = 2
        int groupDay;
        int selectNum;
        int metricsNum;
        int resultType;
        String line;
        char user;
        String area;

        List<List<String>> tmpList = new ArrayList<>();

        Scanner scanInt = new Scanner(System.in);
        Scanner scanString = new Scanner(System.in);
        Scanner scanMain = new Scanner(System.in);

        List<List<String>> data = new ArrayList<>();
        File file = new File("covid-data.csv");



        try {       // get data from covid-data.csv
            FileReader fileName = new FileReader(file.getCanonicalPath());
            BufferedReader br = new BufferedReader(fileName);

            while ((line = br.readLine()) != null) {

                String[] array = line.split(",");
                List<String> temp = Arrays.asList(array);
                data.add(temp);

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            startDate = null;   // initialize Date variable when loop begins
            endDate = null;
            tmpList.clear();
            groupDay = 0;

            System.out.print("Do you want to start the program? (Y/N) ");

            user = scanMain.nextLine().charAt(0);

            if (user == 'Y' || user == 'y') {
                System.out.println(" ____________________________________________________________________________________________________");
                System.out.println("|                                                                                                    |");
                System.out.println("|                                    Welcome to Data management                                      |");
                System.out.println("|                                                                                                    |");
                System.out.println("|____________________________________________________________________________________________________|");
                
                
                num=interface_program.Interface_management("Continent","Country",null);
                if (num == 1) {
                    area=interface_program.Enter_name("Continent");
                } else if (num == 2) {
                    area=interface_program.Enter_name("Country");
                }
                System.out.println();

                rangeNum=interface_program.Interface_management("A pair of start date and end date(inclusive)","A number of days or weeks from a particular date","A number of days or weeks to a particular date");

                if (rangeNum == 1) {
                    String start;
                    String end;

                    interface_program.Interface_text("Start Date");
                    start = scanString.nextLine();

                    interface_program.Interface_text("End Date");
                    end = scanString.nextLine();

                    startDate = changeFormat(start);
                    endDate = changeFormat(end);

                } else if (rangeNum == 2) {
                    String start;

                    selectNum=interface_program.Interface_management("Days","Weeks",null);

                    if (selectNum == 1) {
                        interface_program.Interface_text("Start Date");
                        start = scanString.nextLine();

                        interface_program.Interface_text("A number of days");
                        weekDays = scanInt.nextInt();
                        
                        startDate = changeFormat(start);
                        endDate = changeFormat(start).plusDays(weekDays);

                    } else if (selectNum == 2) {
                        interface_program.Interface_text("Start Date");
                        start = scanString.nextLine();

                        interface_program.Interface_text("A Number of Weeks");
                        weekDays = scanInt.nextInt();

                        startDate = changeFormat(start);
                        endDate = changeFormat(start).plusWeeks(weekDays);

                    }


                } else if (rangeNum == 3) {
                    String start;

                    selectNum=interface_program.Interface_management("Day","Week",null);

                    if (selectNum == 1) {
                        interface_program.Interface_text("Start Date");
                        start = scanString.nextLine();

                        interface_program.Interface_text("A Number Of Days");
                        weekDays = scanInt.nextInt();
                        
                        endDate = changeFormat(start);
                        startDate = changeFormat(start).minusDays(weekDays);

                    } else if (selectNum == 2) {
                        interface_program.Interface_text("Start Date");
                        start = scanString.nextLine();

                        interface_program.Interface_text("A Number of Week");
                        weekDays = scanInt.nextInt();
                        
                        endDate = changeFormat(start);
                        startDate = changeFormat(start).minusWeeks(weekDays);

                    }
                }


                groupType=interface_program.Interface_management("NO GROUPING","DIVIDING DATA INTO A NUMBER OF GROUPS ","NUMBER OF DAYS");


                if ( groupType ==1){
                    System.out.println();
                }
                else if (groupType == 2) {
                    interface_program.Interface_text("Please input a number of groups: ");
                    groupDay = scanInt.nextInt();
                } else if (groupType == 3) {
                    interface_program.Interface_text("Please input a number of days: ");
                    groupDay = scanInt.nextInt();
                }

                metricsNum=interface_program.Interface_management("positive cases","deaths","people vaccinated")+3;

                resultType=interface_program.Interface_management("New Total","Up To",null);

                tmpList = dataProcess(data, area, num);


                System.out.println("\n Choose 1 for Tabular, 2 for Chart display");
                int displaytype = scanInt.nextInt();

                passing_value(groupType, groupDay, metricsNum, resultType, displaytype, tmpList);

            }

            else if (user == 'N') {
                System.out.println("\nThank you for using our program!\n");
                break;
            } else System.out.println("Please enter again");

        }
    }

    public static LocalDate changeFormat(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy");
        return LocalDate.parse(date, format);
    }

    public static boolean validateRange(LocalDate start, LocalDate end, LocalDate date) {
        return start.compareTo(date) <= 0 && end.compareTo(date) >= 0;
    }

    public static List<List<String>> dataProcess(List<List<String>> data, String continent, int num) {
        LocalDate date;
        List<List<String>> tempList = new ArrayList<>();

        if (num == 1) {
            for (int i = 1; i < data.size(); i++) {
                date = changeFormat(data.get(i).get(3));
                if (validateRange(startDate, endDate, date) && data.get(i).get(1).equals(continent)) {
                    List<String> tmp = data.get(i);
                    tempList.add(tmp);
                }
            }

        } else if (num == 2) {
            for (int i = 1; i < data.size(); i++) {
                date = changeFormat(data.get(i).get(3));
                if (validateRange(startDate, endDate, date) && data.get(i).get(2).equals(continent)) {
                    List<String> tmp = data.get(i);
                    tempList.add(tmp);
                }
            }
        }

        return tempList;
    }

    public static void passing_value(int groupType,int groupDay,int metricsNum,int resultType,int displaytype, List<List<String>> tmpList ){
        data_display data2 = new data_display(startDate,endDate,tmpList,groupType,groupDay,metricsNum,resultType,displaytype);
        data2.dataGroup(tmpList, groupType, groupDay, metricsNum, resultType);
        data2.displaying(displaytype,startDate,endDate);
    }


}












