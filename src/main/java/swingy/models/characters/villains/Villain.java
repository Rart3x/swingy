package swingy.models.characters.villains;

import swingy.models.characters.AIndividual;
import swingy.utils.Utils;

public class Villain extends AIndividual {
    private int attack, defense, hitPoints;

    public Villain(String name, int level, int attack, int defense, int hitPoints)
    {
        super(name, "Villain", level);
        this.attack = attack + (level * 2);
        this.defense = defense + (level * 2);
        this.hitPoints = hitPoints + (level * 2);
    }

    public boolean looseHitPoints(int hitPoints, String heroName)
    {
        Utils.printBlue(heroName + " attacks " + this.name + " and deals " + hitPoints + " damage");

        if (this.hitPoints - hitPoints < 0)
        {
            Utils.printRed(this.name + " looses the fight and died");
            this.hitPoints = 0;
            return true;
        }
        else
            this.hitPoints -= hitPoints;
        return false;
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getHitPoints() { return hitPoints; }

    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
}
