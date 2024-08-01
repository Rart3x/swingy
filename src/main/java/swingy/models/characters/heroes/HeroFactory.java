package swingy.models.characters.heroes;

public class HeroFactory {
    private static Hero instance;

    private HeroFactory() {}

    public static Hero createHero(String name, String heroClass)
    {
        if (!heroClass.equals("Archer") && !heroClass.equals("Mage") && !heroClass.equals("Warrior"))
            throw new IllegalArgumentException("Invalid hero class");

        switch (heroClass)
        {
            case "Archer":
                instance = new Archer(name);
                break;
            case "Mage":
                instance = new Mage(name);
                break;
            case "Warrior":
                instance = new Warrior(name);
                break;
        }

        return instance;
    }
}
