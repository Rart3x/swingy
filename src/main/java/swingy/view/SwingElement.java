package swingy.view;

import swingy.model.characters.heroes.Hero;
import swingy.model.maps.Map;
import swingy.utils.Icon;

import javax.swing.*;
import java.awt.*;

public class SwingElement {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int CENTER_WIDTH = WIDTH / 2;

    public static void createSwitchButton(Hero hero, JPanel rightPanel, SwingWindow window)
    {
        JButton switchButton = new JButton("Switch");
        JButton quitButton = new JButton("Quit");

        switchButton.addActionListener(SwingListener.createSwitchListener(hero, window));
        quitButton.addActionListener(SwingListener.createQuitListener(hero, window));

        rightPanel.add(switchButton);
        rightPanel.add(quitButton);
    }

    public static void createDirectionButtons(Hero hero, Map map, JPanel middlePanel, JPanel rightBottomPanel, SwingWindow window)
    {
        rightBottomPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton northButton = new JButton("NORTH");
        JButton westButton = new JButton("WEST");
        JButton eastButton = new JButton("EAST");
        JButton southButton = new JButton("SOUTH");

        northButton.addActionListener(SwingListener.createDirectionListener(hero, map, "NORTH", middlePanel, window));
        westButton.addActionListener(SwingListener.createDirectionListener(hero, map,"WEST", middlePanel, window));
        eastButton.addActionListener(SwingListener.createDirectionListener(hero, map,"EAST", middlePanel, window));
        southButton.addActionListener(SwingListener.createDirectionListener(hero, map,"SOUTH", middlePanel, window));

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rightBottomPanel.add(northButton, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        rightBottomPanel.add(westButton, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        rightBottomPanel.add(eastButton, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rightBottomPanel.add(southButton, gbc);

        gbc.gridx = 0; gbc.gridy = 0;
        rightBottomPanel.add(new JLabel(), gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        rightBottomPanel.add(new JLabel(), gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        rightBottomPanel.add(new JLabel(), gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        rightBottomPanel.add(new JLabel(), gbc);
    }

    public static void createMap(Hero hero, Map map, JPanel middlePanel)
    {
        middlePanel.removeAll();

        int x = Map.getPlayerPosition()[0];
        int y = Map.getPlayerPosition()[1];

        int size = map.getSize();

        int VIEW_DISTANCE = 3;
        int VIEW_SIZE = VIEW_DISTANCE * 2 + 1;

        middlePanel.setLayout(new GridLayout(VIEW_SIZE, VIEW_SIZE));

        int startX = Math.max(0, Math.min(x - VIEW_DISTANCE, size - VIEW_SIZE));
        int startY = Math.max(0, Math.min(y - VIEW_DISTANCE, size - VIEW_SIZE));

        int buttonWidth = CENTER_WIDTH / VIEW_SIZE;
        int buttonHeight = HEIGHT / VIEW_SIZE;

        for (int j = 0; j < VIEW_SIZE; j++)
        {
            for (int i = 0; i < VIEW_SIZE; i++)
            {
                int realX = startX + i;
                int realY = startY + j;

                Image image = Icon.createImageIconDependingMapTile(x, y, realX, realY, hero);

                JButton button = new JButton(new ImageIcon(image));
                button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                button.setEnabled(realX >= 0 && realX < size && realY >= 0 && realY < size);
                middlePanel.add(button);
            }
        }

        middlePanel.revalidate();
        middlePanel.repaint();
    }

    public static void createTitleAndClassIcon(Hero hero, JPanel rightPanel)
    {
        JPanel titleIconPanel = new JPanel();
        titleIconPanel.setLayout(new BoxLayout(titleIconPanel, BoxLayout.X_AXIS));
        titleIconPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(" " + hero.getName());
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        ImageIcon icon = Icon.createImageIconDependingOnClass(hero.getSubClass());
        JLabel iconLabel = new JLabel(icon);

        titleIconPanel.add(iconLabel);
        titleIconPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        titleIconPanel.add(titleLabel);

        rightPanel.add(titleIconPanel);
    }

    public static void createHPBar(Hero hero, JPanel rightPanel)
    {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("HP");
        JProgressBar progressBar = new JProgressBar();

        progressBar.setMaximum(hero.getHitPoints());
        progressBar.setValue(hero.getCurrentHitPoints());
        progressBar.setStringPainted(true);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        containerPanel.add(titleLabel);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        containerPanel.add(progressBar);

        rightPanel.add(containerPanel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    public static void createXPBar(Hero hero, JPanel rightPanel)
    {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Lv. " + hero.getLevel());
        JProgressBar progressBar = new JProgressBar();

        progressBar.setMaximum((int) hero.getMaxExperience());
        progressBar.setValue(hero.getExperience());
        progressBar.setStringPainted(true);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        containerPanel.add(titleLabel);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        containerPanel.add(progressBar);

        rightPanel.add(containerPanel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    }
}
