package swingy.models.characters.villains;

public class Goblin extends Villain {
    private final String className = "Goblin";

    public Goblin(String name, int level)
    {
        super(name, level, 5, 5, 5);
    }
}
