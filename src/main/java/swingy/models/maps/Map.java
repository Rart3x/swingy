package swingy.models.maps;

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

    public void     move(String direction)
    {
        int[] position = getPlayerPosition();
        int x = position[0];
        int y = position[1];


        switch (direction) {
            case "NORTH" -> {
                if (y - 1 >= 0 && map[y - 1][x] != 1)
                {
                    map[y][x] = 0;
                    map[y - 1][x] = 2;
                }
            }
            case "SOUTH" -> {
                if (y + 1 < size && map[y + 1][x] != 1)
                {
                    map[y][x] = 0;
                    map[y + 1][x] = 2;
                }
            }
            case "WEST" -> {
                if (x - 1 >= 0 && map[y][x - 1] != 1)
                {
                    map[y][x] = 0;
                    map[y][x - 1] = 2;
                }
            }
            case "EAST" -> {
                if (x + 1 < size && map[y][x + 1] != 1)
                {
                    map[y][x] = 0;
                    map[y][x + 1] = 2;
                }
            }
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
