package am.project.presentation;
import am.project.logic.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


//import am.project.logic.RoundRobin;
//import am.project.logic.SJF;
//import am.project.logic.SRTF;
//import am.project.logic.FCFS;


public class View{
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
    private JButton prioridadButton;
    private JButton llegadaButton;
    private JPanel buttonsRR;
    private Proceso proceso;

    private List<Proceso> listaProcesos;


    public View() {
        listaProcesos = new ArrayList<>();
        //datos quemados
        Proceso proceso1 = new Proceso("P1", 0, 7, 6,false);
        Proceso proceso2 = new Proceso("P2", 2, 4, 7,false);
        Proceso proceso3 = new Proceso("P3", 4, 1, 8,false);
        Proceso proceso4 = new Proceso("P4", 5, 4, 9,false);
       // Proceso proceso5 = new Proceso("P5", 9, 3, 10,false);

        //Proceso proceso5 = new Proceso("Pepe", 3, 4, 5);
        //Proceso proceso6 = new Proceso("Pablo", 3, 5, 5);

        listaProcesos = new ArrayList<>();
        listaProcesos.add(proceso1);
        listaProcesos.add(proceso2);
        listaProcesos.add(proceso3);
        listaProcesos.add(proceso4);
       // listaProcesos.add(proceso5);
        //listaProcesos.add(proceso5);
        //listaProcesos.add(proceso6);

        /*listaProcesos.add(proceso5);
        listaProcesos.add(proceso6);*/
        AgregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = NombreTextField.getText();
                int duracion = Integer.parseInt(DuracionTextField.getText());
                int llegada = Integer.parseInt(LlegadaTextField.getText());
                int prioridad = Integer.parseInt(PrioridadTextField.getText());
                proceso = new Proceso(nombre, llegada,  duracion, prioridad,false);
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
              //getButtonEnviar().setVisible(true);
              getButtonsRR().setVisible(true);

                // Crear una instancia de la clase roundRobin
                //RoundRobin roundRobin = new RoundRobin();

                // Pasar la lista de procesos a la clase roundRobin
                //roundRobin.procesoRR(listaProcesos);
            }
        });

        ButtonFCFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la clase FCFS
                //FCFS fcfs = new FCFS();

                // Pasar la lista de procesos a la clase FCFS
                //fcfs.procesoFCFS(listaProcesos);

                List<Proceso> listaProcesosCopia = new ArrayList<>();
                for (Proceso p : listaProcesos) {
                    try {
                        listaProcesosCopia.add((Proceso) p.clone());
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                new FCFS(listaProcesosCopia).algoritmoFCFS();
            }
        });

        buttonSJF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la clase SRTF
                //SRTF srtf = new SRTF();

                // Pasar la lista de procesos a la clase SRTF
                //srtf.procesoSRTF(listaProcesos);
                List<Proceso> listaProcesosCopia = new ArrayList<>();
                for (Proceso p : listaProcesos) {
                    try {
                        listaProcesosCopia.add((Proceso) p.clone());
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                new SJF(listaProcesosCopia).algoritmoSJF();
            }
        });

        buttonSRTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Proceso> listaProcesosCopia = new ArrayList<>();
                for (Proceso p : listaProcesos) {
                    try {
                        listaProcesosCopia.add((Proceso) p.clone());
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                new SRTF(listaProcesosCopia).algoritmoSRTF();
            }
        });

       /* buttonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantum = Integer.parseInt(textFieldQuantum.getText());
                new RoundRobin(listaProcesos,quantum).algoritmoRoundRobin();
            }
        });*/


        prioridadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Proceso> listaProcesosCopia = new ArrayList<>();
                    for (Proceso p : listaProcesos) {
                        try {
                            listaProcesosCopia.add((Proceso) p.clone());
                        } catch (CloneNotSupportedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    int quantum = Integer.parseInt(textFieldQuantum.getText());
                    new RoundRobin(listaProcesosCopia,quantum).algoritmoRoundRobinPrioridad();
                }
        });
        llegadaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Proceso> listaProcesosCopia = new ArrayList<>();
                for (Proceso p : listaProcesos) {
                    try {
                        listaProcesosCopia.add((Proceso) p.clone());
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                int quantum = Integer.parseInt(textFieldQuantum.getText());
                new RoundRobin(listaProcesosCopia,quantum).algoritmoRoundRobin();
            }
        });
    }

    public JPanel getButtonsRR() {
        return buttonsRR;
    }

    public void setButtonsRR(JPanel buttonsRR) {
        this.buttonsRR = buttonsRR;
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