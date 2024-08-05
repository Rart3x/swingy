package swingy.models.database;

import swingy.models.artefacts.Artefact;
import swingy.models.characters.heroes.Hero;

import java.sql.PreparedStatement;

public class Insert extends Database {
    public static void insertArtefact(Artefact artefact, int heroIdInDB)
    {
        try
        {
            int artefactIdInDB = Get.getArtefactIdInDB(artefact.getName());

            Class.forName("org.sqlite.JDBC");

            if (connection != null)
            {
                PreparedStatement preparedStatement;

                if (artefactIdInDB != -1)
                {
                    preparedStatement = connection.prepareStatement("UPDATE artefacts SET type = ?, attack = ?, defense = ?, hitPoints = ?, heroId = ? WHERE name = ?");
                    preparedStatement.setString(1, artefact.getType());
                    preparedStatement.setInt(2, artefact.getAttack());
                    preparedStatement.setInt(3, artefact.getDefense());
                    preparedStatement.setInt(4, artefact.getHitPoints());
                    preparedStatement.setInt(5, heroIdInDB);
                    preparedStatement.setString(6, artefact.getName());
                }
                else
                {
                    preparedStatement = connection.prepareStatement("INSERT INTO artefacts (name, type, attack, defense, hitPoints, heroId) VALUES (?, ?, ?, ?, ?, ?)");
                    preparedStatement.setString(1, artefact.getName());
                    preparedStatement.setString(2, artefact.getType());
                    preparedStatement.setInt(3, artefact.getAttack());
                    preparedStatement.setInt(4, artefact.getDefense());
                    preparedStatement.setInt(5, artefact.getHitPoints());
                    preparedStatement.setInt(6, heroIdInDB);
                }
                preparedStatement.executeUpdate();
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

            int heroIdInDB = Get.getHeroIdInDB(hero.getName());

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
                if (heroIdInDB != -1)
                {
                    preparedStatement = connection.prepareStatement("UPDATE heroes SET level = ?, experience = ?, attack = ?, defense = ?, hitPoints = ?, currentHitPoints = ? WHERE name = ?");
                    preparedStatement.setInt(1, hero.getLevel());
                    preparedStatement.setInt(2, hero.getExperience());
                    preparedStatement.setInt(3, hero.getAttack());
                    preparedStatement.setInt(4, hero.getDefense());
                    preparedStatement.setInt(5, hero.getHitPoints());
                    preparedStatement.setInt(6, hero.getCurrentHitPoints());
                    preparedStatement.setString(7, hero.getName());
                }
                else
                {
                    preparedStatement = connection.prepareStatement("INSERT INTO heroes (name, subClass, level, experience, attack, defense, hitPoints, currentHitPoints) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                    preparedStatement.setString(1, hero.getName());
                    preparedStatement.setString(2, hero.getSubClass());
                    preparedStatement.setInt(3, hero.getLevel());
                    preparedStatement.setInt(4, hero.getExperience());
                    preparedStatement.setInt(5, hero.getAttack());
                    preparedStatement.setInt(6, hero.getDefense());
                    preparedStatement.setInt(7, hero.getHitPoints());
                    preparedStatement.setInt(8, hero.getCurrentHitPoints());
                }
                preparedStatement.executeUpdate();
            }

            heroIdInDB = Get.getHeroIdInDB(hero.getName());

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
}
