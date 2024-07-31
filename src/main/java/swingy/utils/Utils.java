package swingy.utils;

import swingy.models.artefacts.Artefact;
import swingy.models.characters.heroes.Hero;
import swingy.models.characters.heroes.HeroFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

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

    public static Hero loadHero(String heroName) throws Exception {
        String path = "src/main/resources/saves/" + heroName + ".txt";
        Hero loadedHero = HeroFactory.createHero(heroName, "Warrior");

        try (BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                if (line.startsWith("Name: ")) {
                    loadedHero.setName(line.substring(6));
                }
                else if (line.startsWith("Level: ")) {
                    loadedHero.setLevel(Integer.parseInt(line.substring(7)));
                }
                else if (line.startsWith("Experience Points: ")) {
                    loadedHero.setExperience(Integer.parseInt(line.substring(19)));
                }
                else if (line.startsWith("Attack: ")) {
                    loadedHero.setAttack(Integer.parseInt(line.substring(8)));
                }
                else if (line.startsWith("Defense: ")) {
                    loadedHero.setDefense(Integer.parseInt(line.substring(9)));
                }
                else if (line.startsWith("Hit Points: ")) {
                    loadedHero.setHitPoints(Integer.parseInt(line.substring(12)));
                }
                else if (line.startsWith("Current Hit Points: ")) {
                    loadedHero.setCurrentHitPoints(Integer.parseInt(line.substring(20)));
                }
//                else if (line.startsWith("Armor: ")) {
//                    loadedHero.setArmor(ArtefactFactory.createArtefact(line.substring(7), "Armor"));
//                }
//                else if (line.startsWith("Helm: ")) {
//                    loadedHero.setHelm(ArtefactFactory.createArtefact(line.substring(6), "Helm"));
//                }
//                else if (line.startsWith("Weapon: ")) {
//                    loadedHero.setWeapon(ArtefactFactory.createArtefact(line.substring(8), "Weapon"));
//                }
            }
        }
        return loadedHero;
    }

    public static void saveHero(Hero hero) throws Exception
    {
        String path = "src/main/resources/saves/" + hero.getName() + ".txt";

        try (FileWriter writer = new FileWriter(path))
        {
            writer.write("Name: " + hero.getName() + "\n");
            writer.write("Level: " + hero.getLevel() + "\n");
            writer.write("Experience Points: " + hero.getExperience() + "\n");
            writer.write("Attack: " + hero.getAttack() + "\n");
            writer.write("Defense: " + hero.getDefense() + "\n");
            writer.write("Hit Points: " + hero.getHitPoints() + "\n");
            writer.write("Current Hit Points: " + hero.getCurrentHitPoints() + "\n\n");

            writer.write("\n");

            writer.write("Artefacts:\n");
            if (hero.getArmor() != null) {
                writer.write("Armor: " + hero.getArmor().getName() + "\n");
                writer.write("Defense: " + hero.getArmor().getDefense() + "\n\n");
            }

            writer.write("\n");

            if (hero.getHelm() != null) {
                writer.write("Helm: " + hero.getHelm().getName() + "\n");
                writer.write("Hit Points: " + hero.getHelm().getHitPoints() + "\n\n");
            }

            writer.write("\n");

            if (hero.getWeapon() != null) {
                writer.write("Weapon: " + hero.getWeapon().getName() + "\n");
                writer.write("Attack: " + hero.getWeapon().getAttack() + "\n\n");
            }
        }
    }

    public static Hero selectHero()
    {
        Hero instance = null;

        Utils.printYellow("Do you want to create a new hero or load an existing one? (new/load)");
        String answer = System.console().readLine();

        while (!answer.equalsIgnoreCase("new") && !answer.equalsIgnoreCase("load"))
        {
            Utils.printRed("Invalid input. Please enter 'new' or 'load'.");
            answer = System.console().readLine();
        }

        if (answer.equalsIgnoreCase("load"))
        {
            Utils.printYellow("Enter the name of the hero you want to load");
            String heroName = System.console().readLine();
            try
            {
                instance = loadHero(heroName);
                return instance;
            }
            catch (Exception e)
            {
                Utils.printError(e.getMessage());
            }
        }

        Utils.printYellow("Select a hero class: Archer, Mage, Warrior");
        String heroClass = System.console().readLine();

        while (!heroClass.equalsIgnoreCase("Archer") && !heroClass.equalsIgnoreCase("Mage") && !heroClass.equalsIgnoreCase("Warrior"))
        {
            Utils.printRed("Invalid hero class. Please enter 'Archer', 'Mage' or 'Warrior'.");
            heroClass = System.console().readLine();
        }

        Utils.printYellow("Select a hero name");
        String heroName = System.console().readLine();

        switch (heroClass)
        {
            case "Archer" -> { instance = HeroFactory.createHero(heroName, "Archer"); }
            case "Mage" -> { instance = HeroFactory.createHero(heroName, "Mage"); }
            case "Warrior" -> { instance = HeroFactory.createHero(heroName, "Warrior"); }
        }
        return instance;
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
