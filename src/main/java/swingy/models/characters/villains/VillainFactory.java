package swingy.models.characters.villains;

public class VillainFactory {
    private static Villain instance;

    private VillainFactory() {}

    public static Villain createVillain(String name, String villainType, int level)
    {
        if (!villainType.equals("Goblin") && !villainType.equals("Orc") && !villainType.equals("Dragon"))
            throw new IllegalArgumentException("Invalid villain type");

        switch (villainType)
        {
            case "Goblin" -> instance = new Goblin(name, level);
            case "Orc" -> instance = new Orc(name, level);
            case "Dragon" -> instance = new Dragon(name, level);
        }

        return instance;
    }
}
