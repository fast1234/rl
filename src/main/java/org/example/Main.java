package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{


        UserBucket ub = registerUsers();

        for(int i=0;i<10;i++){
            ub.allowAccess("C1");
           // Thread.sleep(500);
        }
    }

    public static UserBucket registerUsers() throws FileNotFoundException {
        UserBucket ub = new UserBucket();
        File inputFile = new File("src/main/resources/input.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    if (!(line.isEmpty()))
                        parseTextInput(ub, line.trim());
                }
            } catch (IOException ex) {
                System.out.println("Error in reading the input file.");
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the path specified.");
            e.printStackTrace();
        }
        return ub;
    }

    public static UserBucket parseTextInput(UserBucket ub, String line){
        String[] arr = line.split(" ");

        ub.addUser(arr[0],Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
        return ub;
    }
}