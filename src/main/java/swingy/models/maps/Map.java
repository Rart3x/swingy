package swingy.models.maps;

import swingy.models.characters.heroes.Hero;

import swingy.utils.MoveUtils;
import swingy.utils.PrintUtils;

public class Map {
    private static int[][] map;
    private static int     size;

    public Map(int size)
    {
        Map.size = size;
        map = new int[size][size];

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (i == size / 2 && j == size / 2)
                    map[i][j] = 2;
                else if (i == 0 || j == 0 || i == size - 1 || j == size - 1)
                    map[i][j] = 1;
                else
                {
                    double randomNumber = Math.random();

                    if (randomNumber < 0.25)
                        map[i][j] = 3;
                    else
                        map[i][j] = 0;
                }
            }
        }
    }

    public boolean move(Hero hero)
    {
        printMapCensured();

        while (true)
        {
            PrintUtils.printYellow("Enter a direction (NORTH, SOUTH, WEST, EAST): Switch mode with S, Quit with Q");
            String direction = System.console().readLine();

            if (direction.equals("NORTH") || direction.equals("SOUTH") || direction.equals("WEST") || direction.equals("EAST"))
            {
                switch (direction)
                {
                    case "NORTH":
                        return MoveUtils.moveUp(hero, map);

                    case "SOUTH":
                        return MoveUtils.moveDown(hero, map);

                    case "WEST":
                        return MoveUtils.moveLeft(hero, map);

                    case "EAST":
                        return MoveUtils.moveRight(hero, map);
                }
                printMapCensured();
            }
            else if (direction.equalsIgnoreCase("S"))
            {
                hero.setMode(!hero.getMode());
                return true;
            }
            else if (direction.equalsIgnoreCase("Q"))
                return false;
            else
                PrintUtils.printRed("Invalid direction.");
        }
    }

    public void   printMapCensured()
    {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (map[i][j] == 0)
                    System.out.print(" ");
                else if (map[i][j] == 1)
                    System.out.print("#");
                else if (map[i][j] == 2)
                    System.out.print("K");
                else if (map[i][j] == 3)
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static int[]    getPlayerPosition()
    {
        int[] position = new int[2];

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (map[i][j] == 2)
                {
                    position[0] = j;
                    position[1] = i;
                    return position;
                }
            }
        }
        return position;
    }

    public static int[][] getMap() { return map; }
}
