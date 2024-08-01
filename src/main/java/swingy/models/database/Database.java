package swingy.models.database;

import swingy.models.characters.heroes.Hero;

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
            statement.execute("CREATE TABLE IF NOT EXISTS artefacts (id INTEGER PRIMARY KEY, name TEXT, type TEXT, attack INTEGER, defense INTEGER, hitPoints INTEGER)");
            statement.close();
            connection.close();
        }
    }

    public static void insertHero(Hero hero)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            int heroIdInDB = getHeroIdInDB(hero.getName());

            if (connection != null)
            {
                if (heroIdInDB != -1)
                {
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE heroes SET level = ?, experience = ?, attack = ?, defense = ?, hitPoints = ? WHERE name = ?");
                    preparedStatement.setInt(1, hero.getLevel());
                    preparedStatement.setInt(2, hero.getExperience());
                    preparedStatement.setInt(3, hero.getAttack());
                    preparedStatement.setInt(4, hero.getDefense());
                    preparedStatement.setInt(5, hero.getHitPoints());
                    preparedStatement.setString(6, hero.getName());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connection.close();
                }
                else
                {
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO heroes (name, type, level, experience, attack, defense, hitPoints) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    preparedStatement.setString(1, hero.getName());
                    preparedStatement.setString(2, hero.getType());
                    preparedStatement.setInt(3, hero.getLevel());
                    preparedStatement.setInt(4, hero.getExperience());
                    preparedStatement.setInt(5, hero.getAttack());
                    preparedStatement.setInt(6, hero.getDefense());
                    preparedStatement.setInt(7, hero.getHitPoints());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connection.close();
                }
            }

//            if (hero.getArmor() != null)
//                insertArtefact(hero.getArmor(), heroIdInDB);
//            if (hero.getHelm() != null)
//                insertArtefact(hero.getHelm(), heroIdInDB);
//            if (hero.getWeapon() != null)
//                insertArtefact(hero.getWeapon(), heroIdInDB);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
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
}
