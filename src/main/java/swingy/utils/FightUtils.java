package swingy.utils;

import swingy.models.characters.heroes.Hero;
import swingy.models.characters.villains.Villain;
import swingy.models.characters.villains.VillainFactory;
import swingy.models.fights.Fight;
import swingy.models.fights.FightFactory;

public class FightUtils {
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
                return false;
            else
            {
                PrintUtils.printRed("\nYou failed to run away.");
                return true;
            }
        }
        else
            return true;
    }

    static boolean fightOrRunResult(Hero hero)
    {
        if (fightOrRun())
        {
            Villain randomVillain = VillainFactory.createRandomVillain(hero.getLevel());
            PrintUtils.printYellow("\nYou have encountered a " + randomVillain.getName() + " villain.\n");
            Fight fight = FightFactory.createFight(hero, randomVillain);
            fight.fight();
        }

        return hero.getIsDead();
    }
}
