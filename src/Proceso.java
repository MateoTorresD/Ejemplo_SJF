import java.util.*;

class Proceso {
    String nombre;
    int tiempoRafaga;
    int tiempoLlegada;
    int tiempoEspera;
    int tiempoFinal;

    public Proceso(String nombre, int tiempoRafaga, int tiempoLlegada) {
        this.nombre = nombre;
        this.tiempoRafaga = tiempoRafaga;
        this.tiempoLlegada = tiempoLlegada;
    }
}