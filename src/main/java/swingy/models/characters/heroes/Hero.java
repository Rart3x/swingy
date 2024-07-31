package swingy.models.characters.heroes;

import swingy.models.artefacts.Artefact;
import swingy.models.characters.AIndividual;
import swingy.utils.Utils;

public class Hero extends AIndividual {
    protected Artefact armor;
    protected Artefact helm;
    protected Artefact weapon;

    protected int experience;
    protected int attack, defense, hitPoints;

    public Hero(String name, int attack, int defense, int hitPoints)
    {
        super(name, "Hero", 1);
        this.experience = 0;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    public void gainExperience(int experience)
    {
        double maxExperience = (this.level * 1000) + Math.pow((this.level - 1), 2) * 450;

        if ((this.experience + experience) >= maxExperience)
        {
            this.experience = (this.experience + experience) - (int)maxExperience;
            this.level += 1;

            Utils.printGreen(this.name + " leveled up to level " + this.level);
        }
        else
            this.experience += experience;
    }

    public void looseHitPoints(int hitPoints)
    {
        if (this.hitPoints - hitPoints <= 0)
            this.hitPoints = 0;
        else
            this.hitPoints -= hitPoints;
    }


    public int      getExperience() { return experience; }
    public int      getAttack() { return attack; }
    public int      getDefense() { return defense; }
    public int      getHitPoints() { return hitPoints; }
    public Artefact getArmor() { return armor; }
    public Artefact getHelm() { return helm; }
    public Artefact getWeapon() { return weapon; }

    public void setArmor(Artefact armor) { this.armor = armor; }
    public void setHelm(Artefact helm) { this.helm = helm; }
    public void setWeapon(Artefact weapon) { this.weapon = weapon; }


}
