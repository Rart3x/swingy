package swingy.models.database;

public class Delete extends Database {
    public static void deleteArtefact(String artefactName)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");

            if (connection != null)
            {
                preparedStatement = connection.prepareStatement("DELETE FROM artefacts WHERE name = ?");
                preparedStatement.setString(1, artefactName);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
