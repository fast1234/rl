package org.example;

public class Main {
    public static void main(String[] args) {

        UserBucket ub = new UserBucket();
        ub.addUserBucket("c1", 2, 1);
        for(int i=0;i<10;i++){
            System.out.println(ub.allowAccess("c1"));
        }
    }
}