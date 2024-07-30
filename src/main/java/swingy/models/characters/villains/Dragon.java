package swingy.models.characters.villains;

public class Dragon extends Villain {
    private final String className = "Dragon";
    private int attack, defense, hitPoints;

    public Dragon(String name, int level)
    {
        super(name, level);
        this.setAttack(10);
        this.setDefense(10);
        this.setHitPoints(10);
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getHitPoints() { return hitPoints; }

    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
}
