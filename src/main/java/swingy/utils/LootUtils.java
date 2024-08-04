package swingy.utils;

import swingy.models.artefacts.Artefact;
import swingy.models.characters.heroes.Hero;

public class LootUtils {
    public static void lootRandomArtefact(Artefact randomArtefact, Hero hero)
    {
        Artefact currentArtefact = null;
        String artefactType = randomArtefact.getType();

        switch (artefactType)
        {
            case "Weapon":
                currentArtefact = hero.getWeapon();
                hero.setWeapon(randomArtefact);
                break;
            case "Armor":
                currentArtefact = hero.getArmor();
                hero.setArmor(randomArtefact);
                break;
            case "Helm":
                currentArtefact = hero.getHelm();
                hero.setHelm(randomArtefact);
                break;
        }

        if (currentArtefact != null)
            PrintUtils.printArtefactCompare(randomArtefact, currentArtefact);
        else
        {
            PrintUtils.printBlue("\nYou found a " + randomArtefact.getName() + " artefact!");
            if (randomArtefact.getAttack() > 0)
                PrintUtils.printGreen("Att: " + randomArtefact.getAttack());
            if (randomArtefact.getDefense() > 0)
                PrintUtils.printGreen("Def: " + randomArtefact.getDefense());
            if (randomArtefact.getHitPoints() > 0)
                PrintUtils.printGreen("HP: " + randomArtefact.getHitPoints());
        }

        if (currentArtefact != null)
            PrintUtils.printYellow("\nDo you want to replace " + currentArtefact.getName() + " with " + randomArtefact.getName() + "? (yes/no)");
        else
            PrintUtils.printYellow("\nDo you want to equip it? (yes/no)");

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
            PrintUtils.printBlue("You decided not to equip the " + randomArtefact.getName() + " artefact.");
    }
}
