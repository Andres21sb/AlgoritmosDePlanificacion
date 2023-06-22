package am.project.logic;

import am.project.presentation.DiagramaGanttRR;

import java.util.*;

public class RoundRobin {
    private List<Proceso> listaProcesos;
    private int quantum;

    public RoundRobin(List<Proceso> listaProcesos, int quantum) {
        this.listaProcesos = listaProcesos;
        this.quantum = quantum;
    }

    public void algoritmoRoundRobin() {
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

            

        }

        //new DiagramaGanttRR();
    }
}