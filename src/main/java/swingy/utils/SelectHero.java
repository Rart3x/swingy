package swingy.utils;

import swingy.model.characters.heroes.Hero;
import swingy.model.characters.heroes.HeroFactory;
import swingy.model.database.Get;

public class SelectHero {
    public static Hero selectHero() throws Exception {
        Hero instance = null;

        Print.printYellow("Do you want to create a new hero or load an existing one? (new/load/exit)");
        String answer = System.console().readLine();

        if (answer.equalsIgnoreCase("exit"))
            System.exit(0);

        while (!answer.equalsIgnoreCase("new") && !answer.equalsIgnoreCase("load"))
        {
            if (answer.equalsIgnoreCase("exit"))
                System.exit(0);

            Print.printRed("Invalid input. Please enter 'new', 'load' or 'exit'");
            answer = System.console().readLine();
        }

        if (answer.equalsIgnoreCase("load"))
        {
            if (!selectDBHeroPrint())
                return SelectHero.selectHero();

            Print.printYellow("\nEnter the name of the hero you want to load");
            String heroName = System.console().readLine();

            try
            {
                instance = Get.getHeroFromDB(heroName);
                if (instance == null)
                {
                    Print.printError("Hero not found");
                    return selectHero();
                }
                return instance;
            }
            catch (Exception e) {
                Print.printError(e.getMessage());
            }
        }

        Print.printYellow("Select a hero class: Archer, Mage, Warrior\nArcher: Attack 10, Defense 5, Hit Points 40\nMage: Attack 8, Defense 3, Hit Points 30\nWarrior: Attack 10, Defense 5, Hit Points 50");

        String heroClass = System.console().readLine();
        heroClass = heroClass.substring(0, 1).toUpperCase() + heroClass.substring(1).toLowerCase();

        while (!heroClass.equals("Archer") && !heroClass.equals("Mage") && !heroClass.equals("Warrior"))
        {
            Print.printRed("Invalid hero class. Please enter 'Archer', 'Mage' or 'Warrior'");
            heroClass = System.console().readLine();
        }

        Print.printYellow("Select a hero name");
        String heroName = System.console().readLine();

        instance = HeroFactory.createHero(heroName, heroClass);

        return instance;
    }

    public static boolean selectDBHeroPrint() throws Exception
    {
        Hero[] heros = Get.getHerosInDB();

        if (heros.length == 0)
        {
            Print.printRed("No heroes found in the database");
            return false;
        }
        else
        {
            Print.printYellow("Select a hero to load:\n");
            for (Hero hero : heros)
                Print.printYellow(hero.getName() + " the " + hero.getSubClass() + " level " + hero.getLevel() + " attack " + hero.getAttack() + " defense " + hero.getDefense() + " hit points " + hero.getHitPoints());
        }
        return true;
    }
}
