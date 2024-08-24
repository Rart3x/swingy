package swingy.utils;

import swingy.models.characters.heroes.Hero;
import swingy.models.maps.Map;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Icon {
    public static ImageIcon createImageIcon(String path)
    {
        return new ImageIcon(path);
    }

    public static ImageIcon createImageIconDependingOnClass(String heroClass)
    {
        ImageIcon instance = null;

        switch (heroClass)
        {
            case "Archer":
                instance = new ImageIcon("src/main/resources/icons/bow1.png");
                break;
            case "Mage":
                instance = new ImageIcon("src/main/resources/icons/staff.png");
                break;
            case "Warrior":
                instance = new ImageIcon("src/main/resources/icons/sword1.png");
                break;
        }
        return instance;
    }

    public static Image createImageIconDependingMapTile(int x, int y, int realX, int realY, Hero hero)
    {
        Image image = null;

        if (realX == x && realY == y)
        {
            if (Objects.equals(hero.getSubClass(), "Archer"))
                image = createImageIcon("src/main/resources/icons/bow1.png").getImage();
            else if (Objects.equals(hero.getSubClass(), "Mage"))
                image = createImageIcon("src/main/resources/icons/staff1.png").getImage();
            else if (Objects.equals(hero.getSubClass(), "Warrior"))
                image = createImageIcon("src/main/resources/icons/sword1.png").getImage();
        }
        else
        {
            if (Map.getMap()[realY][realX] == 0 || (Map.getMap()[realY][realX] == 3 && !Map.isWalkedTile(realX, realY)))
                image = createImageIcon("src/main/resources/icons/grass.png").getImage();
            if (Map.getMap()[realY][realX] == 1)
                image = createImageIcon("src/main/resources/icons/wall.png").getImage();
            if (Map.isWalkedTile(realX, realY))
                image = createImageIcon("src/main/resources/icons/death.png").getImage();
        }
        return image;
    }
}
