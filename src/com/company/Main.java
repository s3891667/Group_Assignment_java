package com.company;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.*;

public class Main {
    private static LocalDate startDate; // receive the start date and the end date to retrieve data
    private static LocalDate endDate;

    public static void main(String[] args)  {
        int num;// data object type (e.g, Country = 1, Continent = 2)
        int rangeNum;   // time range type
        int weekDays;   // the number of days or weeks
        int selectNum;//
        int groupType;   // number of groups = 1, number of days = 2
        int groupDay; //
        int metricsNum; // column type of data, 1 = infected, 2 = deaths, 3 = vaccinations
        int resultType; // type to output data, 1 = new total, 2 = up to
        String line; // read data from csv files
        char user; // receive Y/N input at the start of the program
        String area = null;//area represent the country and continent
        String[] array;
        List<List<String>> tmpList = new ArrayList<>(); // processed data storage list

        Scanner scanInt = new Scanner(System.in);
        Scanner scanString = new Scanner(System.in);
        Scanner scanMain = new Scanner(System.in);

        List<List<String>> data = new ArrayList<>();// List of data read from csv file
        File file = new File("covid-data.csv");

        Interface interface_program = new Interface();


        try {       // get data from covid-data.csv
            FileReader fileName = new FileReader(file.getCanonicalPath());
            BufferedReader br = new BufferedReader(fileName);

            while ((line = br.readLine()) != null) {
                array = line.split(",");
                List<String> temp  = Arrays.asList(array);
                data.add(temp);

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        mainLoop: while (true) {
            startDate = null;   // initialize Date variable when loop begins
            endDate = null;
            tmpList.clear();
            groupDay = 0;

            interface_program.Start_Stop_Program();
            user = scanMain.nextLine().charAt(0);

            switch (user) {
                case 'Y':
                case 'y':
                    System.out.println(" ____________________________________________________________________________________________________");
                    System.out.println("|                                                                                                    |");
                    System.out.println("|                                    Welcome to Data management                                      |");
                    System.out.println("|                                                                                                    |");
                    System.out.println("|____________________________________________________________________________________________________|");

                    interface_program.Intro_interface();
                    num = interface_program.Interface_management("Continent","Country",null);

                    if (num == 1) {
                        area = interface_program.Enter_name("Continent");
                    } else if (num == 2) {
                        area = interface_program.Enter_name("Country");
                    }
                    System.out.println();
                    rangeNum=interface_program.Interface_management("A pair of start date and end date(inclusive)","A number of days or weeks from a particular date","A number of days or weeks to a particular date");
                    System.out.println(" Please use date in the format MM/dd/yyyy ");
                    switch (rangeNum) {
                        case 1:
                            String start;
                            String end;

                            interface_program.Interface_text("Start Date");
                            start = scanString.nextLine();

                            interface_program.Interface_text("End Date");
                            end = scanString.nextLine();

                            startDate = changeFormat(start);
                            endDate = changeFormat(end);

                            groupType=interface_program.Interface_management("NO GROUPING","DIVIDING DATA INTO A NUMBER OF GROUPS ","NUMBER OF DAYS");

                            switch (groupType) {
                                case 1:
                                    System.out.println();
                                    break;
                                case 2:
                                    interface_program.Interface_text("Please input a number of groups: ");
                                    groupDay = scanInt.nextInt();
                                    break;
                                case 3:
                                    interface_program.Interface_text("Please input a number of days: ");
                                    groupDay = scanInt.nextInt();
                                    break;
                            }

                            metricsNum=interface_program.Interface_management("positive cases","deaths","people vaccinated")+3;

                            resultType=interface_program.Interface_management("New Total","Up To",null);

                            tmpList = dataProcess(data, area);

                            int displaytype = interface_program.Interface_management("Tabular","Chart",null);
                            passing_value(groupType, groupDay, metricsNum, resultType, displaytype, tmpList);

                            break;



                        case 2:
                            selectNum=interface_program.Interface_management("Days","Weeks",null);

                            switch (selectNum) {
                                case 1:
                                    interface_program.Interface_text("Start Date");
                                    start = scanString.nextLine();

                                    interface_program.Interface_text("A number of days");
                                    weekDays = scanInt.nextInt();

                                    startDate = changeFormat(start);
                                    endDate = changeFormat(start).plusDays(weekDays);
                                    break;

                                case 2:
                                    interface_program.Interface_text("Start Date");
                                    start = scanString.nextLine();

                                    interface_program.Interface_text("A Number of Weeks");
                                    weekDays = scanInt.nextInt();

                                    startDate = changeFormat(start);
                                    endDate = changeFormat(start).plusWeeks(weekDays);
                                    break;
                            }

                            groupType=interface_program.Interface_management("NO GROUPING","DIVIDING DATA INTO A NUMBER OF GROUPS ","NUMBER OF DAYS");

                            switch (groupType) {
                                case 1:
                                    System.out.println();
                                    break;
                                case 2:
                                    interface_program.Interface_text("Please input a number of groups: ");
                                    groupDay = scanInt.nextInt();
                                    break;
                                case 3:
                                    interface_program.Interface_text("Please input a number of days: ");
                                    groupDay = scanInt.nextInt();
                                    break;
                            }

                            metricsNum=interface_program.Interface_management("positive cases","deaths","people vaccinated")+3;

                            resultType=interface_program.Interface_management("New Total","Up To",null);

                            tmpList = dataProcess(data, area);
                            System.out.println(tmpList);

                            displaytype=interface_program.Interface_management("Tabular","Chart",null);
                            passing_value(groupType, groupDay, metricsNum, resultType, displaytype, tmpList);

                            break;


                        case 3:
                            selectNum=interface_program.Interface_management("Day","Week",null);

                            switch (selectNum) {
                                case 1:
                                    interface_program.Interface_text("Start Date");
                                    start = scanString.nextLine();

                                    interface_program.Interface_text("A Number Of Days");
                                    weekDays = scanInt.nextInt();

                                    endDate = changeFormat(start);
                                    startDate = changeFormat(start).minusDays(weekDays);
                                    break;

                                case 2:
                                    interface_program.Interface_text("Start Date");
                                    start = scanString.nextLine();

                                    interface_program.Interface_text("A Number of Week");
                                    weekDays = scanInt.nextInt();

                                    endDate = changeFormat(start);
                                    startDate = changeFormat(start).minusWeeks(weekDays);
                                    break;
                            }

                            groupType=interface_program.Interface_management("NO GROUPING","DIVIDING DATA INTO A NUMBER OF GROUPS ","NUMBER OF DAYS");

                            switch (groupType) {
                                case 1:
                                    System.out.println();
                                    break;
                                case 2:
                                    interface_program.Interface_text("Please input a number of groups: ");
                                    groupDay = scanInt.nextInt();
                                    break;
                                case 3:
                                    interface_program.Interface_text("Please input a number of days: ");
                                    groupDay = scanInt.nextInt();
                                    break;
                            }

                            metricsNum=interface_program.Interface_management("positive cases","deaths","people vaccinated")+3;

                            resultType=interface_program.Interface_management("New Total","Up To",null);

                            tmpList = dataProcess(data, area);

                            displaytype=interface_program.Interface_management("Tabular","Chart",null);
                            passing_value(groupType, groupDay, metricsNum, resultType, displaytype, tmpList);
                            break;


                    }
                    break;

                case 'N':
                case 'n':
                    interface_program.end_program("Thank you for using our program!\n");
                    break mainLoop;

                default:
                    interface_program.Interface_text("Please enter again");

            }

        }
    }

    public static LocalDate changeFormat(String date) { // change the type and pattern of the entered date
        DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy");
        return LocalDate.parse(date, format);
    }

    public static boolean validateRange(LocalDate start, LocalDate end, LocalDate date) { // checks whether the input date is in the current data
        return start.compareTo(date) <= 0 && end.compareTo(date) >= 0;
    }

    public static List<List<String>> dataProcess(List<List<String>> data, String area) {  // processes data according to the type and returns it
        LocalDate date;
        List<List<String>> tempList = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
                date = changeFormat(data.get(i).get(3));// temporarily save the dates in order from the csv file
                if (validateRange(startDate, endDate, date) && data.get(i).get(2).equals(area)) {// Check if the input date corresponds to the csv file
                    List<String> tmp = data.get(i);
                    tempList.add(tmp);
                }
            }
        return tempList;
    }

    public static void passing_value(int groupType,int groupDay,int metricsNum,int resultType,int displaytype, List<List<String>> tmpList ){
        data_display data = new data_display(startDate,endDate,tmpList,groupType,groupDay,metricsNum,resultType,displaytype);
        data.dataGroup(tmpList, groupType, groupDay, metricsNum, resultType); //passing the data to the processor

    }
}
