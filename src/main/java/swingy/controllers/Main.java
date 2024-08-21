package swingy.controllers;

import swingy.models.database.Database;

import swingy.models.characters.heroes.Hero;

import swingy.models.maps.Map;
import swingy.models.maps.MapFactory;

import swingy.utils.ArgsUtils;
import swingy.utils.PrintUtils;
import swingy.utils.SaveUtils;
import swingy.utils.SelectHeroUtils;

import swingy.controllers.validation.Validation;
import swingy.view.SwingWindow;

import java.util.Objects;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args)
    {
        boolean isRunning = true;
        SwingWindow window = null;

        ArgsUtils.checkArgs(args);

        String  currentMode = args[0];
        String  previousMode = "";

        try
        {
            Database.createDB();
            Hero hero = SelectHeroUtils.selectHero();
            hero.setMode(currentMode);

            if (!Validation.validateHero(hero))
                return;

            Map map = MapFactory.createMap(hero.getLevel());

            while (isRunning)
            {
                if (Objects.equals(currentMode, "gui"))
                {
                    if (!Objects.equals(currentMode, previousMode))
                    {
                        window = new SwingWindow(hero, map);
                        window.createWindow(hero, map);
                    }
                    previousMode = currentMode;
                    currentMode = hero.getMode();
                    Thread.sleep(50);
                }

                if (Objects.equals(currentMode, "console"))
                {
                    isRunning = map.move(hero);
                    map = MapFactory.createMap(hero.getLevel());
                    previousMode = currentMode;
                    currentMode = hero.getMode();
                }
            }

            SaveUtils.saveHero(hero);
            Database.closeDB();
        }
        catch (Exception e) {
            PrintUtils.printError(e.getMessage());
        }
        exit(0);
    }
}
