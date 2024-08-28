package swingy.model.characters.heroes;

import javax.validation.constraints.*;

import swingy.model.artefacts.Artefact;
import swingy.model.characters.AIndividual;
import swingy.database.Delete;
import swingy.database.Get;
import swingy.database.Insert;
import swingy.utils.Print;
import swingy.view.SwingWindow;

public class Hero extends AIndividual {
    @NotNull(message = "Name cannot be empty")
    protected String name;

    @NotNull(message = "Subclass cannot be empty")
    protected String subClass;

    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = Integer.MAX_VALUE, message = "Experience exceeds maximum allowed value")
    protected int experience;

    @Min(value = 0, message = "Level cannot be negative")
    @Max(value = Integer.MAX_VALUE, message = "Level exceeds maximum allowed value")
    protected double maxExperience;

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

    protected String  mode   = "";

    public    boolean isDead = false;
    public    boolean newMap = false;
    public    boolean stop   = false;
    public    boolean deadVillain = false;

    public Hero(String name, String className, int attack, int defense, int hitPoints)
    {
        super(name, "Hero", 1);

        this.name = name;
        this.subClass = className;

        this.experience = 0;
        this.maxExperience = (this.level * 1000) + Math.pow((this.level - 1), 2) * 450;

        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.currentHitPoints = hitPoints;
    }

    public void gainExperience(int experience, boolean mode)
    {
        Print.printDependingOnMode("\n" + this.name + " wins the fight and gains " + experience + " experience", "GREEN", mode);

        if ((this.experience + experience) >= maxExperience)
        {
            this.level += 1;
            this.experience = (this.experience + experience) - (int)maxExperience;
            this.maxExperience = (this.level * 1000) + Math.pow((this.level - 1), 2) * 450;

            this.attack += 5;
            this.defense += 5;
            this.hitPoints += 50;
            this.currentHitPoints += 50;

            Print.printDependingOnMode(this.name + " leveled up to level " + this.level, "GREEN", mode);
        }
        else
            this.experience += experience;
    }

    public void looseHitPoints(int hitPoints, String villainName, boolean mode)
    {
        Print.printDependingOnMode(villainName + " attacks " + this.name + " and deals " + hitPoints + " damage", "BLUE", mode);

        if (this.currentHitPoints - hitPoints <= 0)
        {
            if (!mode)
                Print.printRed(this.name + " looses the fight and died");
            else
            {
                SwingWindow.addText(this.name + " looses the fight and died");
                Print.printRed(this.name + " looses the fight and died");
                this.stop = true;
            }
            this.currentHitPoints = 0;
            this.isDead = true;
        }
        else
            this.currentHitPoints -= hitPoints;
    }

    public void equipArtefact(Artefact artefact)
    {
        int id = Get.getHeroIdInDB(this.name);

        switch (artefact.getType())
        {
            case "Armor":
            {
                if (this.armor != null)
                {
                    this.defense -= this.armor.getDefense();
                    Delete.deleteArtefact(this.armor.getName());
                }
                this.armor = artefact;
                this.defense += artefact.getDefense();
                break;
            }
            case "Helm":
            {
                if (this.helm != null)
                {
                    this.hitPoints -= this.helm.getHitPoints();
                    Delete.deleteArtefact(this.helm.getName());
                }
                this.helm = artefact;
                this.hitPoints += artefact.getHitPoints();
                break;
            }
            case "Weapon":
            {
                if (this.weapon != null)
                {
                    this.attack -= this.weapon.getAttack();
                    Delete.deleteArtefact(this.weapon.getName());
                }
                this.weapon = artefact;
                this.attack += artefact.getAttack();
                break;
            }
        }
        Insert.insertArtefact(artefact, id);
    }

    public void unequipArtefact(Artefact artefact)
    {
        Delete.deleteArtefact(artefact.getName());

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
        Print.printBlue("Name: " + name);
        Print.printBlue("Level: " + level);
        Print.printBlue("Experience: " + experience);
        Print.printBlue("Attack: " + attack);
        Print.printBlue("Defense: " + defense);
        Print.printBlue("Hit Points: " + hitPoints);
        Print.printBlue("Current Hit Points: " + currentHitPoints);
    }

    public String   getSubClass() { return subClass; }
    public int      getExperience() { return experience; }
    public double   getMaxExperience() { return maxExperience; }

    public int      getAttack() { return attack; }
    public int      getDefense() { return defense; }
    public int      getHitPoints() { return hitPoints; }
    public int      getCurrentHitPoints() { return currentHitPoints; }

    public Artefact getArmor() { return armor; }
    public Artefact getHelm() { return helm; }
    public Artefact getWeapon() { return weapon; }

    public String   getMode() { return mode; }

    public void setExperience(int experience) { this.experience = experience; }
    public void setMaxExperience(int maxExperience) { this.maxExperience = maxExperience; }

    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
    public void setCurrentHitPoints(int currentHitPoints) { this.currentHitPoints = currentHitPoints; }

    public void setArmor(Artefact armor) { this.armor = armor; }
    public void setHelm(Artefact helm) { this.helm = helm; }
    public void setWeapon(Artefact weapon) { this.weapon = weapon; }

    public void setMode(String mode) { this.mode = mode; }
}
