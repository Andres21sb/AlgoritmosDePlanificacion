package am.project.logic;

import am.project.presentation.DiagramaGantt;
import am.project.presentation.DiagramaGanttSRTF;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
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

/*    public void algoritmoSRTF() {
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
    }*/


    public void algoritmoSRTF() {
        //declarar listas a usar
        List<Proceso> colaEspera = new ArrayList<>();
        List<Proceso> colaFinal = new ArrayList<>();
        //declarar flag del ciclo de tiempo
        Boolean flag = true;
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



        //declarar contador de tiempo
        int currentTime = 0;
        //iniciar ciclo de tiempo
        while(flag){
            //iterar a los procesos
            for(Proceso proceso: listaProcesos){
                //validar si entra a espera
                if(proceso.getLlegada()==currentTime){
                    //entra a la cola de espera
                    colaEspera.add(proceso);
                }
            }

            //limpiar lista de espera
            if (!colaEspera.isEmpty()) {
                Iterator<Proceso> iterator = colaEspera.iterator();
                while (iterator.hasNext()) {
                    Proceso proceso = iterator.next();
                    if (proceso.getTiempoRestante() == 0) {
                        proceso.setTiempoFinalizacion(currentTime-1);
                        iterator.remove(); // Eliminar utilizando el iterador
                    }
                }
            }

            //Incrementar contador de tiempo espera
            if (!colaEspera.isEmpty()) {
                for(Proceso proceso: colaEspera){
                    proceso.setTiempoEspera(proceso.getTiempoEspera()+1);
                }
            }

            //validar si hay procesos en espera
            if(!colaEspera.isEmpty()){
                //ordenar por duracion
                colaEspera.sort(Comparator.comparingInt(Proceso::getDuracion));
                //ejecutar el primero de la cola
                colaFinal.add(colaEspera.get(0));
                //incluir el segundo en la lista
                colaEspera.get(0).getArraySegundosEnEjecucion().add(currentTime);
                // Ejecutar el método run del proceso utilizando el Lock
                lock.lock();
                try {
                    colaEspera.get(0).run();
                } finally {
                    lock.unlock();
                }
                //disminuir el tiempo restante
                colaEspera.get(0).setTiempoRestante(colaEspera.get(0).getTiempoRestante()-1);
                //disminuir en 1 el tiempo de espera si es mayor a 0
                if(colaEspera.get(0).getTiempoEspera()>0)
                    colaEspera.get(0).setTiempoEspera(colaEspera.get(0).getTiempoEspera()-1);
            }

            if(currentTime>=this.tiempoMax()){
                flag=false;
            }
            currentTime++;
        }
        new DiagramaGanttSRTF(colaFinal,listaProcesos);
    }

    private int tiempoMax(){
        int max =0;
        int minLlegada= listaProcesos.get(0).getLlegada();
        for(Proceso proceso: listaProcesos){
            if(proceso.getLlegada()<minLlegada){
                minLlegada=proceso.getLlegada();
            }
            //sumar las duraciones
            max +=proceso.getDuracion();
        }
        //sumar la llegada minima
        max+=minLlegada;

        return max;
    }
}

