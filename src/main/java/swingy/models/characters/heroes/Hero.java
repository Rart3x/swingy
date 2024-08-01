package swingy.models.characters.heroes;

import javax.validation.constraints.*;

import swingy.models.artefacts.Artefact;
import swingy.models.characters.AIndividual;
import swingy.utils.PrintUtils;

public class Hero extends AIndividual {
    @NotNull(message = "Name cannot be empty")
    protected String name;

    @NotNull(message = "Subclass cannot be empty")
    protected String subClass;

    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = Integer.MAX_VALUE, message = "Experience exceeds maximum allowed value")
    protected int experience;

    @NotNull(message = "Attack cannot be null")
    @Min(value = 0, message = "Attack cannot be negative")
    protected int attack;

    @NotNull(message = "Defense cannot be null")
    @Min(value = 0, message = "Defense cannot be negative")
    protected int defense;

    @NotNull(message = "Hit Points cannot be null")
    @Min(value = 0, message = "Hit Points cannot be negative")
    protected int hitPoints;

    @NotNull(message = "Current Hit Points cannot be null")
    @Min(value = 0, message = "Current Hit Points cannot be negative")
    protected int currentHitPoints;

    protected Artefact armor;
    protected Artefact helm;
    protected Artefact weapon;

    protected boolean isDead = false;

    public Hero(String name, String className, int attack, int defense, int hitPoints)
    {
        super(name, "Hero", 1);
        this.name = name;
        this.subClass = className;
        this.experience = 0;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.currentHitPoints = hitPoints;
    }

    public void gainExperience(int experience)
    {
        double maxExperience = (this.level * 1000) + Math.pow((this.level - 1), 2) * 450;

        PrintUtils.printGreen("\n" + this.name + " wins the fight and gains " + experience + " experience");

        if ((this.experience + experience) >= maxExperience)
        {
            this.experience = (this.experience + experience) - (int)maxExperience;
            this.level += 1;

            this.attack += 5;
            this.defense += 5;
            this.hitPoints += 50;
            this.currentHitPoints += 50;

            PrintUtils.printGreen(this.name + " leveled up to level " + this.level);
        }
        else
            this.experience += experience;
    }

    public void looseHitPoints(int hitPoints, String villainName)
    {
        PrintUtils.printBlue(villainName + " attacks " + this.name + " and deals " + hitPoints + " damage");

        if (this.currentHitPoints - hitPoints <= 0)
        {
            PrintUtils.printRed(this.name + " looses the fight and died");
            this.currentHitPoints = 0;
            this.isDead = true;
        }
        else
            this.currentHitPoints -= hitPoints;
    }

    public void equipArtefact(Artefact artefact)
    {
        switch (artefact.getType())
        {
            case "Armor":
            {
                if (this.armor != null)
                    this.defense -= this.armor.getDefense();
                this.armor = artefact;
                this.defense += artefact.getDefense();
                break;
            }
            case "Helm":
            {
                if (this.helm != null)
                    this.hitPoints -= this.helm.getHitPoints();
                this.helm = artefact;
                this.hitPoints += artefact.getHitPoints();
                break;
            }
            case "Weapon":
            {
                if (this.weapon != null)
                    this.attack -= this.weapon.getAttack();
                this.weapon = artefact;
                this.attack += artefact.getAttack();
                break;
            }
        }
    }

    public void unequipArtefact(Artefact artefact)
    {
        switch (artefact.getType())
        {
            case "Armor":
            {
                this.defense -= this.armor.getDefense();
                this.armor = null;
                break;
            }
            case "Helm":
            {
                this.hitPoints -= this.helm.getHitPoints();
                this.helm = null;
                break;
            }
            case "Weapon":
            {
                this.attack -= this.weapon.getAttack();
                this.weapon = null;
                break;
            }
        }
    }


    public void     printHeroInfos()
    {
        PrintUtils.printBlue("Name: " + name);
        PrintUtils.printBlue("Level: " + level);
        PrintUtils.printBlue("Experience: " + experience);
        PrintUtils.printBlue("Attack: " + attack);
        PrintUtils.printBlue("Defense: " + defense);
        PrintUtils.printBlue("Hit Points: " + hitPoints);
        PrintUtils.printBlue("Current Hit Points: " + currentHitPoints);
    }

    public String   getSubClass() { return subClass; }
    public int      getExperience() { return experience; }
    public int      getAttack() { return attack; }
    public int      getDefense() { return defense; }
    public int      getHitPoints() { return hitPoints; }
    public int      getCurrentHitPoints() { return currentHitPoints; }
    public Artefact getArmor() { return armor; }
    public Artefact getHelm() { return helm; }
    public Artefact getWeapon() { return weapon; }
    public boolean  getIsDead() { return isDead; }

    public void setSubClass(String subClass) { this.subClass = subClass; }
    public void setExperience(int experience) { this.experience = experience; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
    public void setCurrentHitPoints(int currentHitPoints) { this.currentHitPoints = currentHitPoints; }
    public void setArmor(Artefact armor) { this.armor = armor; }
    public void setHelm(Artefact helm) { this.helm = helm; }
    public void setWeapon(Artefact weapon) { this.weapon = weapon; }
    public void setIsDead(boolean isDead) { this.isDead = isDead; }
}
