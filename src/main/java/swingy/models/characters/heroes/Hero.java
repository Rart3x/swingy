package swingy.models.characters.heroes;

import swingy.models.artefacts.Armor;
import swingy.models.artefacts.Artefact;
import swingy.models.artefacts.Helm;
import swingy.models.artefacts.Weapon;
import swingy.models.characters.Individual;

public class Hero extends Individual {
    private Artefact armor;
    private Artefact helm;
    private Artefact weapon;

    private int experience;

    public Hero(String name)
    {
        super(name, "Hero", 1);
        this.experience = 0;
    }

    public int getExperience() { return experience; }

    public Artefact getArmor() { return armor; }
    public Artefact getHelm() { return helm; }
    public Artefact getWeapon() { return weapon; }

    public void setExperience(int experience) { this.experience = experience; }

    public void setArmor(Artefact armor) { this.armor = armor; }
    public void setHelm(Artefact helm) { this.helm = helm; }
    public void setWeapon(Artefact weapon) { this.weapon = weapon; }
}
