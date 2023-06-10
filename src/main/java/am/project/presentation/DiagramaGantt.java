package am.project.presentation;

import am.project.logic.Proceso;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DiagramaGantt extends JFrame {

    private List<Proceso> colaDeEjecucionProcesos;
    private int tiempoTotal;

    public DiagramaGantt(List<Proceso> colaDeEjecucionProcesos) {
        this.colaDeEjecucionProcesos = colaDeEjecucionProcesos;
        this.tiempoTotal = calcularTiempoTotal();

        setTitle("Diagrama de Gantt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, tiempoTotal)); // 2 filas: una para etiquetas de tiempo y otra para barras de proceso

        // Agregar las etiquetas de tiempo en la primera fila
        add(new JLabel("Tiempo"));
        for (int i = 0; i < tiempoTotal; i++) {
            add(new JLabel(String.valueOf(i + 1)));
        }

        // Agregar las barras de proceso en la segunda fila
        add(new JLabel("Proceso"));
        for (int i = 0; i < tiempoTotal; i++) {
            JPanel panel = new JPanel();
            Proceso proceso = getProcesoEnTiempo(i);
            if (proceso != null) {
                panel.setBackground(Color.GREEN);
                panel.setToolTipText(proceso.getNombre());
            } else {
                panel.setBackground(Color.WHITE);
            }
            add(panel);
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Proceso getProcesoEnTiempo(int tiempo) {
        for (Proceso proceso : colaDeEjecucionProcesos) {
            if (tiempo >= proceso.getLlegada() && tiempo < (proceso.getLlegada() + proceso.getDuracion())) {
                return proceso;
            }
        }
        return null;
    }

    private int calcularTiempoTotal() {
        int tiempoMaximo = 0;
        for (Proceso proceso : colaDeEjecucionProcesos) {
            tiempoMaximo += proceso.getDuracion();
        }
        return tiempoMaximo;
    }

}


