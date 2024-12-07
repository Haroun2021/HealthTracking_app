package com.healthtracker;

public class HealthGoal {
    private String goalName;
    private int progress;
    private int target;

    public HealthGoal(String goalName, int progress, int target) {
        this.goalName = goalName;
        this.progress = progress;
        this.target = target;
    }

    public String getGoalName() { return goalName; }
    public int getProgress() { return progress; }
    public int getTarget() { return target; }
    public void setProgress(int progress) { this.progress = progress; }
}