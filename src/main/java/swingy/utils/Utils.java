package swingy.utils;

import swingy.models.artefacts.Artefact;
import swingy.models.characters.heroes.Hero;
import swingy.models.characters.heroes.HeroFactory;
import swingy.models.characters.villains.Villain;
import swingy.models.characters.villains.VillainFactory;
import swingy.models.database.Database;
import swingy.models.fights.Fight;
import swingy.models.fights.FightFactory;

public class Utils {
    public static boolean fightOrRun()
    {
        PrintUtils.printYellow("You hear a noise and turn around to see a villain. Do you want to fight or run? (fight/run)");

        String answer = System.console().readLine();

        while (!answer.equalsIgnoreCase("fight") && !answer.equalsIgnoreCase("run"))
        {
            PrintUtils.printRed("Invalid input. Please enter 'fight' or 'run'.");
            answer = System.console().readLine();
        }

        int randomNumber = (int)(Math.random() * 100);

        if (answer.equalsIgnoreCase("run"))
        {
            if (randomNumber < 50)
            {
                PrintUtils.printGreen("You successfully ran away.");
                return false;
            }
            else
            {
                PrintUtils.printRed("You failed to run away.");
                return true;
            }
        }
        else
        {
            PrintUtils.printGreen("You decided to fight!");
            return true;
        }
    }

    static boolean fightOrRunResult(Hero hero)
    {
        if (Utils.fightOrRun())
        {
            Villain randomVillain = VillainFactory.createRandomVillain(hero.getLevel());
            PrintUtils.printYellow("You have encountered a " + randomVillain.getName() + " villain.");
            Fight fight = FightFactory.createFight(hero, randomVillain);
            fight.fight();
        }

        return hero.getIsDead();
    }

    public static void lootRandomArtefact(Artefact randomArtefact, Hero hero)
    {
        PrintUtils.printYellow("You found a " + randomArtefact.getName() + " artefact! Do you want to equip it? (yes/no)");

        String answer = System.console().readLine();

        while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"))
        {
            PrintUtils.printRed("Invalid input. Please enter 'yes' or 'no'.");
            answer = System.console().readLine();
        }

        if (answer.equalsIgnoreCase("yes"))
        {
            PrintUtils.printBlue("You have equipped the " + randomArtefact.getName() + " artefact.");
            hero.equipArtefact(randomArtefact);
        }
        else if (answer.equalsIgnoreCase("no"))
            PrintUtils.printInfo("You decided not to equip the " + randomArtefact.getName() + " artefact.");
    }

    public static Hero selectHero()
    {
        Hero instance = null;

        PrintUtils.printYellow("Do you want to create a new hero or load an existing one? (new/load/exit)");
        String answer = System.console().readLine();

        if (answer.equalsIgnoreCase("exit"))
            System.exit(0);

        while (!answer.equalsIgnoreCase("new") && !answer.equalsIgnoreCase("load") && !answer.equalsIgnoreCase("exit"))
        {
            if (answer.equalsIgnoreCase("exit"))
                System.exit(0);

            PrintUtils.printRed("Invalid input. Please enter 'new' or 'load'.");
            answer = System.console().readLine();
        }

        if (answer.equalsIgnoreCase("load"))
        {
            PrintUtils.printYellow("Enter the name of the hero you want to load");
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

        PrintUtils.printYellow("Select a hero class: Archer, Mage, Warrior");
        String heroClass = System.console().readLine();

        while (!heroClass.equalsIgnoreCase("Archer") && !heroClass.equalsIgnoreCase("Mage") && !heroClass.equalsIgnoreCase("Warrior"))
        {
            PrintUtils.printRed("Invalid hero class. Please enter 'Archer', 'Mage' or 'Warrior'.");
            heroClass = System.console().readLine();
        }

        PrintUtils.printYellow("Select a hero name");
        String heroName = System.console().readLine();

        switch (heroClass)
        {
            case "Archer" :  { instance = HeroFactory.createHero(heroName, "Archer"); }
            case "Mage" :  { instance = HeroFactory.createHero(heroName, "Mage"); }
            case "Warrior" :  { instance = HeroFactory.createHero(heroName, "Warrior"); }
        }
        return instance;
    }

}
