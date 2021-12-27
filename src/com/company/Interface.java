package com.company;
import java.util.Scanner;

public class Interface {


    public void Intro_interface(){
        System.out.println(" ____________________________________________________________________________________________________");
        System.out.println("|                                                                                                    |\t");
        System.out.println("                                  COVID-19 COMMUNITY MOBILITY REPORT                                  \t");
        System.out.println("                                               GROUP 07                                               \t");
        System.out.println("|____________________________________________________________________________________________________|");
    }
    public void Start_Stop_Program(){
        System.out.println(" ____________________________________________________________________________________________________");
        System.out.println("|                                                                                                    |");
        System.out.println("                               Do you want to start the program? (Y/N)                                ");
        System.out.println("                                       Y: Yes     N: No                                               ");
        System.out.println("|____________________________________________________________________________________________________|");


    }
    public int input_two_option() {
        Scanner scanner2 = new Scanner(System.in);
        int number_output2 = scanner2.nextInt();
        while (number_output2 < 1 || number_output2 > 2) {
            System.out.println(" ____________________________________________________________________________________________________");
            System.out.println("|                                                                                                    |\t");
            System.out.println("                                      INVALID NUMBER                                                  \t");
            System.out.println("                                     PLEASE TRY AGAIN!                                                \t");
            System.out.println("|____________________________________________________________________________________________________|");

            number_output2 = scanner2.nextInt();
        }
        return number_output2;
    }
    public int input_three_option(){
            Scanner scanner3=new Scanner(System.in);
            int number_output3=scanner3.nextInt();
            while (number_output3<1||number_output3>3){
                System.out.println(" ____________________________________________________________________________________________________");
                System.out.println("|                                                                                                    |\t");
                System.out.println("                                      INVALID NUMBER                                                  \t");
                System.out.println("                                     PLEASE TRY AGAIN!                                                \t");
                System.out.println("|____________________________________________________________________________________________________|");

                number_output3=scanner3.nextInt();
            }
            return number_output3;
    }
    public int Interface_management(String text1, String text2,String text3) {
        if (text3 == null) {
            System.out.println();
            System.out.println(" ____________________________________________________________________________________________________");
            System.out.println("|                                                                                                    |");
            System.out.println("                Please choose number 1 to input " + text1 + "                       ");
            System.out.println("                Please choose number 2 to input " + text2 + "                       ");
            System.out.println("|____________________________________________________________________________________________________|");
            System.out.println();
            return input_two_option();
        } else System.out.println();
        System.out.println(" ____________________________________________________________________________________________________");
        System.out.println("|                                                                                                    |");
        System.out.println("              Please choose number 1 to input " + text1 + "                       ");
        System.out.println("              Please choose number 2 to input " + text2 + "                       ");
        System.out.println("              Please choose number 3 to input " + text3 + "                       ");
        System.out.println("|____________________________________________________________________________________________________|");
        return input_three_option();
    }
    public String Enter_name(String name){
        System.out.println();
        System.out.println(" ____________________________________________________________________________________________________");
        System.out.println("|                                                                                                    |\t");
        System.out.println("                                     Enter a "+ name+"                                                 \t");
        System.out.println("|____________________________________________________________________________________________________|");
        System.out.println();
        Scanner scanner_name= new Scanner(System.in);
        name=scanner_name.nextLine();
        return name;
    }
    public void Interface_text(String name){
        System.out.println();
        System.out.println(" ____________________________________________________________________________________________________");
        System.out.println("|                                                                                                    |\t");
        System.out.println("                                     Enter a "+ name+"                                                 \t");
        System.out.println("|____________________________________________________________________________________________________|");
        System.out.println();
    }

    public void end_program(String name){
        System.out.println();
        System.out.println(" ____________________________________________________________________________________________________");
        System.out.println("|                                                                                                    |\t");
        System.out.println("                                       "+ name+"                                                   \t");
        System.out.println("|____________________________________________________________________________________________________|");
        System.out.println();
    }
}
