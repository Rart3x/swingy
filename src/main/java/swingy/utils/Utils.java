package swingy.utils;

public class Utils {

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
