package swingy.view.modals;

import swingy.models.characters.heroes.Hero;
import swingy.models.characters.villains.Villain;
import swingy.models.characters.villains.VillainFactory;
import swingy.view.SwingFight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingAcceptFightModal {
    public static void createAndShowModal(Hero hero)
    {
        JFrame frame = new JFrame("You encountered an villain!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("What do you want to do ?", JLabel.CENTER);
        frame.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton fightButton = new JButton("Fight");
        JButton runButton = new JButton("Run");

        buttonPanel.add(fightButton);
        buttonPanel.add(runButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        fightButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Villain randomVillain = VillainFactory.createRandomVillain(hero.getLevel());
                SwingFight fight = new SwingFight(hero, randomVillain);
                fight.fight();

                frame.dispose();
            }
        });

        runButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int randomNumber = (int)(Math.random() * 100);

                frame.dispose();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
