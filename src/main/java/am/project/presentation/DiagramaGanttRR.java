package am.project.presentation;

import am.project.logic.Proceso;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class DiagramaGanttRR extends JFrame {
    private List<Proceso> colaDeEjecucionProcesos;
    private int tiempoTotal;

    public DiagramaGanttRR(List<Proceso> colaDeEjecucionProcesos,List<Proceso> listaProcesos) {
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
                label.setToolTipText(proceso.toString());
                label.setForeground(Color.WHITE);
                panel.add(label);
            } else {
                panel.setBackground(Color.WHITE);
            }
            add(panel);
        }

        add(new JLabel("Espera"));
        for(Proceso proceso: listaProcesos){
            JPanel panel = new JPanel();
            JLabel label = new JLabel(proceso.getNombre() + " -> "+ String.valueOf(proceso.getTiempoEspera()));
            Border borde = new LineBorder(Color.black, 2);
            panel.setBorder(borde);
            label.setToolTipText(proceso.toString());
            label.setForeground(proceso.getColor().darker());
            panel.add(label);
            add(panel);
        }
        for(int i = 0; i < ((tiempoTotal-listaProcesos.size())); i++){
            add(new JLabel(""));
        }

        add(new JLabel("Promedio"));
        try{
            JPanel panel = new JPanel();
            JLabel label = new JLabel(this.getTiempoEsperaMedio(listaProcesos));
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
            if (proceso.getArraySegundosEnEjecucion().contains(tiempo)) {
                return proceso;
            }
        }
        return null;
    }

    private int calcularTiempoTotal() {
        int tiempoMaximo = colaDeEjecucionProcesos.get(0).getTiempoFinalizacion()+3;
        for (Proceso proceso : colaDeEjecucionProcesos) {
            if(proceso.getTiempoFinalizacion()>tiempoMaximo) {
                tiempoMaximo = proceso.getTiempoFinalizacion() +2;
            }
        }
        return tiempoMaximo;
    }


    private String getTiempoEsperaMedio(List<Proceso> procesos) {
        if (procesos.isEmpty()) {
            throw new IllegalArgumentException("La lista de procesos no puede estar vacía");
        }

        double esperaPromedio = 0;
        for (Proceso proceso : procesos) {
            esperaPromedio += proceso.getTiempoEspera();
        }
        esperaPromedio = esperaPromedio / procesos.size();
        DecimalFormat formato = new DecimalFormat("#.#");
        String numeroFormateado = formato.format(esperaPromedio);

        return numeroFormateado;
    }
}
