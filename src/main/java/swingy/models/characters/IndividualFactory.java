package swingy.models.characters;

import swingy.models.characters.heroes.Archer;
import swingy.models.characters.heroes.Sorcerer;
import swingy.models.characters.heroes.Warrior;

import swingy.models.characters.villains.Dragon;
import swingy.models.characters.villains.Goblin;
import swingy.models.characters.villains.Orc;

public final class IndividualFactory {
    private static Individual instance;

    private IndividualFactory() {}

    public static Individual createIndividual(String name, String type, String className, int level)
    {
        if (!type.equals("Hero") && !type.equals("Villain"))
            throw new IllegalArgumentException("Invalid type of individual");

        if (type.equals("Hero"))
        {
            if (!className.equals("Archer") && !className.equals("Sorcerer") && !className.equals("Warrior"))
                throw new IllegalArgumentException("Invalid class name");

            switch (className)
            {
                case "Archer":
                    instance = new Archer(name);
                    break;
                case "Sorcerer":
                    instance = new Sorcerer(name);
                    break;
                case "Warrior":
                    instance = new Warrior(name);
                    break;
            }
        }
        else
        {
            if (!className.equals("Dragon") && !className.equals("Goblin") && !className.equals("Orc"))
                throw new IllegalArgumentException("Invalid class name");

            switch (className)
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
        }
        return instance;
    }
}
