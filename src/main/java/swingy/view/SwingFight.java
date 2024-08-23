package swingy.view;

import swingy.models.artefacts.Artefact;
import swingy.models.artefacts.ArtefactFactory;
import swingy.models.characters.heroes.Hero;
import swingy.models.characters.villains.Villain;
import swingy.utils.LootUtils;

public class SwingFight {
    private final Hero hero;
    private final Villain villain;

    public SwingFight(Hero hero, Villain villain)
    {
        this.hero = hero;
        this.villain = villain;
    }

    public void fight(SwingWindow window)
    {
        int heroDamage = hero.getAttack() * (1 - (villain.getDefense() / 100));
        int villainDamage = villain.getAttack() * (1 - (hero.getDefense() / 100));

        Artefact randomArtefact = ArtefactFactory.createRandomArtefact(villain.getLevel());

        while (hero.getCurrentHitPoints() > 0 || villain.getHitPoints() > 0)
        {
            villain.looseHitPoints(heroDamage, hero.getName(), true);

            if (villain.getHitPoints() == 0)
            {
                hero.gainExperience(hero.getExperience() + villain.getLevel() * 1000, true);

                double successProbability = Math.min(1.0, 0.05 * villain.getLevel());
                double randomValue = Math.random();

                if (randomValue < successProbability)
                    LootUtils.lootRandomArtefact(randomArtefact, hero, true, window);
                break;
            }
            else
            {
                hero.looseHitPoints(villainDamage, villain.getName(), true);
                if (hero.getCurrentHitPoints() == 0)
                    break;
            }
        }
    }
}
