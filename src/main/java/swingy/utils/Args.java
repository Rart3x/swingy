package swingy.utils;

public class Args {
    public static void checkArgs(String[] args)
    {
        if (args.length != 1)
        {
            Print.printError("Invalid number of arguments.");
            System.exit(1);
        }

        if (!args[0].equals("console") && !args[0].equals("gui"))
        {
            Print.printError("Invalid argument.");
            System.exit(1);
        }
    }
}
