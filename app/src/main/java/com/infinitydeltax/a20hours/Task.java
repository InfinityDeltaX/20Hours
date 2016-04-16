package com.infinitydeltax.a20hours;

/**
 * Created by Robert on 4/16/2016.
 */
public class Task {
    private String name;
    private int timeRemaining;
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

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
