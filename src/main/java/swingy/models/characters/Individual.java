package swingy.models.characters;

public abstract class Individual {
    private String name, type ;
    private int level;

    public Individual(String name, String type, int level)
    {
        if (name == null || type == null || level < 1)
            throw new IllegalArgumentException("Invalid character parameters");

        if (!type.equals("Hero") && !type.equals("Villain"))
            throw new IllegalArgumentException("Invalid character type");

        this.name = name;
        this.type = type;
        this.level = level;
    }


    public String getName() { return name; }
    public String getType() { return type; }
    public int getLevel() { return level; }

    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setLevel(int level) { this.level = level; }
}
