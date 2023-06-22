package am.project.logic;

import am.project.presentation.DiagramaGantt;
import am.project.presentation.DiagramaGanttRR;
import am.project.presentation.DiagramaGanttSRTF;

import java.util.*;

public class RoundRobin {
    private List<Proceso> listaProcesos;
    private int quantum;

    public RoundRobin(List<Proceso> listaProcesos, int quantum) {
        this.listaProcesos = listaProcesos;
        this.quantum = quantum;
    }

    public void algoritmoRoundRobin() {
        List<Proceso> listaProcesosFinal = new ArrayList<>();

        int tiempoFinalizacion = 0;

        Collections.sort(listaProcesos, new Comparator<Proceso>() {
            @Override
            public int compare(Proceso proceso1, Proceso proceso2) {
                // Comparar los procesos por tiempo de llegada
                return Integer.compare(proceso1.getLlegada(), proceso2.getLlegada());
            }
        });

        Queue<Proceso> colaProcesos = new LinkedList<>(listaProcesos);

        while (!colaProcesos.isEmpty()) {
            Proceso proceso = colaProcesos.poll();

            for (int i = 0; i < quantum; i++) {
                proceso.setDuracion(proceso.getDuracion() - 1);

                proceso.getArraySegundosEnEjecucion().add(tiempoFinalizacion);

                listaProcesosFinal.add(proceso);
                if (proceso.getDuracion() == 0) {
                    proceso.setTiempoFinalizacion(tiempoFinalizacion);
                    tiempoFinalizacion++;
                    break;
                }
                tiempoFinalizacion++;
            }

            if (proceso.getDuracion() > 0) {
                colaProcesos.add(proceso);
            }

        }

        new DiagramaGanttRR(listaProcesosFinal,listaProcesos);
    }

    public void algoritmoRoundRobinPrioridad() {
        List<Proceso> listaProcesosFinal = new ArrayList<>();

        int tiempoFinalizacion = 0;

        Collections.sort(listaProcesos, new Comparator<Proceso>() {
            @Override
            public int compare(Proceso proceso1, Proceso proceso2) {
                // Comparar los procesos por tiempo de llegada
                return Integer.compare(proceso1.getPrioridad(), proceso2.getPrioridad());
            }
        });

        Queue<Proceso> colaProcesos = new LinkedList<>(listaProcesos);

        while (!colaProcesos.isEmpty()) {
            Proceso proceso = colaProcesos.poll();

            for (int i = 0; i < quantum; i++) {
                proceso.setDuracion(proceso.getDuracion() - 1);

                proceso.getArraySegundosEnEjecucion().add(tiempoFinalizacion);

                listaProcesosFinal.add(proceso);
                if (proceso.getDuracion() == 0) {
                    proceso.setTiempoFinalizacion(tiempoFinalizacion);
                    tiempoFinalizacion++;
                    break;
                }
                tiempoFinalizacion++;
            }

            if (proceso.getDuracion() > 0) {
                colaProcesos.add(proceso);
            }

        }

        new DiagramaGanttRR(listaProcesosFinal,listaProcesos);
    }

}