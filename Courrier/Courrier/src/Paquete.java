public class Paquete {
    private String id;
    private String descripcion;
    private String peso;
    private String remitente;
    private String destinatario;

    // Constructor
    public Paquete(String id, String descripcion, String peso, String remitente, String destinatario) {
        this.id = id;
        this.descripcion = descripcion;
        this.peso = peso;
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPeso() {
        return peso;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Descripción: %s | Peso: %s | Remitente: %s | Destinatario: %s",
                id, descripcion, peso, remitente, destinatario);
    }
}
