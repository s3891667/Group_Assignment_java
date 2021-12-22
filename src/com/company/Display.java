package com.company;
import java.util.*;

public class Display {
    public static void main(String[] args) {
        ArrayList<Integer> List = new ArrayList<>();
        String[][] Oxy = new String[24][80];
        for (int x = 0; x < 24; x++) {
            for (int y = 0; y < 80; y++) {
                if (y == 0) {
                    Oxy[x][0] = "|";
                } else if (x == 23) {
                    Oxy[23][y] = "_";
                } else {
                    Oxy[x][y] = " ";
                }
                System.out.print(Oxy[x][y]);
            }
            System.out.println();
        }
        // getmaxValue.
        List.add(2);
        List.add(4);
        List.add(1);
        Collections.sort(List);
        int max= List.get(List.size()-1);
        System.out.println(max);
        // getminValue.
        int min= List.get(0);
        System.out.println(min);

        // represent data
        float valuerange = 22/(max-min+1);
        float distancebetweencolumn = 78/valuerange;
        int a;
        int b=1;

        for (int k = 0; k < List.toArray().length; k++) {
            if ((int)List.get(k) == min) {
                Oxy[22][b] = "*";
            } else {
                double valuePoint = (List.get(k) * valuerange) - min*valuerange;
                if (valuePoint < 1) {
                    valuePoint = 1;
                }
                a = (int) Math.ceil(23 - valuePoint);
                Oxy[a][b] = "*";
            }

            b += distancebetweencolumn;
        }
        System.out.println("---------------------------------- CHART TABLE ----------------------------------");
    }

}


