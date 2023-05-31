package am.project.presentation;
import am.project.logic.Proceso;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


//import am.project.logic.RoundRobin;
//import am.project.logic.SJF;
//import am.project.logic.SRTF;
//import am.project.logic.FCFS;


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
    private JPanel panel4;
    private JPanel panel5;
    private JButton buttonRR;
    private JButton buttonSJF;
    private JButton buttonSRTF;
    private JButton ButtonFCFS;
    private JTextField textFieldQuantum;
    private JLabel JLabelQuantum;
    private JButton buttonEnviar;
    private JTextArea ProcesosTextArea;
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
        buttonRR.addActionListener(new ActionListener() {
            JLabel labelQuantum =new JLabel("Quantum");
            //labelQuantum.setText("Quantum");
            @Override
            public void actionPerformed(ActionEvent e) {
              getJLabelQuantum().setVisible(true);
              getTextFieldQuantum().setVisible(true);
              getButtonEnviar().setVisible(true);
                // Crear una instancia de la clase roundRobin
               // RoundRobin roundRobin = new RoundRobin();

                // Pasar la lista de procesos a la clase roundRobin
                //roundRobin.procesoRR(listaProcesos);
            }
        });

        ButtonFCFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la clase SJF
                //SJF sjf = new SJF();

                // Pasar la lista de procesos a la clase SJF
                //sjf.procesoFCFS(listaProcesos);
            }
        });

        buttonSJF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la clase SRTF
                //SRTF srtf = new SRTF();

                // Pasar la lista de procesos a la clase SRTF
                //srtf.procesoSRTF(listaProcesos);
            }
        });

        buttonSRTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la clase FCFS
                //FCFS fcfs = new FCFS();

                // Pasar la lista de procesos a la clase FCFS
                //fcfs.procesoSRTF(listaProcesos);
            }
        });



    }

    public JButton getButtonEnviar() {
        return buttonEnviar;
    }

    public void setButtonEnviar(JButton buttonEnviar) {
        this.buttonEnviar = buttonEnviar;
    }

    public JTextField getTextFieldQuantum() {
        return textFieldQuantum;
    }

    public void setTextFieldQuantum(JTextField textFieldQuantum) {
        this.textFieldQuantum = textFieldQuantum;
    }

    public JLabel getJLabelQuantum() {
        return JLabelQuantum;
    }

    public void setJLabelQuantum(JLabel JLabelQuantum) {
        this.JLabelQuantum = JLabelQuantum;
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