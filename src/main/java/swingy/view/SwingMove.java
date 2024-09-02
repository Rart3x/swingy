package swingy.view;

import swingy.model.characters.heroes.Hero;
import swingy.model.maps.Map;
import swingy.view.modals.AcceptFightModal;
import swingy.view.modals.WinLevelModal;

public class SwingMove {
    public static boolean moveUp(Hero hero, int[][] map, SwingWindow window)
    {
        int[] position = Map.getPlayerPosition();
        int x = position[0];
        int y = position[1];

        if (map[y - 1][x] == 1)
        {
            window.lockWindow();
            WinLevelModal.modal(window);
            hero.newMap = true;
            return true;
        }

        if (map[y - 1][x] == 3)
        {
            window.lockWindow();
            AcceptFightModal.modal(hero, window);
        }

        map[y][x] = 0;
        map[y - 1][x] = 2;

        return false;
    }

    public static boolean moveDown(Hero hero, int[][] map, SwingWindow window)
    {
        int[] position = Map.getPlayerPosition();
        int x = position[0];
        int y = position[1];

        if (map[y + 1][x] == 1)
        {
            window.lockWindow();
            WinLevelModal.modal(window);
            hero.newMap = true;
            return true;
        }

        if (map[y + 1][x] == 3)
        {
            window.lockWindow();
            AcceptFightModal.modal(hero, window);
        }

        map[y][x] = 0;
        map[y + 1][x] = 2;

        return false;
    }

    public static boolean moveLeft(Hero hero, int[][] map, SwingWindow window)
    {
        int[] position = Map.getPlayerPosition();
        int x = position[0];
        int y = position[1];

        if (map[y][x - 1] == 1)
        {
            window.lockWindow();
            WinLevelModal.modal(window);
            hero.newMap = true;
            return true;
        }

        if (map[y][x - 1] == 3)
        {
            window.lockWindow();
            AcceptFightModal.modal(hero, window);
        }

        map[y][x] = 0;
        map[y][x - 1] = 2;

        return false;
    }

    public static boolean moveRight(Hero hero, int[][] map, SwingWindow window)
    {
        int[] position = Map.getPlayerPosition();
        int x = position[0];
        int y = position[1];

        if (map[y][x + 1] == 1)
        {
            window.lockWindow();
            WinLevelModal.modal(window);
            hero.newMap = true;
            return true;
        }

        if (map[y][x + 1] == 3)
        {
            window.lockWindow();
            AcceptFightModal.modal(hero, window);
        }

        map[y][x] = 0;
        map[y][x + 1] = 2;

        return false;
    }
}
