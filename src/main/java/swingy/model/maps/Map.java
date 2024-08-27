package swingy.model.maps;

import swingy.model.characters.heroes.Hero;

import swingy.utils.Move;
import swingy.utils.Print;

public class Map {
    private static int[][] map;
    private static int[][] walkedTiles = new int[1000][2];

    private static int     size;
    private static int     walkedTilesIndex = 0;

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
            Print.printYellow("Enter a direction (NORTH, SOUTH, WEST, EAST): Change mode with C, Quit with Q");
            String direction = System.console().readLine();

            direction = direction.toUpperCase();

            if (direction.equals("NORTH") || direction.equals("N")
                    || direction.equals("SOUTH") || direction.equals("S")
                    || direction.equals("WEST") || direction.equals("W")
                    || direction.equals("EAST") || direction.equals("E"))
            {
                boolean result = false;

                switch (direction)
                {
                    case "N":
                    case "NORTH":
                        result = Move.moveUp(hero, map);

                        if (!result && hero.isDead)
                            return false;
                        else if (!result)
                            return true;
                        break;

                    case "S":
                    case "SOUTH":
                        result = Move.moveDown(hero, map);

                        if (!result && hero.isDead)
                            return false;
                        else if (!result)
                            return true;
                        break;

                    case "W":
                    case "WEST":
                        result = Move.moveLeft(hero, map);

                        if (!result && hero.isDead)
                            return false;
                        else if (!result)
                            return true;
                        break;

                    case "E":
                    case "EAST":
                        result = Move.moveRight(hero, map);

                        if (!result && hero.isDead)
                            return false;
                        else if (!result)
                            return true;
                        break;
                }
                printMapCensured();
            }
            else if (direction.equalsIgnoreCase("C"))
            {
                hero.setMode("gui");
                return true;
            }
            else if (direction.equalsIgnoreCase("Q"))
                return false;
            else
                Print.printRed("Invalid direction.");
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

    public static void    addWalkedTile(int x, int y)
    {
        walkedTiles[walkedTilesIndex][0] = x;
        walkedTiles[walkedTilesIndex][1] = y;
        walkedTilesIndex++;
    }

    public static boolean isWalkedTile(int x, int y)
    {
        for (int i = 0; i < walkedTilesIndex; i++)
        {
            if (walkedTiles[i][0] == x && walkedTiles[i][1] == y)
                return true;
        }
        return false;
    }

    public static void   resetWalkedTiles()
    {
        walkedTiles = new int[1000][2];
        walkedTilesIndex = 0;
    }

    public static int[][] getMap() { return map; }
}
