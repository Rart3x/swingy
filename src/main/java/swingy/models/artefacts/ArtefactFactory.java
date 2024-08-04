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
            case "Weapon":
                instance = new Weapon(name, value);
                break;
            case "Armor":
                instance = new Armor(name, value);
                break;
            case "Helm":
                instance = new Helm(name, value);
                break;
        }
        return instance;
    }

    public static Artefact createRandomArtefact(int villainLevel)
    {
        String[] artefactTypes = {"Weapon", "Armor", "Helm"};
        String[] weaponNames = {"Excalibur", "Mjolnir", "Merunes Dagon", "Sword of a Thousand Truths", "Enma", "Master Sword"};
        String[] armorNames = {"Dragonplate", "Daedric", "Elven", "Glass"};
        String[] helmNames = {"Iron", "Steel", "Leather", "Cloth"};

        String type = artefactTypes[(int)(Math.random() * 3)];
        String name;

        switch (type)
        {
            case "Weapon":
                name = weaponNames[(int)(Math.random() * 6)];
                break;
            case "Armor":
                name = armorNames[(int)(Math.random() * 4)];
                break;
            case "Helm":
                name = helmNames[(int)(Math.random() * 4)];
                break;
            default :
                name = "";
        }

        int min = villainLevel * 5;
        int max = villainLevel * 10;

        int value = (int)(Math.random() * (max - min + 1)) + min;

        return createArtefact(name, type, value);
    }
}
