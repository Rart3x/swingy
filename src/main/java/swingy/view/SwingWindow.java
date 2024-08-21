package swingy.view;

import swingy.models.characters.heroes.Hero;
import swingy.models.maps.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SwingWindow extends JFrame {
    private static final String TITLE = "Swingy";

    // -------------------- Sizes -------------------- //
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private static final int HALF_WIDTH = WIDTH / 2;
    private static final int HALF_HEIGHT = HEIGHT / 2;
    private static final int QUARTER_WIDTH = WIDTH / 4;
    private static final int QUARTER_HEIGHT = HEIGHT / 4;

    // -------------------- Panels -------------------- //
    private JPanel middleParentPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel middleBottomPanel = new JPanel();

    private JPanel rightParentPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel rightBottomPanel = new JPanel();

    // -------------------- Panel Sizes -------------------- //
    private Dimension middlePanelSize = new Dimension(WIDTH, HALF_HEIGHT); // Adjusted size
    private Dimension middleBottomPanelSize = new Dimension(WIDTH, QUARTER_HEIGHT); // Adjusted size
    private Dimension rightPanelSize = new Dimension(QUARTER_WIDTH, HEIGHT);
    private Dimension rightBottomPanelSize = new Dimension(QUARTER_WIDTH, QUARTER_HEIGHT);

    // -------------------- Text Area -------------------- //
    private static JTextArea textArea = new JTextArea();


    public SwingWindow(Hero hero, Map map) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createWindow(Hero hero, Map map) {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);

        middlePanel.setPreferredSize(middlePanelSize);
        middleBottomPanel.setPreferredSize(middleBottomPanelSize);
        rightPanel.setPreferredSize(rightPanelSize);
        rightBottomPanel.setPreferredSize(rightBottomPanelSize);

        middleParentPanel.setLayout(new BorderLayout());
        middleParentPanel.add(middlePanel, BorderLayout.CENTER);
        middleParentPanel.add(middleBottomPanel, BorderLayout.SOUTH);

        rightParentPanel.setLayout(new BorderLayout());
        rightParentPanel.add(rightPanel, BorderLayout.CENTER);
        rightParentPanel.add(rightBottomPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());

        updateCenterPanelContent(hero, map);
        updateCenterPanelBottomContent(hero, map);
        updateRightPanelContent(hero, map);
        updateRightPanelBottomContent(hero, map);

        add(middleParentPanel, BorderLayout.CENTER); // Now occupies the center
        add(rightParentPanel, BorderLayout.EAST);

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

    public void updateCenterPanelBottomContent(Hero hero, Map map)
    {
        middleBottomPanel.removeAll();

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(middleBottomPanel.getWidth(), middleBottomPanel.getHeight()));

        middleBottomPanel.setLayout(new BorderLayout());
        middleBottomPanel.add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void updateRightPanelContent(Hero hero, Map map)
    {
        rightPanel.removeAll();

        SwingElement.createTitleLevelAndClassIcon(hero, rightPanel);
        SwingElement.createHPBar(hero, rightPanel);
        SwingElement.createXPBar(hero, rightPanel);
//        SwingElement.createSwitchButton(hero, rightPanel, this);

        revalidate();
        repaint();
    }

    public void updateRightPanelBottomContent(Hero hero, Map map)
    {
        rightBottomPanel.removeAll();

        SwingElement.createDirectionButtons(hero, map, middlePanel,  rightBottomPanel);

        revalidate();
        repaint();
    }

    public static void addText(String text)
    {
        textArea.append(text + "\n");
    }
}
