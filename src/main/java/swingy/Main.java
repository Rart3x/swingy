package swingy;

import swingy.models.database.Database;

import swingy.models.characters.heroes.Hero;

import swingy.models.maps.Map;
import swingy.models.maps.MapFactory;

import swingy.utils.Utils;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            Database.createDB();
            Hero hero = Utils.selectHero();
            Map  map  = MapFactory.createMap(hero.getLevel());

            Utils.printYellow("\n");
            hero.printHeroInfos();

            boolean isRunning = true;

            while (isRunning)
            {
                isRunning = map.move(hero);
                map = MapFactory.createMap(hero.getLevel());
            }

            if (!hero.getIsDead())
            {
                Utils.saveHero(hero);
                Utils.printGreen(hero.getName() + " hero has been saved successfully.");
            }
        }
        catch (Exception e) {
            Utils.printError(e.getMessage());
        }
    }
}
