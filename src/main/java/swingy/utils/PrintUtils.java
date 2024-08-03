package swingy.utils;

import swingy.models.artefacts.Artefact;

public class PrintUtils {

    public static void printArtefactCompare(Artefact artefact, Artefact currentArtefact)
    {
        printBlue("\nYou found a " + artefact.getName() + " artefact!");

        if (artefact.getAttack() > 0)
        {
            if (currentArtefact.getAttack() < artefact.getAttack())
                printGreen("Att: " + artefact.getAttack() + " (+" + (artefact.getAttack() - currentArtefact.getAttack()) + ")");
            else
                printRed("Att: " + artefact.getAttack() + " (" + (artefact.getAttack() - currentArtefact.getAttack()) + ")");
        }

        if (artefact.getDefense() > 0)
        {
            if (currentArtefact.getDefense() < artefact.getDefense())
                printGreen("Def: " + artefact.getDefense() + " (+" + (artefact.getDefense() - currentArtefact.getDefense()) + ")");
            else
                printRed("Def: " + artefact.getDefense() + " (" + (artefact.getDefense() - currentArtefact.getDefense()) + ")");
        }

        if (artefact.getHitPoints() > 0)
        {
            if (currentArtefact.getHitPoints() < artefact.getHitPoints())
                printGreen("HP: " + artefact.getHitPoints() + " (+" + (artefact.getHitPoints() - currentArtefact.getHitPoints()) + ")");
            else
                printRed("HP: " + artefact.getHitPoints() + " (" + (artefact.getHitPoints() - currentArtefact.getHitPoints()) + ")");
        }
    }

    public static void printInfo(String message) { System.out.println("\033[34mInfo: " + message + "\033[0m"); }
    public static void printError(String message) { System.out.println("\033[31mError: " + message + "\033[0m"); }
    public static void printRed(String message) { System.out.println("\033[31m" + message + "\033[0m"); }
    public static void printGreen(String message) { System.out.println("\033[32m" + message + "\033[0m"); }
    public static void printYellow(String message) { System.out.println("\033[33m" + message + "\033[0m"); }
    public static void printBlue(String message)  { System.out.println("\033[34m" + message + "\033[0m"); }
}
