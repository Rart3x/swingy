package swingy.models.characters.villains;

public class VillainFactory {
    private static Villain instance;

    private VillainFactory() {}

    public static Villain createVillain(String name, String villainType, int level)
    {
        if (!villainType.equals("Dragon") && !villainType.equals("Goblin") && !villainType.equals("Orc"))
            throw new IllegalArgumentException("Invalid villain type");

        switch (villainType)
        {
            case "Dragon" -> instance = new Dragon(name, level);
            case "Goblin" -> instance = new Goblin(name, level);
            case "Orc" -> instance = new Orc(name, level);
        }

        return instance;
    }

    public static Villain createRandomVillain()
    {
        int random = (int)(Math.random() * 3);
        int level = (int)(Math.random() * 5) + 1;

        switch (random)
        {
            case 0 -> instance = new Dragon("Dragon", level);
            case 1 -> instance = new Goblin("Goblin", level);
            case 2 -> instance = new Orc("Orc", level);
        }

        return instance;
    }
}
