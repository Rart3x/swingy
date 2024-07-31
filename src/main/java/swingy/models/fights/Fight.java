package swingy.models.fights;

import swingy.models.artefacts.Artefact;
import swingy.models.artefacts.ArtefactFactory;
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

        Artefact randomArtefact = ArtefactFactory.createRandomArtefact(villain.getLevel());

        while (hero.getCurrentHitPoints() > 0 || villain.getHitPoints() > 0)
        {
            villain.looseHitPoints(heroDamage, hero.getName());

            if (villain.getHitPoints() == 0)
            {
                hero.gainExperience(hero.getExperience() + villain.getLevel() * 1000);

                double successProbability = Math.min(1.0, 0.05 * villain.getLevel());
                double randomValue = Math.random();

                if (randomValue < successProbability)
                    Utils.lootRandomArtefact(randomArtefact, hero);
                break;
            }
            else
            {
                hero.looseHitPoints(villainDamage, villain.getName());
                if (hero.getCurrentHitPoints() == 0)
                    break;
            }
        }
    }
}
