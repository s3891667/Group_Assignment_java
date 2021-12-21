package com.company;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data extends Main{
    private final LocalDate startDate;
    private final List<List<String>> tmpList;
    int groupType, groupDay, metricsNum,resultType;
    List<List<String>> finalList = new ArrayList<>();
    int total = 0;
    List<String> tmp = new ArrayList<>();
    LocalDate update;


    public Data( LocalDate startDate, List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType ) {
        this.startDate = startDate;
        this.tmpList = tmpList;
        this.groupType = groupType;
        this.groupDay = groupDay;
        this.metricsNum = metricsNum;
        this.resultType = resultType;
    }


    public void dataGroup() {

        if (groupType == 1 ){
            update = startDate;
            data_display.filling_blanks(tmpList,tmp,metricsNum);

            finalList.add(tmp);
            for(int i = 0 ; i < tmpList.size(); i++ ) {
                System.out.println("day " + update.plusDays(i).getDayOfMonth() + " : " + tmp.get(i));
            }
            if (metricsNum == 4) {
                if(resultType == 1) {
                    System.out.println(finalList);
                }
                else if(resultType == 2){

                    System.out.println("Total infected : " + data_display.Looping_final_list_type2(finalList,total));
                }
            }
            else if (metricsNum == 5) {
                if (resultType == 1) {               // calculate New total
                    System.out.println(finalList);
                } else if (resultType == 2) {
                    System.out.println("Death total: " + data_display.Looping_final_list_type2(finalList,total));
                }

            }

            else if (metricsNum == 6){
                if(resultType == 1) {               // calculate New total
                    System.out.println(finalList);
                }
                else if(resultType == 2) {// calculate from beginning up to last group
                        System.out.println("Vaccinated total: " + data_display.Looping_final_list_type2(finalList,total));
                }

                }
            }


        else if (groupType == 2) {

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
                } else if (tmp.size() == elementNum && finalList.size() >= groupDay - remainder && tmpList.get(i + 1).subList(metricsNum, metricsNum + 1).get(0).equals("")) {
                    tmp.add("0");
                    i++;
                    finalList.add(tmp);
                    tmp = new ArrayList<>();
                }
            }


            if (metricsNum == 4) {
                if (resultType == 1) {
                    data_display.Looping_final_list_type1(finalList,total);
                } else if (resultType == 2) {
                    data_display.Looping_final_list_type1(finalList,total);
                    System.out.println("Total infected: " + data_display.Looping_final_list_type2(finalList,total));
                }
            }

            else if(metricsNum == 5 ){
                if (resultType == 1) {
                    data_display.Looping_final_list_type1(finalList,total);
                } else if (resultType == 2) {
                    data_display.Looping_final_list_type1(finalList,total);
                    System.out.println("Death total: " + data_display.Looping_final_list_type2(finalList,total));
                }

            }


            else if (metricsNum == 6) {

                if (resultType == 1) {               // calculate New total
                    data_display.Looping_final_list_type1(finalList,total);

                } else if (resultType == 2) {          // calculate from beginning up to last group
                   data_display.Looping_final_list_type1(finalList,total);
                    System.out.println("Vaccinated total: " + data_display.Looping_final_list_type2(finalList,total));
                }

            }
        }

        else if (groupType == 3) {

            if (tmpList.size() % groupDay != 0) {
                System.out.println("ERROR: Can't divide groups equally!");
            }
            else {
                int elementNum = tmpList.size() / groupDay;
                for (List<String> strings : tmpList) {
                    String data = strings.subList(metricsNum, metricsNum + 1).get(0);
                    if (!data.equals("")) {
                        tmp.add(data);
                    } else {
                        tmp.add("0");
                    }
                    if (tmp.size() == elementNum) {
                        finalList.add(tmp);
                        tmp = new ArrayList<>();
                    }

                }

                if(metricsNum == 4){
                    if (resultType == 1) {
                        data_display.Looping_final_list_type1(finalList,total);
                    } else if (resultType == 2) {
                        data_display.Looping_final_list_type1(finalList,total);
                        System.out.println("Total infected: " + data_display.Looping_final_list_type2(finalList,total));
                    }
                }

                else if (metricsNum == 5) {
                    if (resultType == 1) {
                        data_display.Looping_final_list_type1(finalList,total);
                    } else if (resultType == 2) {
                        data_display.Looping_final_list_type1(finalList,total);
                        System.out.println("Death total: " + data_display.Looping_final_list_type2(finalList,total));
                    }
                }

                else if (metricsNum == 6) {
                    if (resultType == 1) {               // calculate New total
                        data_display.Looping_final_list_type1(finalList,total);

                    } else if (resultType == 2) {          // calculate from beginning up to last group
                        data_display.Looping_final_list_type1(finalList,total);
                        System.out.println("Vaccinated total: " + data_display.Looping_final_list_type2(finalList,total));
                    }
                }
            }
        }
    }
}

class data_display extends Data {
    public data_display(LocalDate startDate, List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType) {
        super(startDate, tmpList, groupType, groupDay, metricsNum, resultType);
    }

    public static void Looping_final_list_type1 (List<List<String>> finalList,int total){
        ArrayList <Integer> cases_list = new ArrayList<>();
        for (List<String> string : finalList) {
            for (String num : string) {
                int number = Integer.parseInt(num);
                if(number != 0) {
                    total += number;
                }
            }
            System.out.print(string + "  ");
            string.clear();
            string.add(Integer.toString(total));
            System.out.println(string + " ");
            cases_list.add(total);
            total = 0;

        }
        System.out.println(cases_list);
    }

    public static  int Looping_final_list_type2 (List<List<String>> finalList,int total){
        for (List<String> string : finalList) {
            for (String num : string) {
                int number = Integer.parseInt(num);
                if(number != 0) {
                    total += number;
                }
            }
        }
        return total;
    }



    public static void filling_blanks(List<List<String>> tmpList, List<String> tmp, int metricsNum){
        for (List<String> stringList : tmpList) {
            String data = stringList.subList(metricsNum, metricsNum + 1).get(0);
            if (!data.equals("")) {
                tmp.add(data);
            } else {
                tmp.add("0");
            }
        }

    }
}







