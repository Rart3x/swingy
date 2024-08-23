package swingy.view.modals;

import swingy.models.artefacts.Artefact;
import swingy.models.characters.heroes.Hero;
import swingy.view.SwingWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcceptArtefactModal {
    public static void createAcceptArtefactModal(Hero hero, Artefact randomArtefact, SwingWindow window)
    {
        JFrame frame = new JFrame("You found an artefact!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Do you want to equip it?", JLabel.CENTER);
        frame.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        yesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                hero.equipArtefact(randomArtefact);
                window.unlockWindow();
                frame.dispose();
            }
        });

        noButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
