package swingy.view;

import swingy.models.characters.heroes.Hero;
import swingy.models.maps.Map;

import javax.swing.*;
import java.awt.event.ActionListener;
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
                case "WEST":
                    SwingMove.moveLeft(hero, Map.getMap());
                    break;
                case "EAST":
                    SwingMove.moveRight(hero, Map.getMap());
                    break;
                case "SOUTH":
                    SwingMove.moveDown(hero, Map.getMap());
                    break;
            }
            SwingElement.createMap(hero, map, middlePanel);
        };
    }

    public static KeyListener createKeyListener(Hero hero, Map map, JPanel middlePanel)
    {
        return new KeyListener()
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {}

            @Override
            public void keyPressed(java.awt.event.KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    case java.awt.event.KeyEvent.VK_UP:
                        SwingMove.moveUp(hero, Map.getMap());
                        break;
                    case java.awt.event.KeyEvent.VK_DOWN:
                        SwingMove.moveDown(hero, Map.getMap());
                        break;
                    case java.awt.event.KeyEvent.VK_LEFT:
                        SwingMove.moveLeft(hero, Map.getMap());
                        break;
                    case java.awt.event.KeyEvent.VK_RIGHT:
                        SwingMove.moveRight(hero, Map.getMap());
                        break;
                }
                SwingElement.createMap(hero, map, middlePanel);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {}
        };
    }
}
