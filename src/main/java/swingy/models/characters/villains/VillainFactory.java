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
            case "Dragon":
                instance = new Dragon(name, level);
                break;
            case "Goblin":
                instance = new Goblin(name, level);
                break;
            case "Orc":
                instance = new Orc(name, level);
                break;
        }

        return instance;
    }

    public static Villain createRandomVillain(int heroLevel)
    {
        int random = (int)(Math.random() * 3);

        int minLevel = Math.max(1, heroLevel - 3);
        int maxLevel = Math.min(100, heroLevel + 3);
        int level = (int)(Math.random() * (maxLevel - minLevel + 1)) + minLevel;

        switch (random)
        {
            case 0:
                instance = new Dragon("Dragon", level);
                break;
            case 1:
                instance = new Goblin("Goblin", level);
                break;
            case 2:
                instance = new Orc("Orc", level);
                break;
        }

        return instance;
    }
}
