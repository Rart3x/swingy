package swingy.models.characters.heros;

public class Sorcerer extends Hero {
    private final String className = "Sorcerer";
    private int attack, defense, hitPoints;

    public Sorcerer(String name)
    {
        super(name,1,0);
        this.setAttack(8);
        this.setDefense(3);
        this.setHitPoints(3);
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getHitPoints() { return hitPoints; }

    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
}
