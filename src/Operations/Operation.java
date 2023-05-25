package Operations;

import Player.*;

import java.util.ArrayList;
import java.time.*;

public class Operation {
    private String name;
    private String type;
    private String branches;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String objective;
    private String plan;
   // private ArrayList<Soldier> manpower;

    private int commander;

    public Operation(String name, String type, String branches, LocalDateTime startTime, LocalDateTime endTime, String objective, String plan, int commander) {
        this.name = name;
        this.type = type;
        this.branches = branches;
        this.startTime = startTime;
        this.endTime = endTime;
        this.objective = objective;
        this.plan = plan;
        this.commander = commander;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBranches(String branches) {
        this.branches = branches;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setCommander(int commander) {
        this.commander = commander;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getBranches() {
        return branches;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getObjective() {
        return objective;
    }

    public String getPlan() {
        return plan;
    }

    public int getCommander() {
        return commander;
    }
}
