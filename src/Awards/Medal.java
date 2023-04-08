package Awards;

import java.time.LocalDateTime;

public class Medal {
    private String name;
    private int recipientUID;
    private String milestone;
    private String dateAwarded;
    private String citation;
    private String operationName;
    private String type;

    public Medal(String name, int recipientUID, String milestone, String dateAwarded, String citation, String operationName, String type) {
        this.name = name;
        this.recipientUID = recipientUID;
        this.milestone = milestone;
        this.dateAwarded = dateAwarded;
        this.citation = citation;
        this.operationName = operationName;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecipient(int recipientUID) {
        this.recipientUID = recipientUID;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public void setDateAwarded(String dateAwarded) {
        this.dateAwarded = dateAwarded;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getRecipient() {
        return recipientUID;
    }

    public String getMilestone() {
        return milestone;
    }

    public String getDateAwarded() {
        return dateAwarded;
    }

    public String getCitation() {
        return citation;
    }

    public String getOperationName() {
        return operationName;
    }

    public String getType() {
        return type;
    }
}

