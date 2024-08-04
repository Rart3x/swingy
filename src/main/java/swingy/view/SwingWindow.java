package swingy.view;

import swingy.models.characters.heroes.Hero;
import swingy.models.maps.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SwingWindow extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int CENTER_WIDTH = WIDTH / 2;

    private static final String TITLE = "Swingy";

    private JPanel leftPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel rightPanel = new JPanel();


    public SwingWindow(Hero hero, Map map) {}

    public void createWindow(Hero hero, Map map)
    {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        leftPanel.setBackground(Color.BLUE);
        rightPanel.setBackground(Color.RED);

        Dimension leftPanelSize = new Dimension(WIDTH / 4, HEIGHT);
        Dimension middlePanelSize = new Dimension(WIDTH / 2, HEIGHT);
        Dimension rightPanelSize = new Dimension(WIDTH / 4, HEIGHT);

        leftPanel.setPreferredSize(leftPanelSize);
        rightPanel.setPreferredSize(rightPanelSize);

        setLayout(new BorderLayout());

        updateCenterPanelContent(hero, map);
        updateRightPanelContent(hero, map);

        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        Timer timer = new Timer(250, new ActionListener()
        {
            int lastKnownHeroX = Map.getPlayerPosition()[0];
            int lastKnownHeroY = Map.getPlayerPosition()[1];

            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean needsUpdate = false;
                if (Map.getPlayerPosition()[0] != lastKnownHeroX || Map.getPlayerPosition()[1] != lastKnownHeroY)
                {
                    needsUpdate = true;
                    lastKnownHeroX = Map.getPlayerPosition()[0];
                    lastKnownHeroY = Map.getPlayerPosition()[1];
                }

                if (needsUpdate)
                    updateCenterPanelContent(hero, map);
            }
        });
        timer.start();

        setVisible(true);
    }

    public void closeWindow()
    {
        setVisible(false);
        dispose();
    }

    public void updateCenterPanelContent(Hero hero, Map map)
    {
        middlePanel.removeAll();

        SwingElement.createMap(hero, map, middlePanel);

        revalidate();
        repaint();
    }

    public void updateRightPanelContent(Hero hero, Map map)
    {
        rightPanel.removeAll();

        SwingElement.createTitleLevelAndClassIcon(hero, rightPanel);
        SwingElement.createHPBar(hero, rightPanel);
        SwingElement.createXPBar(hero, rightPanel);
        SwingElement.createDirectionButtons(hero, map, middlePanel,  rightPanel);
        SwingElement.createSwitchButton(hero, rightPanel, this);

        revalidate();
        repaint();
    }
}
