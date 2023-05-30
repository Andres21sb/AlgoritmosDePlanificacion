package am.project.presentation;
import am.project.logic.Proceso;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


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
    private JTextArea ProcesosTextArea; // Añade este campo
    private Proceso proceso;

    private List<Proceso> listaProcesos;

    public View() {
        listaProcesos = new ArrayList<>();
        AgregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = NombreTextField.getText();
                int duracion = Integer.parseInt(DuracionTextField.getText());
                int llegada = Integer.parseInt(LlegadaTextField.getText());
                int prioridad = Integer.parseInt(PrioridadTextField.getText());
                proceso = new Proceso(nombre, duracion, llegada, prioridad);
                listaProcesos.add(proceso);
                // Muestra el nuevo proceso en el JTextArea
                ProcesosTextArea.append(proceso.toString() + "\n");
                // Limpiar los campos de texto después de agregar el proceso
                NombreTextField.setText("");
                DuracionTextField.setText("");
                LlegadaTextField.setText("");
                PrioridadTextField.setText("");
            }
        });
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public Proceso getProceso() {
        return proceso;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}