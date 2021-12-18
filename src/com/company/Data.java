package com.company;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data extends Main{
    private final LocalDate startDate;
    private final List<List<String>> tmpList;
    int groupType, groupDay, metricsNum,resultType;
    List<List<String>> finalList = new ArrayList<>();

    public Data( LocalDate startDate, List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType ) {
        this.startDate = startDate;
        this.tmpList = tmpList;
        this.groupType = groupType;
        this.groupDay = groupDay;
        this.metricsNum = metricsNum;
        this.resultType = resultType;


    }


    public void dataGroup(List<List<String>> tmpList, int groupType, int groupDay, int metricsNum, int resultType) {
        List<String> tmp = new ArrayList<>();

        int total = 0;
        LocalDate update;

        if (groupType == 1 ){
             update = startDate;
            for(int i = 0 ; i < tmpList.size(); i++ ){
                String data = tmpList.get(i).subList(metricsNum, metricsNum + 1).get(0);
                if (!data.equals("")) {
                    tmp.add(data);
                } else {
                    tmp.add("0");
                }
                System.out.println("day " +update.plusDays(i).getDayOfMonth()+" : " + tmp.get(i));
            }
            finalList.add(tmp);

            if (metricsNum == 4) {
                if(resultType == 1) {
                    System.out.println(finalList);
                }
                else if(resultType == 2){

                    System.out.println("Total infected : " + Looping_final_list(finalList));
                }
            }

            else if (metricsNum == 5 || metricsNum == 6) {
                if(resultType == 1) {               // calculate New total
                    System.out.println(finalList);
                }
                else if(resultType == 2) {// calculate from beginning up to last group
                    if(metricsNum == 5) {
                        System.out.println("Death total: " + Looping_final_list(finalList));
                    }

                    else {
                        int vaccinatedNum = 0;
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

            if (metricsNum == 4 || metricsNum == 5) {
                if (resultType == 1) {
                    for (List<String> string : finalList) {

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
                } else if (resultType == 2) {

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
            } else if (metricsNum == 6) {
                if (resultType == 1) {               // calculate New total
                    int vaccinatedNum = 0;

                    for (List<String> string : finalList) {

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
                        System.out.println(string + " ");
                    }

                } else if (resultType == 2) {          // calculate from beginning up to last group
                    int vaccinatedNum = 0;


                    for (List<String> string : finalList) {
                        for (String num : string) {
                            int nowVaccinated = Integer.parseInt(num);
                            if (nowVaccinated != 0) {
                                total += nowVaccinated - vaccinatedNum;
                                vaccinatedNum = nowVaccinated;
                            }
                        }
                    }
                    System.out.println("Vaccinated total: " + total);
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

                if (metricsNum == 4 || metricsNum == 5) {
                    if (resultType == 1) {
                        for (List<String> string : finalList) {

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
                    } else if (resultType == 2) {

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
                } else if (metricsNum == 6) {
                    if (resultType == 1) {               // calculate New total
                        int vaccinatedNum = 0;

                        for (List<String> string : finalList) {

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
                            System.out.println(string + " ");
                        }

                    } else if (resultType == 2) {          // calculate from beginning up to last group
                        int vaccinatedNum = 0;


                        for (List<String> string : finalList) {
                            for (String num : string) {
                                int nowVaccinated = Integer.parseInt(num);
                                if (nowVaccinated != 0) {
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
    }

    public static  int Looping_final_list (List<List<String>> finalList){
        int total = 0;
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

}







