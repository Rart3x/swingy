package swingy.models.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Delete {
    public static void deleteArtefact(String artefactName)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            if (connection != null)
            {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM artefacts WHERE name = ?");
                preparedStatement.setString(1, artefactName);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
