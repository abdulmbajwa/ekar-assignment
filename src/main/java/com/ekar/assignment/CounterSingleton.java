package com.ekar.assignment;

import lombok.Synchronized;

public class CounterSingleton {
    private static CounterSingleton instance;
    private int count = 50;

    public static synchronized CounterSingleton getInstance() {
        if (instance == null) {
            instance = new CounterSingleton();
        }
        return instance;
    }

    @Synchronized
    public void reset(int value) {
        count = value;
    }

    @Synchronized
    public int getCount() {
        return count;
    }

    @Synchronized
    public int increaseCounter() {
        count++;
        return count;
    }

    @Synchronized
    public int decreaseCounter() {
        count--;
        return count;
    }
}
