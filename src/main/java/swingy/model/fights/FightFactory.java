package swingy.model.fights;

import swingy.model.characters.heroes.Hero;
import swingy.model.characters.villains.Villain;

public class FightFactory {
    private static Fight instance;

    private FightFactory() {}

    public static Fight createFight(Hero hero, Villain villain)
    {
        instance = new Fight(hero, villain);
        return instance;
    }
}
