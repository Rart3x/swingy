package swingy.view;

import swingy.models.characters.heroes.Hero;
import swingy.models.maps.Map;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingListener {
    public static ActionListener createDirectionListener(Hero hero, Map map, String direction, JPanel middlePanel)
    {
        return e ->
        {
            switch (direction)
            {
                case "NORTH":
                    SwingMove.moveUp(hero, Map.getMap());
                    break;
                case "SOUTH":
                    SwingMove.moveDown(hero, Map.getMap());
                    break;
                case "WEST":
                    SwingMove.moveLeft(hero, Map.getMap());
                    break;
                case "EAST":
                    SwingMove.moveRight(hero, Map.getMap());
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
