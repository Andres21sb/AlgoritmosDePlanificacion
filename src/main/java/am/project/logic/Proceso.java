package am.project.logic;

import java.awt.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.awt.Color;

public class Proceso implements Runnable, Cloneable{
    private String nombre;
    private int llegada;
    private int duracion;
    private int duracionInicial;
    private int prioridad;

    private boolean ejecucion;
    private int tiempoInicio;
    private int tiempoFinalizacion;
    private int tiempoRestante;

    private List<Integer> arraySegundosEnEjecucion;

    private Color color;

    private int tiempoEspera = 0;

    public Proceso() {
    }

    public Proceso(String nombre, int llegada, int duracion, int prioridad, boolean ejecucion) {
        this.nombre = nombre;
        this.llegada = llegada;
        this.duracion = duracion;
        this.duracionInicial=duracion;
        this.prioridad = prioridad;
        this.ejecucion = ejecucion;
        this.tiempoRestante = duracion;
        Random random = new Random();
        color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        this.arraySegundosEnEjecucion = new ArrayList<>();
    }

    public int getDuracionInicial() {
        return duracionInicial;
    }

    public void setDuracionInicial(int duracionInicial) {
        this.duracionInicial = duracionInicial;
    }

    public List<Integer> getArraySegundosEnEjecucion() {
        return arraySegundosEnEjecucion;
    }

    public void setArraySegundosEnEjecucion(List<Integer> arraySegundosEnEjecucion) {
        this.arraySegundosEnEjecucion = arraySegundosEnEjecucion;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
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
            while (tiempoRestante > 0) {
                Thread.sleep(1); // Simulación de la duración del proceso
                tiempoRestante--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Proceso " + nombre + " finalizado");
    }

    public void suspender() {
        ejecucion = false;
    }

    public void reanudar() {
        ejecucion = true;
    }

    public void ejecutar() {
        // No se necesita implementar en este caso ya que el tiempo restante se reduce en el método run()
    }

    public void finalizar() {
        // No se necesita implementar en este caso ya que el proceso se elimina de la lista en el algoritmo principal
    }

    public Boolean isInTime(int time) {
        return time >= tiempoInicio && time < tiempoFinalizacion;
    }

    public int espera(){
        int espera = 0;
        espera = (this.tiempoInicio-this.llegada);
        return espera;
    }

    // Override clone method
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // call to Object.clone()
    }
}