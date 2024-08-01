package swingy;

import swingy.controllers.validation.Validation;
import swingy.models.database.Database;

import swingy.models.characters.heroes.Hero;

import swingy.models.maps.Map;
import swingy.models.maps.MapFactory;

import swingy.utils.Utils;

public class Main {
    public static void main(String[] args)
    {
        boolean isRunning = true;

        try
        {
            Database.createDB();
            Hero hero = Utils.selectHero();

            Validation.validateHero(hero);

            Map  map  = MapFactory.createMap(hero.getLevel());

            while (isRunning)
            {
                isRunning = map.move(hero);
                map = MapFactory.createMap(hero.getLevel());
            }

            if (!hero.getIsDead())
            {
                Database.insertHero(hero);
                Utils.saveHero(hero);
                Utils.printGreen(hero.getName() + " hero has been saved successfully.");
            }
        }
        catch (Exception e) {
            Utils.printError(e.getMessage());
        }
    }
}
