package swingy.utils;

public class ArgsUtils {
    public static void checkArgs(String[] args)
    {
        if (args.length != 1)
        {
            PrintUtils.printError("Invalid number of arguments.");
            System.exit(1);
        }

        if (!args[0].equals("console") && !args[0].equals("gui"))
        {
            PrintUtils.printError("Invalid argument.");
            System.exit(1);
        }
    }
}
