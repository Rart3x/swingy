package swingy.utils;

import swingy.model.artefacts.Artefact;
import swingy.model.characters.heroes.Hero;
import swingy.view.SwingWindow;
import swingy.view.modals.AcceptArtefactModal;

public class Loot {
    public static void lootRandomArtefact(Artefact randomArtefact, Hero hero, boolean mode, SwingWindow window)
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
            Print.printArtefactCompare(randomArtefact, currentArtefact, mode);
        else
            Print.printDependingOnArtefact(randomArtefact, mode);

        if (!mode)
        {
            if (currentArtefact != null)
                Print.printYellow("\nDo you want to replace " + currentArtefact.getName() + " with " + randomArtefact.getName() + "? (yes/no)");
            else
                Print.printYellow("\nDo you want to equip it? (yes/no)");

            String answer = System.console().readLine();

            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"))
            {
                Print.printRed("Invalid input. Please enter 'yes' or 'no'.");
                answer = System.console().readLine();
            }

            if (answer.equalsIgnoreCase("yes"))
            {
                Print.printBlue("You have equipped the " + randomArtefact.getName() + " artefact.");
                hero.equipArtefact(randomArtefact);
            }
            else if (answer.equalsIgnoreCase("no"))
                Print.printBlue("You decided not to equip the " + randomArtefact.getName() + " artefact.");
        }
        else
        {
            window.lockWindow();
            AcceptArtefactModal.modal(hero, randomArtefact, window);
        }
    }
}
