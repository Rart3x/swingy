package swingy;

import swingy.models.artefacts.ArtefactFactory;
import swingy.models.artefacts.Artefact;

import swingy.models.characters.heroes.Hero;
import swingy.models.characters.heroes.HeroFactory;

import swingy.models.characters.villains.Villain;
import swingy.models.characters.villains.VillainFactory;

import swingy.utils.Utils;

public class Main {
    public static void main(String[] args)
    {
        try {
            Hero archer = HeroFactory.createHero("Legolas", "Archer");
            Hero warrior = HeroFactory.createHero("Conan", "Warrior");
            Hero sorcerer = HeroFactory.createHero("Gandalf", "Mage");

            Villain dragon = VillainFactory.createVillain("Smaug", "Dragon", 1);
            Villain goblin = VillainFactory.createVillain("Gollum", "Goblin", 1);
            Villain orc = VillainFactory.createVillain("Azog", "Orc", 1);

            Artefact sword = ArtefactFactory.createArtefact("Sword", 1);
            Artefact armor = ArtefactFactory.createArtefact("Armor", 1);
            Artefact helm = ArtefactFactory.createArtefact("Helm", 1);

            archer.setWeapon(sword);
            archer.setArmor(armor);
            archer.setHelm(helm);
        }
        catch (Exception e) {
            Utils.printError(e.getMessage());
        }
    }
}
