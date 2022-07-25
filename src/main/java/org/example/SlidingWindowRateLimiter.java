package org.example;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter{
    int capacity;
    int timeWindow;

    Queue<Long> timestampQueue;
    public SlidingWindowRateLimiter(int capacity, int timeWindow){
        this.capacity = capacity;
        this.timeWindow = timeWindow;
        timestampQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean shouldGrantAccess() {
        Long currentTime = System.currentTimeMillis()/1000;
        if(timestampQueue.size()<capacity){
            timestampQueue.add(currentTime);
            return true;
        }
        trimQueue(currentTime);
        if(timestampQueue.size()<capacity){
            timestampQueue.add(currentTime);
            return true;
        }
        return false;
    }

    private void trimQueue(long currentTime){
        long timeDiff = timestampQueue.peek()-currentTime;
        while(timeDiff>=timeWindow){
            timestampQueue.poll();
            if(timestampQueue.isEmpty()){
                return;
            }
            timeDiff = timestampQueue.peek()-currentTime;
        }
    }
}
