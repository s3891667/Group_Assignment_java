package com.company;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static LocalDate startDate;
    private static LocalDate endDate;

    public static void main(String[] args) {
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
        String area = null;

        List<List<String>> printList = new ArrayList<>();
        List<List<String>> tmpList = new ArrayList<>();

        Scanner scanInt = new Scanner(System.in);
        Scanner scanString = new Scanner(System.in);

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
            printList.clear();
            tmpList.clear();
            rangeNum = 0;
            selectNum = 0;
            groupDay = 0;
            metricsNum = 0;

            System.out.print("Do you want to start the program? (Y/N) ");

            user = scanString.nextLine().charAt(0);

            if (user == 'Y') {
                System.out.print("Please choose number 1 to input Continent, number 2 to input Country: ");
                num = scanInt.nextInt();
                System.out.println("\nPlease choose the time range type");
                System.out.println("Choose 1 for A pair of start date and end date(inclusive)");
                System.out.println("Choose 2 for A number of days or weeks from a particular date");
                System.out.println("Choose 3 for A number of days or weeks to a particular date");

                rangeNum = scanInt.nextInt();

                if (rangeNum == 1) {
                    String start;
                    String end;

                    System.out.println("\nStart date: ");
                    start = scanString.nextLine();

                    System.out.println("End date: ");
                    end = scanString.nextLine();

                    startDate = changeFormat(start);
                    endDate = changeFormat(end);

                } else if (rangeNum == 2) {
                    String start;

                    System.out.println("\nEnter 1 for days, 2 for weeks: ");
                    selectNum = scanInt.nextInt();

                    if (selectNum == 1) {
                        System.out.println("\nStart date: ");
                        start = scanString.nextLine();

                        System.out.println("A number of days: ");
                        weekDays = scanInt.nextInt();

                        startDate = changeFormat(start);
                        endDate = changeFormat(start).plusDays(weekDays);

                    } else if (selectNum == 2) {
                        System.out.println("\nStart date: ");
                        start = scanString.nextLine();

                        System.out.println("A number of weeks: ");
                        weekDays = scanInt.nextInt();

                        startDate = changeFormat(start);
                        endDate = changeFormat(start).plusWeeks(weekDays);

                    }


                } else if (rangeNum == 3) {
                    String start;

                    System.out.println("\nEnter 1 for days, 2 for weeks: ");
                    selectNum = scanInt.nextInt();

                    if (selectNum == 1) {
                        System.out.println("\nStart date: ");
                        start = scanString.nextLine();

                        System.out.println("A number of days: ");
                        weekDays = scanInt.nextInt();

                        endDate = changeFormat(start);
                        startDate = changeFormat(start).minusDays(weekDays);

                    } else if (selectNum == 2) {
                        System.out.println("\nStart date: ");
                        start = scanString.nextLine();

                        System.out.println("A number of weeks: ");
                        weekDays = scanInt.nextInt();

                        endDate = changeFormat(start);
                        startDate = changeFormat(start).minusWeeks(weekDays);

                    }

                }

                System.out.println("\nChoose 1 for no grouping, Choose 2 for divide data into number of groups, 3 for number of days: ");
                groupType = scanInt.nextInt();

                if ( groupType ==1){
                    System.out.println();
                }
                else if (groupType == 2) {
                    System.out.println("Please input a number of groups: ");
                    groupDay = scanInt.nextInt();
                } else if (groupType == 3) {
                    System.out.println("Please input a number of days: ");
                    groupDay = scanInt.nextInt();
                }

                System.out.println("\nChoose 1 for positive cases, 2 for deaths, 3 for people vaccinated");
                metricsNum = scanInt.nextInt() + 3;

                System.out.println("\nChoose 1 for New Total, 2 for Up To ");
                resultType = scanInt.nextInt();

                if (num == 1) {

                    System.out.print("\nPlease input the Continent: ");
                    area = scanString.nextLine();

                    tmpList = dataProcess(data, area, num);

                    printList = dataGroup(tmpList, groupType, groupDay, metricsNum, resultType);

                } else if (num == 2) {

                    System.out.print("\nPlease input the Country: ");
                    area = scanString.nextLine();

                    tmpList = dataProcess(data, area, num);

                    printList = dataGroup(tmpList, groupType, groupDay, metricsNum, resultType);

                }


                Data total_case = new Data(area,startDate,endDate);

            } else if (user == 'N') {
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
                    tempList.add(tmp); }
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

    public static List<List<String>> dataGroup(List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType) {
        List<String> tmp = new ArrayList<>();
        List<List<String>> finalList = new ArrayList<>();

        if (groupType == 1 ){
            LocalDate update = startDate;
            for(int i = 0 ; i < tmpList.size(); i++ ){
                String data = tmpList.get(i).subList(metricsNum, metricsNum + 1).get(0);
                if (!data.equals("")) {
                    tmp.add(data);
                } else {
                    tmp.add("0");
                }
                System.out.println("day " +update.plusDays(i).getDayOfMonth()+" : " + tmp.get(i));
            finalList.add(tmp);
            }
            if (metricsNum == 4) {
                if(resultType == 1) {
                    for (List<String> string : finalList) {
                        int total = 0;
                        for (String num : string) {
                            int number = Integer.parseInt(num);
                            if(number != 0) {
                                total += number;
                            }
                        }
                        System.out.print(string + "  ");
                        string.clear();
                        string.add(Integer.toString(total));
                        System.out.println(string);
                    }
                }
                else if(resultType == 2){
                    int total = 0;
                    for (List<String> string : finalList) {
                        for (String num : string) {
                            int number = Integer.parseInt(num);
                            if(number != 0) {
                                total += number;
                            }
                        }
                    }

                    System.out.println("Death total: " + total);
                }
            }

            else if (metricsNum == 5 || metricsNum == 6) {
                if (resultType == 1) {               // calculate New total
                    if (metricsNum == 5) {
                        for (List<String> string : finalList) {
                            int total = 0;
                            for (String num : string) {
                                int number = Integer.parseInt(num);
                                if (number != 0) {
                                    total += number;
                                }
                            }
                            System.out.print(string + "  ");
                            string.clear();
                            string.add(Integer.toString(total));
                            System.out.println(string);
                        }
                    } else {
                        int vaccinatedNum = 0;

                        for (List<String> string : finalList) {
                            int total = 0;
                            for (String num : string) {
                                int nowVaccinated = Integer.parseInt(num);
                                if (nowVaccinated != 0) {
                                    total += nowVaccinated - vaccinatedNum;
                                    vaccinatedNum = nowVaccinated;
                                }
                            }
                            System.out.print(string + "  ");
                            string.clear();
                            string.add(Integer.toString(total));
                            System.out.println(string);
                        }
                    }
                } else if (resultType == 2) {          // calculate from beginning up to last group
                    if (metricsNum == 5) {
                        int total = 0;

                        for (List<String> string : finalList) {
                            for (String num : string) {
                                int number = Integer.parseInt(num);
                                if (number != 0) {
                                    total += number;
                                }
                            }
                        }

                        System.out.println("Death total: " + total);
                    }


                }
            }
        }
        else if  (groupType == 2) {
            int elementNum = tmpList.size() / groupDay;
            int remainder = tmpList.size() % groupDay;

            for (int i = 0; i < tmpList.size(); i++) {
                String data = tmpList.get(i).subList(metricsNum, metricsNum + 1).get(0);

                if (!data.equals("")) {
                    tmp.add(data);
                } else {
                    tmp.add("0");
                }

                if (tmp.size() == elementNum && finalList.size() < groupDay - remainder) {
                    finalList.add(tmp);
                    tmp = new ArrayList<>();
                } else if (tmp.size() == elementNum && finalList.size() >= groupDay - remainder && !tmpList.get(i + 1).subList(metricsNum, metricsNum + 1).get(0).equals("")) {
                    tmp.add(tmpList.get(++i).subList(metricsNum, metricsNum + 1).get(0));
                    finalList.add(tmp);
                    tmp = new ArrayList<>();
                } else if (tmp.size() == elementNum && finalList.size() >= groupDay - remainder && tmpList.get(i+1).subList(metricsNum, metricsNum + 1).get(0).equals("")){
                    tmp.add("0");
                    i++;
                    finalList.add(tmp);
                    tmp = new ArrayList<>();

                }

            }

            if (metricsNum == 4) {
                if(resultType == 1) {
                    for (List<String> string : finalList) {
                        int total = 0;
                        for (String num : string) {
                            int number = Integer.parseInt(num);
                            if(number != 0) {
                                total += number;
                            }
                        }
                        System.out.print(string + "  ");
                        string.clear();
                        string.add(Integer.toString(total));
                        System.out.println(string);
                    }
                }
                else if(resultType == 2){
                    int total = 0;
                    for (List<String> string : finalList) {
                        for (String num : string) {
                            int number = Integer.parseInt(num);
                            if(number != 0) {
                                total += number;
                            }
                        }
                    }

                    System.out.println("Death total: " + total);
                }
            }

            else if (metricsNum == 5 || metricsNum == 6) {
                if(resultType == 1) {               // calculate New total
                    if(metricsNum == 5) {
                        for (List<String> string : finalList) {
                            int total = 0;
                            for (String num : string) {
                                int number = Integer.parseInt(num);
                                if(number != 0) {
                                    total += number;
                                }
                            }
                            System.out.print(string + "  ");
                            string.clear();
                            string.add(Integer.toString(total));
                            System.out.println(string);
                        }
                    }
                    else {
                        int vaccinatedNum = 0;

                        for (List<String> string : finalList) {
                            int total = 0;
                            for (String num : string) {
                                int nowVaccinated = Integer.parseInt(num);
                                if(nowVaccinated != 0) {
                                    total += nowVaccinated - vaccinatedNum;
                                    vaccinatedNum = nowVaccinated;
                                }
                            }
                            System.out.print(string + "  ");
                            string.clear();
                            string.add(Integer.toString(total));
                            System.out.println(string);
                        }
                    }
                }
                else if(resultType == 2) {          // calculate from beginning up to last group
                    if(metricsNum == 5) {
                        int total = 0;

                        for (List<String> string : finalList) {
                            for (String num : string) {
                                int number = Integer.parseInt(num);
                                if(number != 0) {
                                    total += number;
                                }
                            }
                        }

                        System.out.println("Death total: " + total);
                    }

                    else {
                        int vaccinatedNum = 0;
                        int total = 0;

                        for (List<String> string : finalList) {
                            for (String num : string) {
                                int nowVaccinated = Integer.parseInt(num);
                                if(nowVaccinated != 0) {
                                    total += nowVaccinated - vaccinatedNum;
                                    vaccinatedNum = nowVaccinated;
                                }
                            }
                        }

                        System.out.println("Vaccinated total: " + total);
                    }

                }

            }
        }
        else if (groupType == 3) {
            if(tmpList.size() % groupDay != 0) {
                System.out.println("ERROR: Can't divide groups equally!"); }
            else {
                for (List<String> strings : tmpList) {
                    tmp.add(strings.subList(metricsNum, metricsNum+1).get(0));
                    if (tmp.size() == groupDay) {
                        finalList.add(tmp);
                        tmp = new ArrayList<>();
                    }
                }
            }
        }
        return finalList;
    }
}




