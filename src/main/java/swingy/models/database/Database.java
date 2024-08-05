package swingy.models.database;

import java.sql.* ;

public class Database {

    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static Statement statement = null;

    public static void createDB() throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

        if (connection != null)
        {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS heroes (id INTEGER PRIMARY KEY, name TEXT, subClass TEXT, level INTEGER, experience INTEGER, attack INTEGER, defense INTEGER, hitPoints INTEGER, currentHitPoints INTEGER)");
            statement.execute("CREATE TABLE IF NOT EXISTS artefacts (id INTEGER PRIMARY KEY, name TEXT, type TEXT, attack INTEGER, defense INTEGER, hitPoints INTEGER, heroId INTEGER, FOREIGN KEY(heroId) REFERENCES heroes(id) )");
        }
    }

    public static void closeDB() throws Exception
    {
        if (connection != null)
        {
            connection.close();
            preparedStatement.close();
            statement.close();
        }
    }
}
