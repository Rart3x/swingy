package swingy.utils;

import swingy.models.artefacts.Artefact;
import swingy.models.characters.heroes.Hero;

import java.util.Scanner;

public class Utils {
    public static void lootRandomArtefact(Artefact randomArtefact, Hero hero)
    {
        Utils.printYellow("You found a " + randomArtefact.getName() + " artefact! Do you want to equip it? (yes/no)");

        String answer = System.console().readLine();

        while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"))
        {
            Utils.printRed("Invalid input. Please enter 'yes' or 'no'.");
            answer = System.console().readLine();
        }

        if (answer.equalsIgnoreCase("yes"))
        {
            Utils.printBlue("You have equipped the " + randomArtefact.getName() + " artefact.");
            hero.equipArtefact(randomArtefact);
        }
        else if (answer.equalsIgnoreCase("no"))
            Utils.printInfo("You decided not to equip the " + randomArtefact.getName() + " artefact.");
    }


    public static void printInfo(String message)
    {
        System.out.println("\033[34mInfo: " + message + "\033[0m");
    }

    public static void printError(String message)
    {
        System.out.println("\033[31mError: " + message + "\033[0m");
    }

    public static void printRed(String message)
    {
        System.out.println("\033[31m" + message + "\033[0m");
    }

    public static void printGreen(String message)
    {
        System.out.println("\033[32m" + message + "\033[0m");
    }

    public static void printYellow(String message)
    {
        System.out.println("\033[33m" + message + "\033[0m");
    }

    public static void printBlue(String message)
    {
        System.out.println("\033[34m" + message + "\033[0m");
    }
}
