package swingy.models.characters.heroes;

import swingy.models.artefacts.Artefact;
import swingy.models.characters.AIndividual;
import swingy.utils.Utils;

public class Hero extends AIndividual {
    protected Artefact armor = null;
    protected Artefact helm = null;
    protected Artefact weapon = null;

    protected int experience;
    protected int attack, defense, hitPoints;
    protected int currentHitPoints;

    protected boolean isDead = false;

    public Hero(String name, int attack, int defense, int hitPoints)
    {
        super(name, "Hero", 1);
        this.experience = 0;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.currentHitPoints = hitPoints;
    }

    public void gainExperience(int experience)
    {
        double maxExperience = (this.level * 1000) + Math.pow((this.level - 1), 2) * 450;

        Utils.printGreen(this.name + " wins the fight and gains " + experience + " experience");

        if ((this.experience + experience) >= maxExperience)
        {
            this.experience = (this.experience + experience) - (int)maxExperience;
            this.level += 1;

            this.attack += 5;
            this.defense += 5;
            this.hitPoints += 50;
            this.currentHitPoints += 50;

            Utils.printGreen(this.name + " leveled up to level " + this.level);
        }
        else
            this.experience += experience;
    }

    public void looseHitPoints(int hitPoints, String villainName)
    {
        Utils.printBlue(villainName + " attacks " + this.name + " and deals " + hitPoints + " damage");

        if (this.currentHitPoints - hitPoints <= 0)
        {
            Utils.printRed(this.name + " looses the fight and died");
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
            case "Armor" ->
            {
                if (this.armor != null)
                {
                    this.defense -= this.armor.getDefense();
                    Utils.printBlue(this.name + " unequips " + this.armor.getName());
                }
                this.armor = artefact;
                this.defense += artefact.getDefense();
                Utils.printBlue(this.name + " equips " + artefact.getName());
            }
            case "Helm" ->
            {
                if (this.helm != null)
                {
                    this.hitPoints -= this.helm.getHitPoints();
                    Utils.printBlue(this.name + " unequips " + this.helm.getName());
                }
                this.helm = artefact;
                this.hitPoints += artefact.getHitPoints();
                Utils.printBlue(this.name + " equips " + artefact.getName());
            }
            case "Weapon" ->
            {
                if (this.weapon != null)
                {
                    this.attack -= this.weapon.getAttack();
                    Utils.printBlue(this.name + " unequips " + this.weapon.getName());
                }
                this.weapon = artefact;
                this.attack += artefact.getAttack();
                Utils.printBlue(this.name + " equips " + artefact.getName());
            }
        }
    }

    public void unequipArtefact(Artefact artefact)
    {
        switch (artefact.getType())
        {
            case "Armor" ->
            {
                this.defense -= this.armor.getDefense();
                Utils.printBlue(this.name + " unequips " + this.armor.getName());
                this.armor = null;
            }
            case "Helm" ->
            {
                this.hitPoints -= this.helm.getHitPoints();
                Utils.printBlue(this.name + " unequips " + this.helm.getName());
                this.helm = null;
            }
            case "Weapon" ->
            {
                this.attack -= this.weapon.getAttack();
                Utils.printBlue(this.name + " unequips " + this.weapon.getName());
                this.weapon = null;
            }
        }
    }


    public void     printHeroInfos()
    {
        Utils.printBlue("Name: " + name);
        Utils.printBlue("Level: " + level);
        Utils.printBlue("Experience: " + experience);
        Utils.printBlue("Attack: " + attack);
        Utils.printBlue("Defense: " + defense);
        Utils.printBlue("Hit Points: " + hitPoints);
        Utils.printBlue("Current Hit Points: " + currentHitPoints);
    }


    public int      getExperience() { return experience; }
    public int      getAttack() { return attack; }
    public int      getDefense() { return defense; }
    public int      getHitPoints() { return hitPoints; }
    public int      getCurrentHitPoints() { return currentHitPoints; }
    public Artefact getArmor() { return armor; }
    public Artefact getHelm() { return helm; }
    public Artefact getWeapon() { return weapon; }
    public boolean  getIsDead() { return isDead; }

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
