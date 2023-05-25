package Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Soldier {

    private static int recruitmentNumber = 100000;
    private final int UID;
    private String username;
    private String discordTag;
    private String steamUrl;
    protected String rankType;
    protected int rank;
    private String specialization;
    private int skill;

    public Soldier(int UID, String username, String discordTag, String steamUrl, String rankType, int rank, String specialization, int skill) {
        if(UID == -1) {
            this.UID = recruitmentNumber;
            recruitmentNumber++;
        }
        else this.UID = UID;
        this.username = username;
        this.discordTag = discordTag;
        this.steamUrl = steamUrl;
        this.rankType = rankType;
        this.rank = rank;
        this.specialization = specialization;
        this.skill = skill;
    }

    public void increaseSkill(int amount) {
        this.skill += amount;
    }

    public void promote() {
        if (rank < 7) {
            rank++;
        }
    }

    public void demote() {
        if (rank > 1) {
            rank--;
        }
    }

    public void increase_skill() {
        if (skill < 10) {
            skill++;
        }
    }

    public void decrease_skill() {
        if (skill > 1) {
            skill--;
        }
    }

    // setters for the attributes

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDiscordTag(String discordTag) {
        this.discordTag = discordTag;
    }

    public void setSteamUrl(String steamUrl) {
        this.steamUrl = steamUrl;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }


    // getters for the attributes

    public int getUID() {
        return UID;
    }

    public String getUsername() {
        return username;
    }

    public String getDiscordTag() {
        return discordTag;
    }

    public String getSteamUrl() {
        return steamUrl;
    }

    public String getRankType() {
        return rankType;
    }

    public int getRank() {
        return rank;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getSkill() {
        return skill;
    }


}

