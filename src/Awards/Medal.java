package Awards;

import java.time.LocalDateTime;

public class Medal {
    private String name;
    private String milestone;
    private String type;

    public Medal(String name, String milestone, String type) {
        this.name = name;
        this.milestone = milestone;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getMilestone() {
        return milestone;
    }

    public String getType() {
        return type;
    }
}

