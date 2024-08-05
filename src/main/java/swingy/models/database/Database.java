package swingy.models.database;

import java.sql.* ;

public class Database {

    public static void createDB() throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

        if (connection != null)
        {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS heroes (id INTEGER PRIMARY KEY, name TEXT, subClass TEXT, level INTEGER, experience INTEGER, attack INTEGER, defense INTEGER, hitPoints INTEGER, currentHitPoints INTEGER)");
            statement.execute("CREATE TABLE IF NOT EXISTS artefacts (id INTEGER PRIMARY KEY, name TEXT, type TEXT, attack INTEGER, defense INTEGER, hitPoints INTEGER, heroId INTEGER, FOREIGN KEY(heroId) REFERENCES heroes(id) )");
            statement.close();
            connection.close();
        }
    }
}
