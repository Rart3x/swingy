package swingy.models.characters.heros;

import swingy.models.characters.Individual;

public class Hero extends Individual {
    private int experience, level;

    public Hero(String name, int level, int experience)
    {
        super(name, "Hero", 1);
        this.level = level;
        this.experience = experience;
    }

    public int getExperience() { return experience; }
    public int getLevel() { return level; }

    public void setExperience(int experience) { this.experience = experience; }
    public void setLevel(int level) { this.level = level; }
}
