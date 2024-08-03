package swingy.utils;

import javax.swing.*;

public class WindowUtils {
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
}
