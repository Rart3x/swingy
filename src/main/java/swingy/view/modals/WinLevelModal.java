package swingy.view.modals;

import swingy.view.SwingWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinLevelModal {
    public static void modal(SwingWindow window)
    {
        JFrame frame = new JFrame("Congratulations!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("You have reached a new level!", JLabel.CENTER);
        frame.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton okButton = new JButton("OK");

        buttonPanel.add(okButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                window.unlockWindow();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
