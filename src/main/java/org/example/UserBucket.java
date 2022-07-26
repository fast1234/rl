package org.example;

import java.util.HashMap;

public class UserBucket {
    SlidingWindowRateLimiter sl;

    HashMap<String, SlidingWindowRateLimiter> hm;
    public UserBucket(){
        hm = new HashMap<>();
    }

    public void addUser(String id, int capacity, int timewindow){
        sl = new SlidingWindowRateLimiter(capacity, timewindow);
        hm.put(id, sl);
    }
    public boolean allowAccess(String id){
        if(hm.get(id).shouldGrantAccess()){
            System.out.println("Request granted");
            return true;
        }
        else {
            System.out.println("limit reached");
            return false;
        }
    }
}
