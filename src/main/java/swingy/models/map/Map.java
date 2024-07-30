package swingy.models.map;

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
                    this.map[i][j] = 0;
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


    public int      getCaracter(int x, int y) { return map[y][x]; }
    public int[][]  getMap() { return map; }
    public int      getSize() { return size; }

    public void setCaracter(int x, int y, int value) { map[y][x] = value; }
    public void setSize(int size) { this.size = size; }
    public void setMap(int[][] map) { this.map = map; }
}
