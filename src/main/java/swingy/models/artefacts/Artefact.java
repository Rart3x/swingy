package swingy.models.artefacts;

public class Artefact {
    private int attack, defense, hitPoints;

    public Artefact(int attack, int defense, int hitPoints)
    {
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getHitPoints() { return hitPoints; }

    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
}
