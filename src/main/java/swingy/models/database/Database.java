package swingy.models.database;

import swingy.models.artefacts.Armor;
import swingy.models.artefacts.Artefact;
import swingy.models.characters.heroes.Hero;
import swingy.models.characters.heroes.HeroFactory;

import java.sql.* ;

public class Database {

    public static void createDB() throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

        if (connection != null)
        {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS heroes (id INTEGER PRIMARY KEY, name TEXT, type TEXT, level INTEGER, experience INTEGER, attack INTEGER, defense INTEGER, hitPoints INTEGER)");
            statement.execute("CREATE TABLE IF NOT EXISTS artefacts (" + "id INTEGER PRIMARY KEY," + "name TEXT," + "type TEXT," + "attack INTEGER," + "defense INTEGER," + "hitPoints INTEGER," + "heroId INTEGER," + "FOREIGN KEY(heroId) REFERENCES heroes(id)" + ")");
            statement.close();
            connection.close();
        }
    }

    public static int getHeroIdInDB(String heroName)
    {
        int heroId = -1;

        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            if (connection != null)
            {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM heroes WHERE name = ?");
                preparedStatement.setString(1, heroName);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.getInt("id") != 0)
                    heroId = resultSet.getInt("id");

                preparedStatement.close();
                connection.close();

                return heroId;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return heroId;
    }

    public static void insertArtefact(Artefact artefact, int heroIdInDB)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            if (connection != null)
            {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO artefacts (name, type, attack, defense, hitPoints, heroId) VALUES (?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, artefact.getName());
                preparedStatement.setString(2, artefact.getType());
                preparedStatement.setInt(3, artefact.getAttack());
                preparedStatement.setInt(4, artefact.getDefense());
                preparedStatement.setInt(5, artefact.getHitPoints());
                preparedStatement.setInt(6, heroIdInDB);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertHero(Hero hero)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            int heroIdInDB = getHeroIdInDB(hero.getName());

            Artefact armor = hero.getArmor();
            Artefact helm = hero.getHelm();
            Artefact weapon = hero.getWeapon();

            if (armor != null)
                hero.unequipArtefact(armor);
            if (helm != null)
                hero.unequipArtefact(helm);
            if (weapon != null)
                hero.unequipArtefact(weapon);

            if (connection != null)
            {
                PreparedStatement preparedStatement;

                if (heroIdInDB != -1)
                {
                    preparedStatement = connection.prepareStatement("UPDATE heroes SET level = ?, experience = ?, attack = ?, defense = ?, hitPoints = ? WHERE name = ?");
                    preparedStatement.setInt(1, hero.getLevel());
                    preparedStatement.setInt(2, hero.getExperience());
                    preparedStatement.setInt(3, hero.getAttack());
                    preparedStatement.setInt(4, hero.getDefense());
                    preparedStatement.setInt(5, hero.getHitPoints());
                    preparedStatement.setString(6, hero.getName());
                }
                else
                {
                    preparedStatement = connection.prepareStatement("INSERT INTO heroes (name, type, level, experience, attack, defense, hitPoints) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    preparedStatement.setString(1, hero.getName());
                    preparedStatement.setString(2, hero.getType());
                    preparedStatement.setInt(3, hero.getLevel());
                    preparedStatement.setInt(4, hero.getExperience());
                    preparedStatement.setInt(5, hero.getAttack());
                    preparedStatement.setInt(6, hero.getDefense());
                    preparedStatement.setInt(7, hero.getHitPoints());
                }
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            }

            heroIdInDB = getHeroIdInDB(hero.getName());
            if (armor != null)
                insertArtefact(armor, heroIdInDB);
            if (helm != null)
                insertArtefact(helm, heroIdInDB);
            if (weapon != null)
                insertArtefact(weapon, heroIdInDB);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Hero loadHeroFromDB(String heroName)
    {
        Hero hero = null;
        int heroId = getHeroIdInDB(heroName);

        if (heroId != -1)
        {
            try
            {
                Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

                if (connection != null)
                {
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM heroes WHERE id = ?");
                    preparedStatement.setInt(1, heroId);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    int level = resultSet.getInt("level");
                    int experience = resultSet.getInt("experience");
                    int attack = resultSet.getInt("attack");
                    int defense = resultSet.getInt("defense");
                    int hitPoints = resultSet.getInt("hitPoints");

                    PreparedStatement newPrepareStatement = connection.prepareStatement("SELECT * FROM artefacts WHERE heroId = ?");
                    newPrepareStatement.setInt(1, heroId);

                    ResultSet newResultSet = newPrepareStatement.executeQuery();

                    Artefact armor = null;
                    Artefact helm = null;
                    Artefact weapon = null;

                    while (newResultSet.next())
                    {
                        String artefactName = newResultSet.getString("name");
                        String artefactType = newResultSet.getString("type");
                        int artefactAttack = newResultSet.getInt("attack");
                        int artefactDefense = newResultSet.getInt("defense");
                        int artefactHitPoints = newResultSet.getInt("hitPoints");

                        switch (artefactType)
                        {
                            case "Armor" ->
                                    armor = new Artefact(artefactName, artefactType, artefactAttack, artefactDefense, artefactHitPoints);
                            case "Helm" ->
                                    helm = new Artefact(artefactName, artefactType, artefactAttack, artefactDefense, artefactHitPoints);
                            case "Weapon" ->
                                    weapon = new Artefact(artefactName, artefactType, artefactAttack, artefactDefense, artefactHitPoints);
                        }
                    }

                    hero = HeroFactory.createHero(name, type);

                    hero.setAttack(attack);
                    hero.setDefense(defense);
                    hero.setHitPoints(hitPoints);
                    hero.setLevel(level);
                    hero.setExperience(experience);

                    if (armor != null)
                        hero.setArmor(armor);
                    if (helm != null)
                        hero.setHelm(helm);
                    if (weapon != null)
                        hero.setWeapon(weapon);

                    preparedStatement.close();
                    connection.close();
                }

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return hero;
    }

}
