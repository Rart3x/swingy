package swingy.models.fights;

import swingy.models.characters.heroes.Hero;
import swingy.models.characters.villains.Villain;
import swingy.utils.Utils;

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
