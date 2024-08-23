package swingy.utils;

import swingy.models.artefacts.Artefact;
import swingy.view.SwingWindow;

public class Print {

    public static void printArtefactCompare(Artefact artefact, Artefact currentArtefact, boolean mode)
    {
        switch (artefact.getType())
        {
            case "Armor":
                printDependingOnMode("You found a " + artefact.getName() + " armor!", "BLUE", mode);
                break;
            case "Helm":
                printDependingOnMode("You found a " + artefact.getName() + " helmet!", "BLUE", mode);
                break;
            case "Weapon":
                printDependingOnMode("You found a " + artefact.getName() + " weapon!", "BLUE", mode);
                break;
        }

        if (artefact.getAttack() > 0)
        {
            if (!mode)
            {
                if (currentArtefact.getAttack() < artefact.getAttack())
                    printGreen("Att: " + artefact.getAttack() + " (+" + (artefact.getAttack() - currentArtefact.getAttack()) + ")");
                else
                    printRed("Att: " + artefact.getAttack() + " (" + (artefact.getAttack() - currentArtefact.getAttack()) + ")");
            }
            else
            {
                if (currentArtefact.getAttack() < artefact.getAttack())
                    SwingWindow.addText("Att: " + artefact.getAttack() + " (+" + (artefact.getAttack() - currentArtefact.getAttack()) + ")");
                else
                    SwingWindow.addText("Att: " + artefact.getAttack() + " (" + (artefact.getAttack() - currentArtefact.getAttack()) + ")");
            }
        }

        if (artefact.getDefense() > 0)
        {
            if (!mode)
            {
                if (currentArtefact.getDefense() < artefact.getDefense())
                    printGreen("Def: " + artefact.getDefense() + " (+" + (artefact.getDefense() - currentArtefact.getDefense()) + ")");
                else
                    printRed("Def: " + artefact.getDefense() + " (" + (artefact.getDefense() - currentArtefact.getDefense()) + ")");
            }
            else
            {
                if (currentArtefact.getDefense() < artefact.getDefense())
                    SwingWindow.addText("Def: " + artefact.getDefense() + " (+" + (artefact.getDefense() - currentArtefact.getDefense()) + ")");
                else
                    SwingWindow.addText("Def: " + artefact.getDefense() + " (" + (artefact.getDefense() - currentArtefact.getDefense()) + ")");
            }
        }

        if (artefact.getHitPoints() > 0)
        {
            if (!mode)
            {
                if (currentArtefact.getHitPoints() < artefact.getHitPoints())
                    printGreen("HP: " + artefact.getHitPoints() + " (+" + (artefact.getHitPoints() - currentArtefact.getHitPoints()) + ")");
                else
                    printRed("HP: " + artefact.getHitPoints() + " (" + (artefact.getHitPoints() - currentArtefact.getHitPoints()) + ")");
            }
            else
            {
                if (currentArtefact.getHitPoints() < artefact.getHitPoints())
                    SwingWindow.addText("HP: " + artefact.getHitPoints() + " (+" + (artefact.getHitPoints() - currentArtefact.getHitPoints()) + ")");
                else
                    SwingWindow.addText("HP: " + artefact.getHitPoints() + " (" + (artefact.getHitPoints() - currentArtefact.getHitPoints()) + ")");
            }
        }
    }

    public static void printDependingOnArtefact(Artefact artefact, boolean mode)
    {
        if (!mode)
        {
            Print.printBlue("\nYou found a " + artefact.getName() + " artefact!");
            if (artefact.getAttack() > 0)
                Print.printGreen("Att: " + artefact.getAttack());
            if (artefact.getDefense() > 0)
                Print.printGreen("Def: " + artefact.getDefense());
            if (artefact.getHitPoints() > 0)
                Print.printGreen("HP: " + artefact.getHitPoints());
        }
        else
        {
            SwingWindow.addText("\nYou found a " + artefact.getName() + " artefact!");
            if (artefact.getAttack() > 0)
                SwingWindow.addText("Att: " + artefact.getAttack());
            if (artefact.getDefense() > 0)
                SwingWindow.addText("Def: " + artefact.getDefense());
            if (artefact.getHitPoints() > 0)
                SwingWindow.addText("HP: " + artefact.getHitPoints());
        }
    }

    public static void printDependingOnMode(String message, String color, boolean mode)
    {
        if (!mode)
        {
            switch (color)
            {
                case "BLUE":
                    printBlue(message);
                    break;
                case "GREEN":
                    printGreen(message);
                    break;
                case "RED":
                    printRed(message);
                    break;
                case "YELLOW":
                    printYellow(message);
                    break;
            }
        }
        else
            SwingWindow.addText(message);
    }

    public static void printError(String message) { System.out.println("\033[31mError: " + message + "\033[0m"); }
    public static void printRed(String message) { System.out.println("\033[31m" + message + "\033[0m"); }
    public static void printGreen(String message) { System.out.println("\033[32m" + message + "\033[0m"); }
    public static void printYellow(String message) { System.out.println("\033[33m" + message + "\033[0m"); }
    public static void printBlue(String message)  { System.out.println("\033[34m" + message + "\033[0m"); }
}
