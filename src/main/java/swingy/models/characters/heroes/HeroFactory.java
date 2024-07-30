package swingy.models.characters.heroes;

import swingy.models.characters.heroes.Archer;
import swingy.models.characters.heroes.Mage;
import swingy.models.characters.heroes.Warrior;

public class HeroFactory {
    private static Hero instance;

    private HeroFactory() {}

    public static Hero createHero(String name, String heroClass)
    {
        if (!heroClass.equals("Warrior") && !heroClass.equals("Mage") && !heroClass.equals("Archer"))
            throw new IllegalArgumentException("Invalid hero class");

        switch (heroClass)
        {
            case "Archer" -> instance = new Archer(name);
            case "Warrior" -> instance = new Warrior(name);
            case "Mage" -> instance = new Mage(name);
        }

        return instance;
    }
}
