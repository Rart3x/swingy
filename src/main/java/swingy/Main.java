package swingy;

import swingy.models.artefacts.Artefact;
import swingy.models.artefacts.ArtefactFactory;
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
            Hero hero = Utils.selectHero();
            Map  map  = MapFactory.createMap(hero.getLevel());

            Utils.printYellow("\n");

            while (true)
            {
                map.move(hero);
                map = MapFactory.createMap(hero.getLevel());
            }
        }
        catch (Exception e) {
            Utils.printError(e.getMessage());
        }


    }
}
