package swingy.utils;

import swingy.models.artefacts.Artefact;
import swingy.view.SwingWindow;

public class PrintUtils {

    public static void printArtefactCompare(Artefact artefact, Artefact currentArtefact, boolean mode)
    {
        switch (artefact.getType())
        {
            case "Armor":
                if (!mode)
                    printBlue("\nYou found a " + artefact.getName() + " armor!");
                else
                    SwingWindow.addText("You found a " + artefact.getName() + " armor!");
                break;
            case "Helm":
                if (!mode)
                    printBlue("\nYou found a " + artefact.getName() + " helmet!");
                else
                    SwingWindow.addText("You found a " + artefact.getName() + " helmet!");
                break;
            case "Weapon":
                if (!mode)
                    printBlue("\nYou found a " + artefact.getName() + " weapon!");
                else
                    SwingWindow.addText("You found a " + artefact.getName() + " weapon!");
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

    public static void printError(String message) { System.out.println("\033[31mError: " + message + "\033[0m"); }
    public static void printRed(String message) { System.out.println("\033[31m" + message + "\033[0m"); }
    public static void printGreen(String message) { System.out.println("\033[32m" + message + "\033[0m"); }
    public static void printYellow(String message) { System.out.println("\033[33m" + message + "\033[0m"); }
    public static void printBlue(String message)  { System.out.println("\033[34m" + message + "\033[0m"); }
}
