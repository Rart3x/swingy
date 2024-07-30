package swingy.models.characters.heros;

public class Warrior extends Hero {
    private final String className = "Warrior";
    private int attack, defense, hitPoints;

    public Warrior(String name)
    {
        super(name,1,0);
        this.setAttack(10);
        this.setDefense(5);
        this.setHitPoints(5);
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getHitPoints() { return hitPoints; }

    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
}
