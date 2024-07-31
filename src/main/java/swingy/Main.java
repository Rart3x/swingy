package swingy;

import swingy.models.artefacts.ArtefactFactory;
import swingy.models.artefacts.Artefact;

import swingy.models.characters.heroes.Hero;
import swingy.models.characters.heroes.HeroFactory;

import swingy.models.characters.villains.Villain;
import swingy.models.characters.villains.VillainFactory;

import swingy.models.map.Map;
import swingy.models.map.MapFactory;

import swingy.utils.Utils;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            Hero archer = HeroFactory.createHero("Legolas", "Archer");
            Map map = MapFactory.createMap(archer.getLevel());

            map.printMap();

            map.move("NORTH");
            map.move("NORTH");
            map.move("NORTH");
            map.move("NORTH");
            map.move("NORTH");
            map.move("NORTH");
            map.move("NORTH");
            map.move("EAST");
            map.move("EAST");
            map.move("EAST");
            map.move("EAST");
            map.move("EAST");
            map.move("EAST");

            System.out.println();

            map.printMap();
        }
        catch (Exception e) {
            Utils.printError(e.getMessage());
        }
    }
}
