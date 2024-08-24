package swingy.model.fights;

import swingy.model.artefacts.Artefact;
import swingy.model.artefacts.ArtefactFactory;
import swingy.model.characters.heroes.Hero;
import swingy.model.characters.villains.Villain;
import swingy.utils.Loot;

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
            villain.looseHitPoints(heroDamage, hero.getName(), false);

            if (villain.getHitPoints() == 0)
            {
                hero.gainExperience(hero.getExperience() + villain.getLevel() * 1000, false);

                double successProbability = Math.min(1.0, 0.05 * villain.getLevel());
                double randomValue = Math.random();

                if (randomValue < successProbability)
                    Loot.lootRandomArtefact(randomArtefact, hero, false, null);
                break;
            }
            else
            {
                hero.looseHitPoints(villainDamage, villain.getName(),false);
                if (hero.getCurrentHitPoints() == 0)
                    break;
            }
        }
    }
}
