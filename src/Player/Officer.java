package Player;

public class Officer extends Soldier {
    private int infantryCommandSkill;
    private int tankCommandSkill;
    private int artilleryCommandSkill;
    private int navyCommandSkill;
    private int logisticsCommandSkill;

    public Officer(String username, String discordTag, String steamUrl, String rankType, int rank, String specialization, int skill, int infantryCommandSkill, int tankCommandSkill, int artilleryCommandSkill, int navyCommandSkill, int logisticsCommandSkill) {
        super(-1, username, discordTag, steamUrl, rankType, rank, specialization, skill);
        this.infantryCommandSkill = infantryCommandSkill;
        this.tankCommandSkill = tankCommandSkill;
        this.artilleryCommandSkill = artilleryCommandSkill;
        this.navyCommandSkill = navyCommandSkill;
        this.logisticsCommandSkill = logisticsCommandSkill;
    }

    public Officer(Soldier soldier, int infantryCommandSkill, int tankCommandSkill, int artilleryCommandSkill, int navyCommandSkill, int logisticsCommandSkill) {
        super(soldier.getUID(), soldier.getUsername(), soldier.getDiscordTag(), soldier.getSteamUrl(), soldier.getRankType(), soldier.getRank(), soldier.getSpecialization(), soldier.getSkill());
        this.infantryCommandSkill = infantryCommandSkill;
        this.tankCommandSkill = tankCommandSkill;
        this.artilleryCommandSkill = artilleryCommandSkill;
        this.navyCommandSkill = navyCommandSkill;
        this.logisticsCommandSkill = logisticsCommandSkill;
    }
    @Override
    public void promote() {

        if (rank < 7) {
            rank++;
        }
    }
    @Override
    public void demote() {
        if (rank > 1) {
            rank--;
        }
    }

    public void increaseCommandSkill(String skill, int amount) {
        switch(skill) {
            case "Infantry":
                this.infantryCommandSkill += amount;
                break;
            case "Tank":
                this.tankCommandSkill += amount;
                break;
            case "Artillery":
                this.artilleryCommandSkill += amount;
                break;
            case "Navy":
                this.navyCommandSkill += amount;
                break;
            case "Logistics":
                this.logisticsCommandSkill += amount;
                break;
            default:
                System.out.println("Invalid skill");
                break;
        }
    }


    // setters for the additional attributes

    public int getInfantryCommandSkill() {
        return infantryCommandSkill;
    }

    public int getTankCommandSkill() {
        return tankCommandSkill;
    }

    public int getArtilleryCommandSkill() {
        return artilleryCommandSkill;
    }

    public int getNavyCommandSkill() {
        return navyCommandSkill;
    }

    public int getLogisticsCommandSkill() {
        return logisticsCommandSkill;
    }


    // getters for the additional attributes
    public void setInfantryCommandSkill(int infantryCommandSkill) {
        this.infantryCommandSkill = infantryCommandSkill;
    }

    public void setTankCommandSkill(int tankCommandSkill) {
        this.tankCommandSkill = tankCommandSkill;
    }

    public void setArtilleryCommandSkill(int artilleryCommandSkill) { this.artilleryCommandSkill = artilleryCommandSkill;}

    public void setNavyCommandSkill(int navyCommandSkill) {
        this.navyCommandSkill = navyCommandSkill;
    }

    public void setLogisticsCommandSkill(int logiCommandSkill) {
        this.logisticsCommandSkill = logiCommandSkill;
    }
}