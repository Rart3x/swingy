package swingy.view;

import swingy.models.characters.heroes.Hero;
import swingy.models.maps.Map;
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
            WinLevelModal.createWinLevelModal(window);
            hero.newMap = true;
            return true;
        }

        if (map[y - 1][x] == 3)
        {
            window.lockWindow();
            AcceptFightModal.createAndShowModal(hero, window);
            Map.addWalkedTile(x, y - 1);
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
            WinLevelModal.createWinLevelModal(window);
            hero.newMap = true;
            return true;
        }

        if (map[y + 1][x] == 3)
        {
            window.lockWindow();
            AcceptFightModal.createAndShowModal(hero, window);
            Map.addWalkedTile(x, y + 1);
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
            WinLevelModal.createWinLevelModal(window);
            hero.newMap = true;
            return true;
        }

        if (map[y][x - 1] == 3)
        {
            window.lockWindow();
            AcceptFightModal.createAndShowModal(hero, window);
            Map.addWalkedTile(x - 1, y);
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
            WinLevelModal.createWinLevelModal(window);
            hero.newMap = true;
            return true;
        }

        if (map[y][x + 1] == 3)
        {
            window.lockWindow();
            AcceptFightModal.createAndShowModal(hero, window);
            Map.addWalkedTile(x + 1, y);
        }

        map[y][x] = 0;
        map[y][x + 1] = 2;

        return false;
    }
}
