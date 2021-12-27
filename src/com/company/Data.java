package com.company;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data extends Main {
    private LocalDate startDate;
    private  LocalDate endDate;
    private  List<List<String>> tmpList;
    private  int groupType, groupDay, metricsNum, resultType,display_type;
    int total = 0;
    List<String> tmp = new ArrayList<>();

    ArrayList<Integer> cases_list = new ArrayList<>();
    List<List<String>> finalList = new ArrayList<>();


    public Data(LocalDate startDate,LocalDate endDate, List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType, int display_type) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.tmpList = tmpList;
        this.groupType = groupType;
        this.groupDay = groupDay;
        this.metricsNum = metricsNum;
        this.resultType = resultType;
        this.display_type = display_type;}

    public Data(ArrayList<Integer> cases_list) {
        this.cases_list = cases_list;}

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<List<String>> getTmpList() {
        return tmpList;
    }

    public int getGroupDay() {
        return groupDay;
    }

    public int getDisplay_type() {
        return display_type;
    }

    public int getGroupType() {
        return groupType;
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

    public void dataGroup(List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType) {
        get_types processing = new get_types( getStartDate(), getEndDate(),  tmpList,  groupType,  groupDay,  metricsNum, resultType,getDisplay_type());
        processing.check_metrics();
        processing.displaying_chart(getDisplay_type(),getStartDate(),getEndDate());}

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
            cases_list.add(total);}
        System.out.print("\nGrouped Data : ");
        return cases_list;
    }

    public static ArrayList<Integer> new_total(List<List<String>> finalList, int total, ArrayList<Integer> cases_list) {
        for (List<String> string : finalList) {
            for (String num : string) {
                int number = Integer.parseInt(num);
                if (number != 0) {
                    total += number;}
            }
            System.out.print(string + "  ");
            System.out.println(total + " ");
            cases_list.add(total);
            total = 0;}
        System.out.print("\nGrouped Data : ");
        return cases_list;
    }

    public static void grouping1(List<List<String>> tmpList, List<String> tmp, int metricsNum, List<List<String>> finalList) {
        for (List<String> stringList : tmpList) {
            String data = stringList.subList(metricsNum, metricsNum + 1).get(0);
            if (!data.equals("")) {
                tmp.add(data);
            } else {
                tmp.add("0");
            }
            finalList.add(tmp);
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

    public static void grouping3( List<List<String>> tmpList,List<List<String>> finalList ,int groupDay, int metricsNum,List<String> tmp){
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
    }
}


abstract class information_processor extends data_display {
    public information_processor(LocalDate startDate, LocalDate endDate, List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType, int display_type) {
        super(startDate, endDate, tmpList, groupType, groupDay, metricsNum, resultType, display_type);}
}

class get_types extends information_processor{
    public get_types(LocalDate startDate, LocalDate endDate, List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType, int display_type) {
        super(startDate, endDate, tmpList, groupType, groupDay, metricsNum, resultType, display_type);}

    public void result_checker(){
         if(getResultType() ==1){
             System.out.println(new_total(finalList,total, cases_list));
         }
         else if(getResultType() == 2){
             System.out.println(up_to(finalList,total, cases_list));
         }
    }

    public  void check_metrics (){
        if(getMetricsNum() ==4){
            System.out.println("Infected cases : ");}
        else if (getMetricsNum() == 5){
            System.out.println("Death cases : ");}
        else if (getMetricsNum() ==6){
            System.out.println("vaccinated : ");}
        grouping_type();}


    public void grouping_type(){
        if( getGroupType() ==1){
            grouping1(getTmpList(),tmp,getMetricsNum(),  finalList);}
        else if (getGroupType() == 2){
            grouping2( tmp, getGroupDay(),  finalList,  getMetricsNum(),  getTmpList());}

        else if ( getGroupType()==3){
            if (getTmpList().size() % getGroupDay() != 0) {
                System.out.println("ERROR: Can't divide groups equally!");}
            grouping3( getTmpList(), finalList , getGroupDay(), getMetricsNum(), tmp);}
        result_checker();
    }

    public void displaying_chart(int display_type, LocalDate startDate, LocalDate endDate){
        if( display_type == 1){
            Display.tabular(startDate,endDate,finalList,cases_list);}
        else if ( display_type == 2){
            Display.chart(cases_list);
        }

    }





}









