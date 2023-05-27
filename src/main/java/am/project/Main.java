package am.project;

import am.project.presentation.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Crear el frame
        JFrame frame = new JFrame("mainFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1320, 720);
        frame.setLocationRelativeTo(null);

        View view = new View();
        frame.setContentPane(view.getPanelPrincipal());
        frame.setVisible(true);
    }
}