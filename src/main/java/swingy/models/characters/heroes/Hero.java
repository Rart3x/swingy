package swingy.models.characters.heroes;

import swingy.models.characters.Individual;

public class Hero extends Individual {
    private int experience;

    public Hero(String name)
    {
        super(name, "Hero", 1);
        this.experience = 0;
    }

    public int getExperience() { return experience; }

    public void setExperience(int experience) { this.experience = experience; }
}
