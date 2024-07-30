package swingy.models.map;

public final class MapFactory {
    private static Map instance;

    private MapFactory() {}

    public static Map createMap(int heroLevel)
    {
        int size = (heroLevel - 1) * 5 + 10 - (heroLevel % 2);

        instance = new Map(size);
        return instance;
    }
}
