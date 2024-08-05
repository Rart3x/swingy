package swingy;

import swingy.models.database.Database;

import swingy.models.characters.heroes.Hero;

import swingy.models.maps.Map;
import swingy.models.maps.MapFactory;

import swingy.utils.PrintUtils;
import swingy.utils.SaveUtils;
import swingy.utils.SelectHeroUtils;

import swingy.controllers.validation.Validation;
import swingy.view.SwingWindow;

public class Main {
    public static void main(String[] args)
    {
        boolean isRunning = true;
        boolean currentMode = false;

        try
        {
            Database.createDB();
            Hero hero = SelectHeroUtils.selectHero();

            if (!Validation.validateHero(hero))
                return;

            Map map = MapFactory.createMap(hero.getLevel());
            SwingWindow window = new SwingWindow(hero, map);

            window.createWindow(hero, map);
//            while (isRunning)
//            {
//                if (hero.getMode() != currentMode)
//                {
//                    currentMode = hero.getMode();
//
//                    if (currentMode)
//                    {
//                        window.createWindow(hero, map);
//                    }
//                }
//
//                PrintUtils.printBlue("qweqe");
//
//                if (!currentMode)
//                {
//                    isRunning = map.move(hero);
//                    map = MapFactory.createMap(hero.getLevel());
//                }
//            }
            SaveUtils.saveHero(hero);
            Database.closeDB();
        }
        catch (Exception e) {
            PrintUtils.printError(e.getMessage());
        }
    }
}
