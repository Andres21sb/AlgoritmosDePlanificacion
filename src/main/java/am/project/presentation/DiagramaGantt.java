package am.project.presentation;

import am.project.logic.Proceso;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class DiagramaGantt extends JFrame {

    private List<Proceso> colaDeEjecucionProcesos;
    private int tiempoTotal;

    public DiagramaGantt(List<Proceso> colaDeEjecucionProcesos) {
        this.colaDeEjecucionProcesos = colaDeEjecucionProcesos;
        this.tiempoTotal = calcularTiempoTotal();

        setTitle("Diagrama de Gantt");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, tiempoTotal)); // 2 filas: una para etiquetas de tiempo y otra para barras de proceso

        // Agregar las etiquetas de tiempo en la primera fila
        add(new JLabel("Tiempo"));
        for (int i = 0; i < tiempoTotal; i++) {
            add(new JLabel("      "+String.valueOf(i )));
        }

        // Agregar las barras de proceso en la segunda fila
        add(new JLabel("Proceso"));
        for (int i = 0; i < tiempoTotal; i++) {
            JPanel panel = new JPanel();
            Proceso proceso = getProcesoEnTiempo(i);
            if (proceso != null) {
                if(proceso.getColor() == Color.white){
                    panel.setBackground(Color.green);
                }
                else{
                    panel.setBackground(proceso.getColor());
                }
                Border borde = new LineBorder(Color.black, 2);
                panel.setBorder(borde);
                JLabel label = new JLabel(proceso.getNombre());
                label.setForeground(Color.WHITE);
                panel.add(label);
            } else {
                panel.setBackground(Color.WHITE);
            }
            add(panel);
        }

        add(new JLabel("Espera"));
        for(Proceso proceso: colaDeEjecucionProcesos){
            JPanel panel = new JPanel();
            JLabel label = new JLabel(proceso.getNombre() + " -> "+ String.valueOf(proceso.espera()));
            Border borde = new LineBorder(Color.black, 2);
            panel.setBorder(borde);
            label.setForeground(proceso.getColor().darker());
            panel.add(label);
            add(panel);
        }
        for(int i = 0; i < ((tiempoTotal-colaDeEjecucionProcesos.size())); i++){
            add(new JLabel(""));
        }

        add(new JLabel("Promedio"));
        try{
            JPanel panel = new JPanel();
            JLabel label = new JLabel(this.getTiempoEsperaMedio(colaDeEjecucionProcesos));
            Border borde = new LineBorder(Color.black, 2);
            panel.setBorder(borde);
            panel.add(label);
            add(panel);
        }catch (Exception ex ){
            JOptionPane.showMessageDialog(null, "Se produjo un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        for(int i = 0; i < (tiempoTotal-1); i++){
            add(new JLabel(""));
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);



    }

    private Proceso getProcesoEnTiempo(int tiempo) {
        for (Proceso proceso : colaDeEjecucionProcesos) {
            if (proceso.isInTime(tiempo)) {
                return proceso;
            }
        }
        return null;
    }

    private int calcularTiempoTotal() {
        int tiempoMaximo = 0;
        for (Proceso proceso : colaDeEjecucionProcesos) {
                tiempoMaximo = proceso.getTiempoFinalizacion()+2;
        }
        return tiempoMaximo;
    }

    private String getTiempoEsperaMedio(List<Proceso> procesos) {
        if (procesos.isEmpty()) {
            throw new IllegalArgumentException("La lista de procesos no puede estar vacía");
        }

        double esperaPromedio = 0;
        for (Proceso proceso : procesos) {
            esperaPromedio += proceso.espera();
        }
        esperaPromedio = esperaPromedio / procesos.size();
        DecimalFormat formato = new DecimalFormat("#.#");
        String numeroFormateado = formato.format(esperaPromedio);

        return numeroFormateado;
    }

}


