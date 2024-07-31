package swingy;

import swingy.models.characters.heroes.Hero;
import swingy.models.characters.heroes.HeroFactory;

import swingy.models.fights.Fight;
import swingy.models.fights.FightFactory;
import swingy.models.maps.Map;
import swingy.models.maps.MapFactory;

import swingy.models.characters.villains.Villain;
import swingy.models.characters.villains.VillainFactory;

import swingy.utils.Utils;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            Hero    archer = HeroFactory.createHero("Legolas", "Archer");
            Map     map    = MapFactory.createMap(archer.getLevel());

            Utils.printYellow("\n");

            map.move(archer);
        }
        catch (Exception e) {
            Utils.printError(e.getMessage());
        }
    }
}
