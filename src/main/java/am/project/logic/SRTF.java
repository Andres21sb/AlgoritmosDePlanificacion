package am.project.logic;

import am.project.presentation.DiagramaGantt;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SRTF {
    private List<Proceso> listaProcesos;
    private Lock lock;

    public SRTF(List<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
        lock = new ReentrantLock();
    }

    public void algoritmoSRTF() {
        // Ordenar por tiempo de llegada y duración del proceso
        listaProcesos.sort(new Comparator<Proceso>() {
            @Override
            public int compare(Proceso p1, Proceso p2) {
                if (p1.getLlegada() == p2.getLlegada()) {
                    return Integer.compare(p1.getDuracion(), p2.getDuracion());
                } else {
                    return Integer.compare(p1.getLlegada(), p2.getLlegada());
                }
            }
        });

        int currentTime = 0;
        while (!listaProcesos.isEmpty()) {
            Proceso proceso = listaProcesos.get(0);
            int index = 0;
            for (int i = 1; i < listaProcesos.size(); i++) {
                Proceso p = listaProcesos.get(i);
                if (p.getLlegada() <= currentTime && p.getDuracion() < proceso.getDuracion()) {
                    proceso = p;
                    index = i;
                }
            }

            listaProcesos.remove(index);

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

