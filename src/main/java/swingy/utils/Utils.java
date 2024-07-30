package swingy.utils;

public class Utils {
    public static void printInfo(String message)
    {
        System.out.println("\033[34mInfo:" + message + "\033[0m");
    }

    public static void printError(String message)
    {
        System.out.println("\033[31mError:" + message + "\033[0m");
    }
}
