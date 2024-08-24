package swingy.utils;

import swingy.model.characters.heroes.Hero;
import swingy.model.characters.villains.Villain;
import swingy.model.characters.villains.VillainFactory;
import swingy.model.fights.FightFactory;

public class Fight {
    public static boolean fightOrRun()
    {
        Print.printYellow("You hear a noise and turn around to see a villain. Do you want to fight or run? (fight/run)");

        String answer = System.console().readLine();

        while (!answer.equalsIgnoreCase("fight") && !answer.equalsIgnoreCase("run"))
        {
            Print.printRed("Invalid input. Please enter 'fight' or 'run'.");
            answer = System.console().readLine();
        }

        int randomNumber = (int)(Math.random() * 100);

        if (answer.equalsIgnoreCase("run"))
        {
            if (randomNumber < 50)
                return false;
            else
            {
                Print.printRed("\nYou failed to run away.");
                return true;
            }
        }
        else
            return true;
    }

    public static boolean fightOrRunResult(Hero hero)
    {
        if (fightOrRun())
        {
            Villain randomVillain = VillainFactory.createRandomVillain(hero.getLevel());
            Print.printYellow("\nYou have encountered a " + randomVillain.getName() + " villain.\n");
            swingy.model.fights.Fight fight = FightFactory.createFight(hero, randomVillain);
            fight.fight();
        }
        return hero.isDead;
    }
}
