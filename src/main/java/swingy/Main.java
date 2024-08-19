package swingy;

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

public class Main {
    public static void main(String[] args)
    {
        boolean isRunning = true;

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
            SwingWindow window = new SwingWindow(hero, map);

            while (isRunning)
            {
                if (Objects.equals(currentMode, "gui"))
                {
                    if (!Objects.equals(currentMode, previousMode))
                        window.createWindow(hero, map);
                    previousMode = currentMode;
                    currentMode = hero.getMode();
                }

                if (Objects.equals(currentMode, "console"))
                {
                    isRunning = map.move(hero);
                    map = MapFactory.createMap(hero.getLevel());
                    currentMode = hero.getMode();
                }
            }
            SaveUtils.saveHero(hero);
            Database.closeDB();
        }
        catch (Exception e) {
            PrintUtils.printError(e.getMessage());
        }
    }
}
