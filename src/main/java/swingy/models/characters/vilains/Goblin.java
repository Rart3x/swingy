package swingy.models.characters.vilains;

public class Goblin extends Villain {
    private final String className = "Goblin";
    private int attack, defense, hitPoints;

    public Goblin(String name, int level)
    {
        super(name, level);
        this.setAttack(2);
        this.setDefense(2);
        this.setHitPoints(2);
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getHitPoints() { return hitPoints; }

    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
}
