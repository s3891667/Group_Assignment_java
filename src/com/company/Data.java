package com.company;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Data extends Main {
    private LocalDate startDate;
    private  LocalDate endDate;
    private  List<List<String>> tmpList;
    private  int groupType, groupDay, metricsNum, resultType,displaytype;
    int total = 0;
    List<String> tmp = new ArrayList<>();
    LocalDate update;
    ArrayList<Integer> cases_list = new ArrayList<>();
    List<List<String>> finalList = new ArrayList<>();


    public Data(LocalDate startDate,LocalDate endDate, List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType, int displaytype) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.tmpList = tmpList;
        this.groupType = groupType;
        this.groupDay = groupDay;
        this.metricsNum = metricsNum;
        this.resultType = resultType;
        this.displaytype = displaytype;
    }

    public Data(ArrayList<Integer> cases_list) {
        super();
    }

    public int getMetricsNum() {
        return metricsNum;
    }

    public int getResultType() {
        return resultType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }




}

class data_display extends  Data {

    public data_display(LocalDate startDate, LocalDate endDate, List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType, int displaytype) {
        super(startDate, endDate, tmpList, groupType, groupDay, metricsNum, resultType, displaytype);
    }
    public static ArrayList<Integer> up_to(List<List<String>> finalList, int total, ArrayList<Integer> cases_list) {
        for (List<String> string : finalList) {
            for (String num : string) {
                int number = Integer.parseInt(num);
                if (number != 0) {
                    total += number;
                }
            }
            System.out.print(string + "  ");
            System.out.println(total + " ");
            cases_list.add(total);

        }
        System.out.print("\nGrouped Data : ");
        return cases_list;
    }

    public static ArrayList<Integer> new_total(List<List<String>> finalList, int total, ArrayList<Integer> cases_list) {
        for (List<String> string : finalList) {
            for (String num : string) {
                int number = Integer.parseInt(num);
                if (number != 0) {
                    total += number;
                }
            }
            System.out.print(string + "  ");
            System.out.println(total + " ");
            cases_list.add(total);
            total = 0;

        }
        System.out.print("\nGrouped Data : ");
        return cases_list;
    }

    public static void grouping1(List<List<String>> tmpList, List<String> tmp, int metricsNum, List<List<String>> finalList, ArrayList<Integer> cases_list) {
        for (List<String> stringList : tmpList) {
            String data = stringList.subList(metricsNum, metricsNum + 1).get(0);
            if (!data.equals("")) {
                tmp.add(data);
            } else {
                tmp.add("0");
            }
            finalList.add(tmp);
            cases_list.add(Integer.valueOf(data));
            tmp = new ArrayList<>();
        }
    }


    public static void grouping2(List<String> tmp, int groupDay, List<List<String>> finalList, int metricsNum, List<List<String>> tmpList) {
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
    }


    public void dataGroup(List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType) {
        if (groupType == 1) {
            update = getStartDate();
            data_display.grouping1(tmpList, tmp, metricsNum, finalList, cases_list);
            if (getMetricsNum() == 4) {
                if (getResultType() == 1) {
                    System.out.println(data_display.new_total(finalList, total, cases_list));
                } else if (getResultType() == 2) {
                    System.out.println(data_display.up_to(finalList, total, cases_list));
                }
            } else if (getMetricsNum() == 5) {
                if (resultType == 1) {               // calculate New total
                    System.out.println(data_display.new_total(finalList, total, cases_list));
                } else if (resultType == 2) {
                    System.out.println(data_display.up_to(finalList, total, cases_list));
                }

            } else if (getMetricsNum() == 6) {
                if (getResultType() == 1) {               // calculate New total
                    System.out.println(data_display.new_total(finalList, total, cases_list));
                } else if (getResultType() == 2) {// calculate from beginning up to last group
                    System.out.println(data_display.up_to(finalList, total, cases_list));
                }
            }


        } else if (groupType == 2) {
            grouping2(tmp, groupDay, finalList, metricsNum, tmpList);

            if (metricsNum == 4) {
                if (resultType == 1) {
                    System.out.println(data_display.new_total(finalList, total, cases_list));
                } else if (resultType == 2) {
                    System.out.println(data_display.up_to(finalList, total, cases_list));

                }
            } else if (metricsNum == 5) {
                if (resultType == 1) {
                    System.out.println(data_display.new_total(finalList, total, cases_list));
                } else if (resultType == 2) {
                    System.out.println(data_display.up_to(finalList, total, cases_list));

                }

            } else if (metricsNum == 6) {
                if (resultType == 1) {               // calculate New total
                    System.out.println(data_display.new_total(finalList, total, cases_list));
                } else if (resultType == 2) {          // calculate from beginning up to last group
                    System.out.println(data_display.up_to(finalList, total, cases_list));
                }

            }
        } else if (groupType == 3) {
            if (tmpList.size() % groupDay != 0) {
                System.out.println("ERROR: Can't divide groups equally!");
            } else {
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
                if (metricsNum == 4) {
                    if (resultType == 1) {
                        System.out.println(data_display.new_total(finalList, total, cases_list));
                    } else if (resultType == 2) {
                        System.out.println(data_display.up_to(finalList, total, cases_list));
                    }
                } else if (metricsNum == 5) {
                    if (resultType == 1) {
                        System.out.println(data_display.new_total(finalList, total, cases_list));
                    } else if (resultType == 2) {
                        System.out.println(data_display.up_to(finalList, total, cases_list));

                    }
                } else if (metricsNum == 6) {
                    if (resultType == 1) {               // calculate New total
                        System.out.println(data_display.new_total(finalList, total, cases_list));

                    } else if (resultType == 2) {          // calculate from beginning up to last group
                        System.out.println(data_display.up_to(finalList, total, cases_list));
                    }
                }
            }
        }
    }

    public void displaying(int displaytype, LocalDate startDate, LocalDate endDate){
        if( displaytype == 1){
            Display.tabular(startDate,endDate,finalList,cases_list);
        }
        else if ( displaytype == 2){
                Display.Chart_theme();
                Display.Solving_the_data(cases_list);
                Display.display_chart_data();

        }
    }

}







