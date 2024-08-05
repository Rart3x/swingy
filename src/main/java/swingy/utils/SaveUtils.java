package swingy.utils;

import swingy.models.characters.heroes.Hero;
import swingy.models.database.Insert;

public class SaveUtils {
    public static void saveHero(Hero hero)
    {
        if (!hero.getIsDead())
        {
            try
            {
                Insert.insertHero(hero);
                PrintUtils.printGreen("Hero saved successfully.");
            }
            catch (Exception e) {
                PrintUtils.printError(e.getMessage());
            }
        }
    }
}
