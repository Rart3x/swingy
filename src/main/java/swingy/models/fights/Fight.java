package swingy.models.fights;

import swingy.models.artefacts.Artefact;
import swingy.models.artefacts.ArtefactFactory;
import swingy.models.characters.heroes.Hero;
import swingy.models.characters.villains.Villain;
import swingy.utils.Utils;

import java.util.Scanner;

public class Fight {
    private final Hero    hero;
    private final Villain villain;

    public Fight(Hero hero, Villain villain)
    {
        this.hero = hero;
        this.villain = villain;
    }

    public void fight()
    {
        int heroDamage = hero.getAttack() * (1 - (villain.getDefense() / 100));
        int villainDamage = villain.getAttack() * (1 - (hero.getDefense() / 100));

        Artefact randomArtefact = ArtefactFactory.createRandomArtefact(villain.getLevel());

        while (hero.getHitPoints() > 0 || villain.getHitPoints() > 0)
        {
            villain.looseHitPoints(heroDamage);
            Utils.printBlue(hero.getName() + " attacks " + villain.getName() + " and deals " + heroDamage + " damage");

            hero.looseHitPoints(villainDamage);
            Utils.printBlue(villain.getName() + " attacks " + hero.getName() + " and deals " + villainDamage + " damage");

            if (villain.getHitPoints() == 0)
            {
                Utils.printGreen(hero.getName() + " wins the fight and gains " + villain.getLevel() * 1000 + " experience");
                hero.gainExperience(hero.getExperience() + villain.getLevel() * 1000);

                double successProbability = Math.min(1.0, 0.1 * villain.getLevel());
                double randomValue = Math.random();

                if (randomValue < successProbability)
                {
                    Scanner scanner = new Scanner(System.in);

                    System.out.println("You found a " + randomArtefact.getName() + " artefact! Do you want to equip it? (yes/no)");

                    String answer = scanner.nextLine();

                    while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"))
                    {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        answer = scanner.nextLine();
                    }

                    if (answer.equalsIgnoreCase("yes"))
                    {
                        Utils.printInfo("You have equipped the " + randomArtefact.getName() + " artefact.");

                        switch (randomArtefact.getType())
                        {
                            case "Armor":
                                hero.setArmor(randomArtefact);
                                break;
                            case "Helm":
                                hero.setHelm(randomArtefact);
                                break;
                            case "Weapon":
                                hero.setWeapon(randomArtefact);
                                break;
                        }
                    }
                    else if (answer.equalsIgnoreCase("no"))
                        Utils.printInfo("You decided not to equip the " + randomArtefact.getName() + " artefact.");

                    scanner.close();

                }

                break;
            }
            else if (hero.getHitPoints() == 0)
            {
                Utils.printRed(hero.getName() + " looses the fight");
                break;
            }
        }
    }
}
