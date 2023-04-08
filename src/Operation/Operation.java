package Operation;

import Player.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Operation {
    private String name;
    private String type;
    private ArrayList<String> branches;
    private String startTime;
    private String endTime;
    private String objective;
    private String plan;
    private ArrayList<Soldier> manpower;

    private int commander;

    public Operation(String name, String type, ArrayList<String> branches, String startTime, String endTime, String objective, String plan, ArrayList<Soldier> manpower, int commander) {
        this.name = name;
        this.type = type;
        this.branches = branches;
        this.startTime = startTime;
        this.endTime = endTime;
        this.objective = objective;
        this.plan = plan;
        this.manpower = manpower;
        this.commander = commander;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBranches(ArrayList<String> branches) {
        this.branches = branches;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
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

    public ArrayList<String> getBranches() {
        return branches;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getObjective() {
        return objective;
    }

    public String getPlan() {
        return plan;
    }

    public ArrayList<Soldier> getManpower() {
        return manpower;
    }

    public int getCommander() {
        return commander;
    }
}
