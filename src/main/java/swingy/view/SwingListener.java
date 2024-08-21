package swingy.view;

import swingy.models.characters.heroes.Hero;
import swingy.models.maps.Map;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SwingListener {
    public static ActionListener createDirectionListener(Hero hero, Map map, String direction, JPanel middlePanel, SwingWindow window)
    {
        return e ->
        {
            switch (direction)
            {
                //TODO: Fix the move methods
                case "NORTH":
                    SwingMove.moveLeft(hero, map.getMap(), window);
                    break;
                case "SOUTH":
                    SwingMove.moveRight(hero, map.getMap(), window);
                    break;
                case "WEST":
                    SwingMove.moveUp(hero, map.getMap(), window);
                    break;
                case "EAST":
                    SwingMove.moveDown(hero, map.getMap(), window);
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
}
