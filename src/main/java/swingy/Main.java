package swingy;

import swingy.controllers.validation.Validation;
import swingy.models.database.Database;

import swingy.models.characters.heroes.Hero;

import swingy.models.maps.Map;
import swingy.models.maps.MapFactory;

import swingy.utils.PrintUtils;
import swingy.utils.Utils;

public class Main {
    public static void main(String[] args)
    {
        boolean isRunning = true;

        try
        {
            Database.createDB();
            Hero hero = Utils.selectHero();

            if (!Validation.validateHero(hero))
                return;

//            Hero hero = HeroFactory.createHero("Legolas", "Archer");
            Map map = MapFactory.createMap(hero.getLevel());
//            SwingWindow window = new SwingWindow(hero, map);

            while (isRunning)
            {
                isRunning = map.move(hero);
                map = MapFactory.createMap(hero.getLevel());
            }

            if (!hero.getIsDead())
            {
                Database.insertHero(hero);
                PrintUtils.printGreen(hero.getName() + " hero has been saved successfully.");
            }
        }
        catch (Exception e) {
            PrintUtils.printError(e.getMessage());
        }
    }
}
