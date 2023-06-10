package am.project.logic;

import am.project.presentation.DiagramaGantt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Algoritmos {
    private List<Proceso> listaProcesos;
    private List<Proceso> colaDeEsperaProcesos;
    private List<Proceso> colaDeEjecucionProcesos;
    private Lock lock;

    public Algoritmos(List<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
        this.colaDeEsperaProcesos = new ArrayList<>();//aqui se almacenan los procesos que no están en ejecucion
        this.colaDeEjecucionProcesos = new ArrayList<>();//aqui se almacenan los procesos que se están ejecutando
        this.lock = new ReentrantLock();
    }



    public List<Proceso> ordenaFCFS(){
        //aquí se ordena la lista de ejecucion
        return colaDeEjecucionProcesos;
    }

    /*public void FCFS() {
        // Ordenar los procesos según el tiempo de llegada (FCFS)
        listaProcesos.sort((p1, p2) -> p1.getLlegada() - p2.getLlegada());

        // Crear un hilo para cada proceso y agregarlo a la cola de ejecución
        for (Proceso proceso : listaProcesos) {
            Thread hilo = new Thread(() -> {
                lock.lock(); // Adquirir el bloqueo antes de acceder a la zona crítica
                try {
                    proceso.run(); // Ejecutar el proceso en la zona crítica
                } finally {
                    lock.unlock(); // Liberar el bloqueo después de ejecutar el proceso
                }
            });
            colaDeEjecucionProcesos.add(proceso);
            hilo.start();
        }
    }*/
   /* public void FCFS() {
        // Ordenar los procesos según el tiempo de llegada (FCFS)
        listaProcesos.sort(Comparator.comparingInt(Proceso::getLlegada));

        // Crear un hilo para cada proceso y agregarlo a la cola de ejecución
        for (Proceso proceso : listaProcesos) {
            Thread hilo = new Thread(() -> {
                lock.lock(); // Adquirir el bloqueo antes de acceder a la zona crítica
                try {
                    proceso.run(); // Ejecutar el proceso en la zona crítica
                } finally {
                    lock.unlock(); // Liberar el bloqueo después de ejecutar el proceso
                }
            });
            lock.lock(); // Adquirir el bloqueo antes de modificar la cola de ejecución
            try {
                colaDeEjecucionProcesos.add(proceso);
            } finally {
                lock.unlock(); // Liberar el bloqueo después de modificar la cola de ejecución
            }
            hilo.start();
        }
    }*/

    public void FCFS() {
        // Ordenar los procesos según el tiempo de llegada (FCFS)
        listaProcesos.sort(Comparator.comparingInt(Proceso::getLlegada));

        lock.lock(); // Adquirir el bloqueo antes de modificar la cola de ejecución
        try {
            // Crear un hilo para cada proceso y agregarlo a la cola de ejecución
            for (Proceso proceso : listaProcesos) {
                Thread hilo = new Thread(() -> {
                    lock.lock(); // Adquirir el bloqueo antes de acceder a la zona crítica
                    try {
                        proceso.run(); // Ejecutar el proceso en la zona crítica
                    } finally {
                        lock.unlock(); // Liberar el bloqueo después de ejecutar el proceso
                    }
                });
                colaDeEjecucionProcesos.add(proceso);
                hilo.start();
            }
        } finally {
            lock.unlock(); // Liberar el bloqueo después de modificar la cola de ejecución
        }
        new DiagramaGantt(colaDeEjecucionProcesos);
    }


}
