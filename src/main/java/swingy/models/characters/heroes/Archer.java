package swingy.models.characters.heroes;

public class Archer extends Hero {
    private final String className = "Archer";
    private int attack, defense, hitPoints;

    public Archer(String name)
    {
        super(name);
        this.setAttack(5);
        this.setDefense(5);
        this.setHitPoints(2);
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getHitPoints() { return hitPoints; }

    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
}
