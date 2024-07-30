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
        try {
            Hero archer = HeroFactory.createHero("Legolas", "Archer");
            Hero sorcerer = HeroFactory.createHero("Gandalf", "Mage");
            Hero warrior = HeroFactory.createHero("Conan", "Warrior");

            Villain dragon = VillainFactory.createVillain("Smaug", "Dragon", 1);
            Villain goblin = VillainFactory.createVillain("Gollum", "Goblin", 1);
            Villain orc = VillainFactory.createVillain("Azog", "Orc", 1);

            Artefact sword = ArtefactFactory.createArtefact("Excalibur", "Weapon", 1);
            Artefact armor = ArtefactFactory.createArtefact("Adamantium armor", "Armor", 1);
            Artefact helm = ArtefactFactory.createArtefact("Morokei", "Helm", 1);

            Map map = MapFactory.createMap(archer.getLevel());

            archer.setArmor(armor);
            sorcerer.setHelm(helm);
            warrior.setWeapon(sword);

            Utils.printInfo(archer.getArmor().getName());
            Utils.printInfo(sorcerer.getHelm().getName());
            Utils.printInfo(warrior.getWeapon().getName());

            map.printMap();
        }
        catch (Exception e) {
            Utils.printError(e.getMessage());
        }
    }
}
