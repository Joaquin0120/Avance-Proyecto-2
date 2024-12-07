import java.util.LinkedList;
import java.util.Queue;

public class ColaEnvios {
    private Queue<Paquete> cola;

    public ColaEnvios() {
        this.cola = new LinkedList<>();
    }

    public void agregarPaquete(Paquete paquete) {
        cola.offer(paquete);
    }

    public Paquete procesarPaquete() {
        return cola.poll(); // Elimina y devuelve el primer paquete
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public int obtenerTamano() {
        return cola.size();
    }
}