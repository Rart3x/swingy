package swingy.view;

import swingy.model.characters.heroes.Hero;
import swingy.model.maps.Map;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SwingListener {
    public static ActionListener createDirectionListener(Hero hero, Map map, String direction, JPanel middlePanel, SwingWindow window)
    {
        return e ->
        {
            switch (direction)
            {
                case "NORTH":
                    SwingMove.moveUp(hero, map.getMap(), window);
                    break;
                case "SOUTH":
                    SwingMove.moveDown(hero, map.getMap(), window);
                    break;
                case "WEST":
                    SwingMove.moveLeft(hero, map.getMap(), window);
                    break;
                case "EAST":
                    SwingMove.moveRight(hero, map.getMap(), window);
                    break;
            }
            SwingElement.createMap(hero, map, middlePanel);
        };
    }

    public static ActionListener createSwitchListener(Hero hero, SwingWindow window)
    {
        return e ->
        {
            hero.setMode("console");
            window.closeWindow();
        };
    }

    public static ActionListener createQuitListener(Hero hero, SwingWindow window)
    {
        return e ->
        {
            hero.stop = true;
            window.closeWindow();
        };
    }
}
