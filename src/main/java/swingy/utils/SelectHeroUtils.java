package swingy.utils;

import swingy.models.characters.heroes.Hero;
import swingy.models.characters.heroes.HeroFactory;
import swingy.models.database.Database;

public class SelectHeroUtils {
    public static Hero selectHero() throws Exception {
        Hero instance = null;

        PrintUtils.printYellow("Do you want to create a new hero or load an existing one? (new/load/exit)");
        String answer = System.console().readLine();

        if (answer.equalsIgnoreCase("exit"))
            System.exit(0);

        while (!answer.equalsIgnoreCase("new") && !answer.equalsIgnoreCase("load"))
        {
            if (answer.equalsIgnoreCase("exit"))
                System.exit(0);

            PrintUtils.printRed("Invalid input. Please enter 'new' or 'load'.");
            answer = System.console().readLine();
        }

        if (answer.equalsIgnoreCase("load"))
        {
            selectDBHeroPrint();
            PrintUtils.printYellow("\nEnter the name of the hero you want to load");
            String heroName = System.console().readLine();

            try
            {
                instance = Database.loadHeroFromDB(heroName);
                if (instance == null)
                {
                    PrintUtils.printError("Hero not found.");
                    return selectHero();
                }
                return instance;
            }
            catch (Exception e) {
                PrintUtils.printError(e.getMessage());
            }
        }

        PrintUtils.printYellow("Select a hero class: Archer, Mage, Warrior\n");
        PrintUtils.printYellow("Archer: Attack 10, Defense 5, Hit Points 40\nMage: Attack 8, Defense 3, Hit Points 30\nWarrior: Attack 10, Defense 5, Hit Points 50");
        String heroClass = System.console().readLine();

        while (!heroClass.equalsIgnoreCase("Archer") && !heroClass.equalsIgnoreCase("Mage") && !heroClass.equalsIgnoreCase("Warrior"))
        {
            PrintUtils.printRed("Invalid hero class. Please enter 'Archer', 'Mage' or 'Warrior'.");
            heroClass = System.console().readLine();
        }

        PrintUtils.printYellow("Select a hero name");
        String heroName = System.console().readLine();

        instance = HeroFactory.createHero(heroName, heroClass);

        return instance;
    }

    public static void selectDBHeroPrint() throws Exception
    {
        Hero[] heros = Database.getHerosInDB();

        if (heros.length == 0)
            PrintUtils.printRed("No heroes found in the database.");
        else
        {
            PrintUtils.printYellow("Select a hero to load:\n");
            for (Hero hero : heros)
                PrintUtils.printYellow(hero.getName() + " the " + hero.getSubClass() + " level " + hero.getLevel() + " attack " + hero.getAttack() + " defense " + hero.getDefense() + " hit points " + hero.getHitPoints());
        }
    }
}
