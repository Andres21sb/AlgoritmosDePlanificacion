package am.project.logic;

public class Proceso implements Runnable{
    String nombre;
    int llegada;
    int duracion;
    int prioridad;
    boolean ejecucion;

    private int tiempoInicio;
    private int tiempoFinalizacion;

    int tiempoEspera = 0;
    public Proceso() {
    }
    public Proceso(String nombre, int llegada, int duracion, int prioridad, boolean ejecucion) {
        this.nombre = nombre;
        this.llegada = llegada;
        this.duracion = duracion;
        this.prioridad = prioridad;
        this.ejecucion = ejecucion;
    }

    public Proceso(String nombre, int duracion, int llegada, int prioridad) {
        this.nombre = nombre;
        this.llegada = llegada;
        this.duracion = duracion;
        this.prioridad = prioridad;
    }

    public int getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(int tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public int getTiempoFinalizacion() {
        return tiempoFinalizacion;
    }

    public void setTiempoFinalizacion(int tiempoFinalizacion) {
        this.tiempoFinalizacion = tiempoFinalizacion;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLlegada() {
        return llegada;
    }

    public void setLlegada(int llegada) {
        this.llegada = llegada;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(boolean ejecucion) {
        this.ejecucion = ejecucion;
    }

    @Override
    public String toString() {
        return "Proceso{" +
                "nombre='" + nombre + '\'' +
                ", llegada=" + llegada +
                ", duracion=" + duracion +
                ", prioridad=" + prioridad +
                ", ejecucion=" + ejecucion +
                ", tiempoEspera=" + tiempoEspera +
                '}';
    }


    @Override
    public void run() {
        // Lógica del proceso en su ejecución
        System.out.println("Proceso " + nombre + " en ejecución");
        try {
           // Thread.sleep(duracion); // Simulación de la duración del proceso
            Thread.sleep(1000); // Simulación de la duración del proceso

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Proceso " + nombre + " finalizado");
    }

    public Boolean isInTime(int time) {
        return time >= tiempoInicio && time < tiempoFinalizacion;
    }
}
