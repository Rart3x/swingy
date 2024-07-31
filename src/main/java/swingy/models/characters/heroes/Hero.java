package swingy.models.characters.heroes;

import swingy.models.artefacts.Artefact;
import swingy.models.characters.AIndividual;

public class Hero extends AIndividual {
    protected Artefact armor;
    protected Artefact helm;
    protected Artefact weapon;

    protected int experience;

    public Hero(String name)
    {
        super(name, "Hero", 1);
        this.experience = 0;
    }

    public void gainExperience(int experience)
    {
        double maxExperience = (this.level * 1000) + Math.pow((this.level - 1), 2) * 450;

        if ((this.experience + experience) >= maxExperience)
        {
            this.experience = (this.experience + experience) - (int)maxExperience;
            this.level += 1;
        }
        else
            this.experience += experience;
    }


    public int      getExperience() { return experience; }
    public Artefact getArmor() { return armor; }
    public Artefact getHelm() { return helm; }
    public Artefact getWeapon() { return weapon; }

    public void setArmor(Artefact armor) { this.armor = armor; }
    public void setHelm(Artefact helm) { this.helm = helm; }
    public void setWeapon(Artefact weapon) { this.weapon = weapon; }
}
