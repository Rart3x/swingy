package swingy.models.characters.villains;

import swingy.models.characters.AIndividual;

public class Villain extends AIndividual {
    private int attack, defense, hitPoints;

    public Villain(String name, int level, int attack, int defense, int hitPoints)
    {
        super(name, "Villain", level);
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    public void looseHitPoints(int hitPoints)
    {
        if (this.hitPoints - hitPoints < 0)
            this.hitPoints = 0;
        else
            this.hitPoints -= hitPoints;
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getHitPoints() { return hitPoints; }

    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
}
