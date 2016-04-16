package com.infinitydeltax.a20hours;

import java.io.Serializable;

/**
 * Created by Robert on 4/16/2016.
 */
public class Task implements Serializable{
    private String name;
    private long timeRemaining;
    private String description;

    public Task(String name, int timeRemaining, String description) {
        this.name = name;
        this.timeRemaining = timeRemaining;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
