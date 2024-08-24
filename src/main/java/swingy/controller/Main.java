package swingy.controller;

import swingy.model.database.Database;

import swingy.model.characters.heroes.Hero;

import swingy.model.maps.Map;
import swingy.model.maps.MapFactory;

import swingy.utils.Args;
import swingy.utils.Print;
import swingy.utils.Save;
import swingy.utils.SelectHero;

import swingy.controller.validation.Validation;
import swingy.view.SwingWindow;

import java.util.Objects;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args)
    {
        boolean isRunning = true;
        SwingWindow window = null;

        Args.checkArgs(args);

        String  currentMode = args[0];
        String  previousMode = "";

        try
        {
            Database.createDB();
            Hero hero = SelectHero.selectHero();
            hero.setMode(currentMode);

            if (!Validation.validateHero(hero))
                return;

            Map map = MapFactory.createMap(hero.getLevel());

            while (isRunning && !hero.stop)
            {
                if (Objects.equals(currentMode, "gui"))
                {
                    if (!Objects.equals(currentMode, previousMode))
                    {
                        window = new SwingWindow(hero, map);
                        window.createWindow(hero, map);
                    }

                    if (hero.newMap)
                    {
                        Map.resetWalkedTiles();
                        map = MapFactory.createMap(hero.getLevel());
                        hero.newMap = false;
                        window.updateMap(hero, map);
                    }

                    previousMode = currentMode;
                    currentMode = hero.getMode();
                    Thread.sleep(50);
                }

                if (Objects.equals(currentMode, "console"))
                {
                    isRunning = map.move(hero);
                    if (hero.newMap)
                    {
                        Map.resetWalkedTiles();
                        map = MapFactory.createMap(hero.getLevel());
                        hero.newMap = false;
                    }
                    previousMode = currentMode;
                    currentMode = hero.getMode();
                }
            }
            if (!hero.isDead)
                Save.saveHero(hero);
            Database.closeDB();
        }
        catch (Exception e) {
            Print.printError(e.getMessage());
        }
        exit(0);
    }
}
