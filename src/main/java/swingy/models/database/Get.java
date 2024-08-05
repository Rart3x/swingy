package swingy.models.database;

import swingy.models.artefacts.Artefact;
import swingy.models.characters.heroes.Hero;
import swingy.models.characters.heroes.HeroFactory;

import java.sql.ResultSet;

public class Get extends Database {
    public static int getArtefactIdInDB(String artefactName)
    {
        int artefactId = -1;

        try
        {
            Class.forName("org.sqlite.JDBC");

            if (connection != null)
            {
                preparedStatement = connection.prepareStatement("SELECT id FROM artefacts WHERE name = ?");
                preparedStatement.setString(1, artefactName);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.getInt("id") != 0)
                    artefactId = resultSet.getInt("id");

                return artefactId;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return artefactId;
    }

    public static int getHeroIdInDB(String heroName)
    {
        int heroId = -1;

        try
        {
            Class.forName("org.sqlite.JDBC");

            if (connection != null)
            {
                preparedStatement = connection.prepareStatement("SELECT id FROM heroes WHERE name = ?");
                preparedStatement.setString(1, heroName);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.getInt("id") != 0)
                    heroId = resultSet.getInt("id");

                return heroId;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return heroId;
    }

    public static Hero[] getHerosInDB() throws Exception
    {
        Hero[] heros = null;

        try
        {
            Class.forName("org.sqlite.JDBC");

            if (connection != null)
            {
                preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM heroes");

                ResultSet resultSet = preparedStatement.executeQuery();

                int count = resultSet.getInt(1);
                heros = new Hero[count];

                preparedStatement = connection.prepareStatement("SELECT * FROM heroes");
                resultSet = preparedStatement.executeQuery();

                int i = 0;

                while (resultSet.next())
                {
                    String name = resultSet.getString("name");
                    String subClass = resultSet.getString("subClass");

                    int experience = resultSet.getInt("experience");
                    int level = resultSet.getInt("level");

                    int attack = resultSet.getInt("attack");
                    int defense = resultSet.getInt("defense");
                    int hitPoints = resultSet.getInt("hitPoints");
                    int currentHitPoints = resultSet.getInt("currentHitPoints");

                    int heroId = getHeroIdInDB(name);

                    preparedStatement = connection.prepareStatement("SELECT * FROM artefacts WHERE heroId = ?");
                    preparedStatement.setInt(1, heroId);
                    ResultSet newResultSet = preparedStatement.executeQuery();

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
                            case "Armor":
                                armor = new Artefact(artefactName, artefactType, artefactAttack, artefactDefense, artefactHitPoints);
                                break;
                            case "Helm":
                                helm = new Artefact(artefactName, artefactType, artefactAttack, artefactDefense, artefactHitPoints);
                                break;
                            case "Weapon":
                                weapon = new Artefact(artefactName, artefactType, artefactAttack, artefactDefense, artefactHitPoints);
                                break;
                        }
                    }

                    heros[i] = HeroFactory.createHero(name, subClass);

                    heros[i].setExperience(experience);
                    heros[i].setLevel(level);

                    heros[i].setAttack(attack);
                    heros[i].setDefense(defense);
                    heros[i].setHitPoints(hitPoints);
                    heros[i].setCurrentHitPoints(currentHitPoints);

                    if (armor != null)
                        heros[i].equipArtefact(armor);
                    if (helm != null)
                        heros[i].equipArtefact(helm);
                    if (weapon != null)
                        heros[i].equipArtefact(weapon);

                    i++;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return heros;
    }

    public static Hero getHeroFromDB(String heroName)
    {
        Hero hero = null;
        int heroId = getHeroIdInDB(heroName);

        if (heroId != -1)
        {
            try
            {
                Class.forName("org.sqlite.JDBC");

                if (connection != null)
                {
                    preparedStatement = connection.prepareStatement("SELECT * FROM heroes WHERE id = ?");
                    preparedStatement.setInt(1, heroId);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    String name = resultSet.getString("name");
                    String subClass = resultSet.getString("subClass");
                    int level = resultSet.getInt("level");
                    int experience = resultSet.getInt("experience");
                    int attack = resultSet.getInt("attack");
                    int defense = resultSet.getInt("defense");
                    int hitPoints = resultSet.getInt("hitPoints");
                    int currentHitPoints = resultSet.getInt("currentHitPoints");

                    preparedStatement = connection.prepareStatement("SELECT * FROM artefacts WHERE heroId = ?");
                    preparedStatement.setInt(1, heroId);

                    ResultSet newResultSet = preparedStatement.executeQuery();

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
                            case "Armor":
                                armor = new Artefact(artefactName, artefactType, artefactAttack, artefactDefense, artefactHitPoints);
                                break;
                            case "Helm":
                                helm = new Artefact(artefactName, artefactType, artefactAttack, artefactDefense, artefactHitPoints);
                                break;
                            case "Weapon":
                                weapon = new Artefact(artefactName, artefactType, artefactAttack, artefactDefense, artefactHitPoints);
                                break;
                        }
                    }

                    hero = HeroFactory.createHero(name, subClass);

                    hero.setAttack(attack);
                    hero.setDefense(defense);
                    hero.setHitPoints(hitPoints);
                    hero.setCurrentHitPoints(currentHitPoints);
                    hero.setLevel(level);
                    hero.setExperience(experience);

                    if (armor != null)
                        hero.equipArtefact(armor);
                    if (helm != null)
                        hero.equipArtefact(helm);
                    if (weapon != null)
                        hero.equipArtefact(weapon);
                }

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return hero;
    }
}
