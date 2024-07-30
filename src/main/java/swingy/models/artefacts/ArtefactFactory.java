package swingy.models.artefacts;

public final class ArtefactFactory {
    private static Artefact instance;

    private ArtefactFactory() {}

    public static Artefact createArtefact(String type, int value)
    {
        if (!type.equals("Weapon") && !type.equals("Armor") && !type.equals("Helm"))
            throw new IllegalArgumentException("Invalid type of artefact");

        switch (type)
        {
            case "Weapon" -> instance = new Weapon(value);
            case "Armor" -> instance = new Armor(value);
            case "Helm" -> instance = new Helm(value);
        }

        return instance;
    }
}
