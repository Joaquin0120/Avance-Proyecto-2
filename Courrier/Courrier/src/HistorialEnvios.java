import java.util.ArrayList;
import java.util.List;

public class HistorialEnvios {
    private List<Paquete> historial;

    public HistorialEnvios() {
        this.historial = new ArrayList<>();
    }

    public void agregarPaquete(Paquete paquete) {
        historial.add(paquete);
    }

    public List<Paquete> obtenerHistorial() {
        return historial;
    }
}
