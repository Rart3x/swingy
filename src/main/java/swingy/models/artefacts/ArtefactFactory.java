package swingy.models.artefacts;

public final class ArtefactFactory {
    private static Artefact instance;

    private ArtefactFactory() {}

    public static Artefact createArtefact(String name, String type, int value)
    {
        if (!type.equals("Weapon") && !type.equals("Armor") && !type.equals("Helm"))
            throw new IllegalArgumentException("Invalid type of artefact");

        switch (type)
        {
            case "Weapon" -> instance = new Weapon(name, value);
            case "Armor" -> instance = new Armor(name, value);
            case "Helm" -> instance = new Helm(name, value);
        }

        return instance;
    }
}
