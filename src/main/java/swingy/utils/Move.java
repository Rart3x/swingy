package swingy.utils;

import swingy.model.characters.heroes.Hero;

import swingy.model.maps.Map;

import static swingy.utils.Fight.fightOrRunResult;

public class Move {
    public static boolean moveUp(Hero hero, int[][] map)
    {
        int[] position = Map.getPlayerPosition();
        int x = position[0];
        int y = position[1];

        if (map[y - 1][x] == 1)
        {
            Print.printGreen("You have survived this level.");
            hero.newMap = true;
            return false;
        }

        if (map[y - 1][x] == 3)
            if (fightOrRunResult(hero)) return false;

        map[y][x] = 0;
        map[y - 1][x] = 2;

        return true;
    }

    public static boolean moveDown(Hero hero, int[][] map)
    {
        int[] position = Map.getPlayerPosition();
        int x = position[0];
        int y = position[1];

        if (map[y + 1][x] == 1)
        {
            Print.printGreen("You have survived this level.");
            hero.newMap = true;
            return false;
        }

        if (map[y + 1][x] == 3)
            if (fightOrRunResult(hero)) return false;

        map[y][x] = 0;
        map[y + 1][x] = 2;

        return true;
    }

    public static boolean moveLeft(Hero hero, int[][] map)
    {
        int[] position = Map.getPlayerPosition();
        int x = position[0];
        int y = position[1];

        if (map[y][x - 1] == 1)
        {
            Print.printGreen("You have survived this level.");
            hero.newMap = true;
            return false;
        }

        if (map[y][x - 1] == 3)
            if (fightOrRunResult(hero)) return false;

        map[y][x] = 0;
        map[y][x - 1] = 2;

        return true;
    }

    public static boolean moveRight(Hero hero, int[][] map)
    {
        int[] position = Map.getPlayerPosition();
        int x = position[0];
        int y = position[1];

        if (map[y][x + 1] == 1)
        {
            Print.printGreen("You have survived this level.");
            hero.newMap = true;
            return false;
        }

        if (map[y][x + 1] == 3)
            if (fightOrRunResult(hero)) return false;

        map[y][x] = 0;
        map[y][x + 1] = 2;

        return true;
    }
}
