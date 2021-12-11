import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class summary {
    public static void main(String[] args) {
        FileReader fileName = null;
        BufferedReader br = null;
        List<List<String>> data = new ArrayList<>();
        File file = new File("src\\covid-data.csv");


        try {
            int num = 0;
            String line = "";
            Scanner scanMain= new Scanner(System.in);
            Scanner scanSub = new Scanner(System.in);
            fileName= new FileReader(file.getCanonicalPath());
            br = new BufferedReader(fileName);

            while ((line = br.readLine()) != null) {    // input a specific number and divides it into the number of groups equal to that number

                String array[] = line.split(",");
                List<String> temp = new ArrayList<>();
                temp = Arrays.asList(array);
                data.add(temp);

            }

            System.out.println(data.get(0));

            while (true) {
                System.out.print("1.Area  2.Range: ");
                num = scanMain.nextInt();

                if (num == 1) {
                    System.out.print("1.Country  2.Continent: ");
                    int num2 = scanMain.nextInt();

                    if (num2 == 1) {    // input country name and print if it exists
                        System.out.print("Country name: ");
                        String areaName = scanSub.nextLine();
                        for (int i = 1; i < data.size(); i++) {
                            if (data.get(i).get(2).equals(areaName)) {
                                System.out.println(data.get(i));
                            }
                        }
                        continue;
                    }

                    else if (num2 == 2) {       // input the name of the continent and output if it exists
                        System.out.print("Continent name: ");
                        String areaName = scanSub.nextLine();
                        for (int i = 1; i < data.size(); i++) {
                            if (data.get(i).get(1).equals(areaName)) {
                                System.out.println(data.get(i));
                            }
                        }
                        continue;
                    }
                }
                else if (num == 2) {
                    System.out.println("Time range: ");
                }

                else if (num == 3) {
                    break;
                }

            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(br != null) {
                    br.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
