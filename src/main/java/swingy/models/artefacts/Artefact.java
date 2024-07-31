package swingy.models.artefacts;

public class Artefact {
    protected int attack, defense, hitPoints;
    protected String name, type;


    public Artefact(String name, String type, int attack, int defense, int hitPoints)
    {
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getHitPoints() { return hitPoints; }

    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
}
