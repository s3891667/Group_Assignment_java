package com.company;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



public class Main {
    public static void main(String[] args) throws IOException{
            Scanner scanner = new Scanner(System.in);
        System.out.println("please enter");
            String user = scanner.nextLine();
            String input_country = scanner.nextLine();
            String input_continent = scanner.nextLine();
            String input_time_range = scanner.nextLine();
            if (user.equals("ok")){
                Data inputdata = new Data(input_continent,input_time_range,input_country);
                inputdata.Data_division();
            }

    }


}
