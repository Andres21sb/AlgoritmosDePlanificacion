package am.project.presentation;

import javax.swing.*;

public class View {
    private JPanel panelPrincipal;
    private JLabel mainLabel;
    private JPanel panel2;
    private JPanel panel3;
    private JButton AgregarButton;
    private JTextField NombreTextField;
    private JTextField DuracionTextField;
    private JTextField LlegadaTextField;
    private JTextField PrioridadTextField;

    public View() {
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
}
