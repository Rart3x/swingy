package swingy.view;

import javax.swing.*;

public class SwingWindow {
    public SwingWindow()
    {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingWindow());
    }
}

