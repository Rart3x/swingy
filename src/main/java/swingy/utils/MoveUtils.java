package swingy.utils;

import swingy.models.characters.heroes.Hero;

import swingy.models.maps.Map;

import static swingy.utils.FightUtils.fightOrRunResult;

public class MoveUtils {
    public static boolean moveUp(Hero hero, int[][] map)
    {
        int[] position = Map.getPlayerPosition();
        int x = position[0];
        int y = position[1];

        if (map[y - 1][x] == 1)
        {
            PrintUtils.printGreen("You have survived this level.");
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
            PrintUtils.printGreen("You have survived this level.");
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
            PrintUtils.printGreen("You have survived this level.");
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
            PrintUtils.printGreen("You have survived this level.");
            return false;
        }

        if (map[y][x + 1] == 3)
            if (fightOrRunResult(hero)) return false;

        map[y][x] = 0;
        map[y][x + 1] = 2;

        return true;
    }
}
