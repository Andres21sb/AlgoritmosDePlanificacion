package am.project.logic;

import am.project.presentation.DiagramaGantt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FCFS {

    //atributos
    private List<Proceso> listaProcesos;

    Lock lock ;


    public FCFS(List<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
        lock = new ReentrantLock();
    }

    public void algoritmoFCFS() {

        //ordenar por llegada
        listaProcesos.sort(Comparator.comparingInt(Proceso::getLlegada));

        int currentTime = 0;
        for (Proceso proceso: listaProcesos) {
            int startTime = Math.max(currentTime, proceso.getLlegada());
            int finishTime = startTime + proceso.getDuracion();
            proceso.setTiempoInicio(startTime);
            proceso.setTiempoFinalizacion(finishTime);
            currentTime = finishTime;

            // Ejecutar el método run del proceso utilizando el Lock
            lock.lock();
            try {
                proceso.run();
            } finally {
                lock.unlock();
            }

        }
        new DiagramaGantt(listaProcesos);
    }

}
