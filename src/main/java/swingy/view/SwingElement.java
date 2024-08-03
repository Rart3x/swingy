package swingy.view;

import swingy.models.characters.heroes.Hero;
import swingy.models.maps.Map;
import swingy.utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SwingElement {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int CENTER_WIDTH = WIDTH / 2;


    public static void createDirectionButtons(Hero hero, Map map, JPanel middlePanel, JPanel rightPanel)
    {
        JButton northButton = new JButton("NORTH");
        JButton westButton = new JButton("WEST");
        JButton eastButton = new JButton("EAST");
        JButton southButton = new JButton("SOUTH");

        northButton.addActionListener(SwingListener.createDirectionListener(hero, map, "NORTH", middlePanel));
        westButton.addActionListener(SwingListener.createDirectionListener(hero, map,"WEST", middlePanel));
        eastButton.addActionListener(SwingListener.createDirectionListener(hero, map,"EAST", middlePanel));
        southButton.addActionListener(SwingListener.createDirectionListener(hero, map,"SOUTH", middlePanel));

        rightPanel.add(northButton);
        rightPanel.add(westButton);
        rightPanel.add(eastButton);
        rightPanel.add(southButton);
    }

    public static void createMap(Hero hero, Map map, JPanel middlePanel)
    {
        middlePanel.removeAll();

        int heroY = Map.getPlayerPosition()[0];
        int heroX = Map.getPlayerPosition()[1];
        int size = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);

        middlePanel.setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                Image image = null;
                JButton button = new JButton();

                button.setPreferredSize(new Dimension(CENTER_WIDTH / size, HEIGHT / size));
                button.setEnabled(true);

                if (i == heroX && j == heroY)
                {
                    if (Objects.equals(hero.getSubClass(), "Archer"))
                        image = WindowUtils.createImageIcon("src/main/resources/icons/bow1.png").getImage();
                    else if (Objects.equals(hero.getSubClass(), "Mage"))
                        image = WindowUtils.createImageIcon("src/main/resources/icons/staff1.png").getImage();
                    else
                        image = WindowUtils.createImageIcon("src/main/resources/icons/sword.png").getImage();
                    button.setIcon(new ImageIcon(image));
                }
//                else if (Map.getMap()[i][j] == 1)
//                    image = WindowUtils.createImageIcon("src/main/resources/icons/bow1.png").getImage();
//                else if (Map.getMap()[i][j] == 2)
//                    image = WindowUtils.createImageIcon("src/main/resources/icons/hat1.png").getImage();
//                else
//                    image = WindowUtils.createImageIcon("src/main/resources/icons/staff1.png").getImage();

                middlePanel.add(button);
            }
        }
    }

    public static void createTitleLevelAndClassIcon(Hero hero, JPanel rightPanel)
    {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(hero.getName());
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);

        JLabel levelLabel = new JLabel(" Level " + String.valueOf(hero.getLevel()));
        levelLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        levelLabel.setVerticalAlignment(SwingConstants.TOP);

        ImageIcon icon = WindowUtils.createImageIconDependingOnClass(hero.getSubClass());
        JLabel iconLabel = new JLabel(icon);

        Box titleBox = Box.createHorizontalBox();
        Box levelBox = Box.createHorizontalBox();
        Box buttonBox = Box.createHorizontalBox();

        titleBox.add(titleLabel);
        titleBox.add(Box.createRigidArea(new Dimension(10, 0)));

        levelBox.add(levelLabel);
        buttonBox.add(iconLabel);

        rightPanel.add(titleBox);
        rightPanel.add(buttonBox);
    }


    public static void createHPBar(Hero hero, JPanel rightPanel)
    {
        JLabel titleLabel = new JLabel("HP");
        JPanel containerPanel = new JPanel();
        JProgressBar progressBar = new JProgressBar();

        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        progressBar.setMaximum(hero.getHitPoints());
        progressBar.setValue(hero.getCurrentHitPoints());
        progressBar.setStringPainted(true);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        containerPanel.add(titleLabel);
        containerPanel.add(progressBar);

        rightPanel.add(containerPanel);
    }

    public static void createXPBar(Hero hero, JPanel rightPanel)
    {
        JLabel titleLabel = new JLabel("XP");
        JPanel containerPanel = new JPanel();
        JProgressBar progressBar = new JProgressBar();

        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        progressBar.setMaximum((int)hero.getMaxExperience());
        progressBar.setValue(hero.getExperience());
        progressBar.setStringPainted(true);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        containerPanel.add(titleLabel);
        containerPanel.add(progressBar);

        rightPanel.add(containerPanel);
    }
}
