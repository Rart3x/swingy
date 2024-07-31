package swingy.models.maps;

import swingy.models.characters.villains.Villain;
import swingy.models.characters.villains.VillainFactory;

import swingy.models.characters.heroes.Hero;

import swingy.models.fights.Fight;
import swingy.models.fights.FightFactory;

import swingy.utils.Utils;

public class Map {
    private int[][] map;
    private int     size;

    public Map(int size)
    {
        this.size = size;
        this.map = new int[size][size];

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (i == size / 2 && j == size / 2)
                    this.map[i][j] = 2;
                else if (i == 0 || j == 0 || i == size - 1 || j == size - 1)
                    this.map[i][j] = 1;
                else
                {
                    double randomNumber = Math.random();

                    if (randomNumber < 0.25)
                        this.map[i][j] = 3;
                    else
                        this.map[i][j] = 0;
                }
            }
        }
    }

    public void     move(Hero hero)
    {
        int[] position = getPlayerPosition();
        int x = position[0];
        int y = position[1];

        printMapCensured();

        while (true)
        {
            Utils.printYellow("Enter a direction (NORTH, SOUTH, WEST, EAST): Quit with Q");
            String direction = System.console().readLine();

            if (direction.equals("NORTH") || direction.equals("SOUTH") || direction.equals("WEST") || direction.equals("EAST"))
            {
                switch (direction)
                {
                    case "NORTH" ->
                    {
                        if (map[y - 1][x] == 1)
                        {
                            Utils.printGreen("You have survived this level.");
                            return;
                        }

                        if (map[y - 1][x] == 3)
                        {
                            Villain randomVillain = VillainFactory.createRandomVillain(hero.getLevel());
                            Utils.printYellow("You have encountered a " + randomVillain.getName() + " villain.");
                            Fight fight = FightFactory.createFight(hero, randomVillain);
                            fight.fight();
                        }

                        map[y][x] = 0;
                        map[y - 1][x] = 2;
                    }
                    case "SOUTH" ->
                    {
                        if (map[y + 1][x] == 1)
                        {
                            Utils.printGreen("You have survived this level.");
                            return;
                        }

                        if (map[y + 1][x] == 3)
                        {
                            Villain randomVillain = VillainFactory.createRandomVillain(hero.getLevel());
                            Utils.printYellow("You have encountered a " + randomVillain.getName() + " villain.");
                            Fight fight = FightFactory.createFight(hero, randomVillain);
                            fight.fight();
                        }

                        map[y][x] = 0;
                        map[y + 1][x] = 2;
                    }
                    case "WEST" ->
                    {
                        if (map[y][x - 1] == 1)
                        {
                            Utils.printGreen("You have survived this level.");
                            return;
                        }

                        if (map[y][x - 1] == 3)
                        {
                            Villain randomVillain = VillainFactory.createRandomVillain(hero.getLevel());
                            Utils.printYellow("You have encountered a " + randomVillain.getName() + " villain.");
                            Fight fight = FightFactory.createFight(hero, randomVillain);
                            fight.fight();
                        }

                        map[y][x] = 0;
                        map[y][x - 1] = 2;
                    }
                    case "EAST" ->
                    {
                        if (map[y][x + 1] == 1)
                        {
                            Utils.printGreen("You have survived this level.");
                            return;
                        }

                        if (map[y][x + 1] == 3)
                        {
                            Villain randomVillain = VillainFactory.createRandomVillain(hero.getLevel());
                            Utils.printYellow("You have encountered a " + randomVillain.getName() + " villain.");
                            Fight fight = FightFactory.createFight(hero, randomVillain);
                            fight.fight();
                        }

                        map[y][x] = 0;
                        map[y][x + 1] = 2;
                    }
                }
                printMapCensured();

                position = getPlayerPosition();
                x = position[0];
                y = position[1];
            }
            else if (direction.equals("Q"))
                System.exit(0);
            else
                Utils.printRed("Invalid direction");
        }
    }

    public void     printMap()
    {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                System.out.print(map[i][j]);
            }
            System.out.println();
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


    public int[]    getPlayerPosition()
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
    public int      getCaracter(int x, int y) { return map[y][x]; }
    public int[][]  getMap() { return map; }
    public int      getSize() { return size; }

    public void setCaracter(int x, int y, int value) { map[y][x] = value; }
    public void setSize(int size) { this.size = size; }
    public void setMap(int[][] map) { this.map = map; }
}
