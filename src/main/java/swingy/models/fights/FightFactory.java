package swingy.models.fights;

import swingy.models.characters.heroes.Hero;
import swingy.models.characters.villains.Villain;

public class FightFactory {
    private static Fight instance;

    private FightFactory() {}

    public static Fight createFight(Hero hero, Villain villain)
    {
        instance = new Fight(hero, villain);
        return instance;
    }
}
