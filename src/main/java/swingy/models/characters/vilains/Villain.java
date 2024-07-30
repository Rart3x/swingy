package swingy.models.characters.vilains;

import swingy.models.characters.Individual;

public class Villain extends Individual {
    private int level;

    public Villain(String name, int level)
    {
        super(name, "Villain", level);
        this.level = level;
    }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }
}
