package am.project.logic;

import am.project.presentation.DiagramaGantt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FCFS {

    //atributos
    private List<Proceso> listaProcesos;


    public FCFS(List<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
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
        }
        new DiagramaGantt(listaProcesos);
    }

}
