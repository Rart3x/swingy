package swingy.utils;

import swingy.model.characters.heroes.Hero;
import swingy.model.database.Insert;

public class Save {
    public static void saveHero(Hero hero)
    {
        if (!hero.isDead)
        {
            try
            {
                Insert.insertHero(hero);
                Print.printGreen("Hero saved successfully.");
            }
            catch (Exception e) {
                Print.printError(e.getMessage());
            }
        }
    }
}
