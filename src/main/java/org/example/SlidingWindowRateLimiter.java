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
        Long currentTime = System.currentTimeMillis();
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
        long timeDiff = (currentTime-timestampQueue.peek())/1000;
        while(timeDiff>=timeWindow){
            timestampQueue.poll();
            if(timestampQueue.isEmpty()){
                return;
            }
            timeDiff = (currentTime-timestampQueue.peek())/1000;
        }
    }

    public void updateCapacity(int capacity){
        this.capacity = capacity;
    }
}
