package swingy.models.characters.vilains;

public class Orc extends Villain {
    private final String className = "Orc";
    private int attack, defense, hitPoints;

    public Orc(String name, int level)
    {
        super(name, level);
        this.setAttack(5);
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
