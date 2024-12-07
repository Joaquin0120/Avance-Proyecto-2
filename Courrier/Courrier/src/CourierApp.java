import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourierApp {

    // Componentes de la interfaz
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel compraPanel;
    private JPanel registroPanel;
    private JTextField idField;
    private JTextField descripcionField;
    private JTextField pesoField;
    private JTextField remitenteField;
    private JTextField destinatarioField;
    private JButton agregarPaqueteButton;
    private JTextArea registroTextArea;

    public CourierApp() {
        // Crear el frame principal
        frame = new JFrame("Courier Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Crear el JTabbedPane
        tabbedPane = new JTabbedPane();

        // Crear las pestañas
        compraPanel = createCompraPanel();
        registroPanel = createRegistroPanel();

        tabbedPane.addTab("Compra", compraPanel);
        tabbedPane.addTab("Registro", registroPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
        agregarPaqueteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores ingresados por el usuario
                    String id = idField.getText().trim();
                    String descripcion = descripcionField.getText().trim();
                    String pesoStr = pesoField.getText().trim();
                    String remitente = remitenteField.getText().trim();
                    String destinatario = destinatarioField.getText().trim();

                    // Validar que los campos no estén vacíos
                    if (id.isEmpty() || descripcion.isEmpty() || pesoStr.isEmpty() || remitente.isEmpty() || destinatario.isEmpty()) {
                        throw new IllegalArgumentException("Todos los campos son obligatorios.");
                    }

                    // Validar el formato del peso
                    double pesoKg = convertirPesoAKilogramos(pesoStr); // Convertir peso a kilogramos

                    // Agregar el paquete al registro
                    String paqueteInfo = String.format(
                            "ID: %s, Descripción: %s, Peso: %.2f kg, Remitente: %s, Destinatario: %s\n",
                            id, descripcion, pesoKg, remitente, destinatario
                    );
                    registroTextArea.append(paqueteInfo);

                    // Limpiar los campos de texto después de registrar
                    idField.setText("");
                    descripcionField.setText("");
                    pesoField.setText("");
                    remitenteField.setText("");
                    destinatarioField.setText("");

                    // Confirmar al usuario que el paquete se registró
                    JOptionPane.showMessageDialog(frame, "Paquete registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    // Manejar errores de conversión
                    JOptionPane.showMessageDialog(frame, "Formato de peso inválido. Ejemplo válido: '220 g', '2.5 kg', '300 gramos'.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    // Manejar errores de validación
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    // Capturar cualquier otro error inesperado
                    JOptionPane.showMessageDialog(frame, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
    }

    private JPanel createCompraPanel() {
        // Crear el panel de compra
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Crear los campos de texto y etiquetas
        panel.add(new JLabel("ID del Paquete:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Descripción:"));
        descripcionField = new JTextField();
        panel.add(descripcionField);

        panel.add(new JLabel("Peso:"));
        pesoField = new JTextField();
        panel.add(pesoField);

        panel.add(new JLabel("Remitente:"));
        remitenteField = new JTextField();
        panel.add(remitenteField);

        panel.add(new JLabel("Destinatario:"));
        destinatarioField = new JTextField();
        panel.add(destinatarioField);

        // Botón para agregar el paquete
        agregarPaqueteButton = new JButton("Agregar Paquete");
        panel.add(agregarPaqueteButton);

        return panel;
    }

    private double convertirPesoAKilogramos(String pesoStr) throws NumberFormatException {
        pesoStr = pesoStr.toLowerCase().trim();

        // Expresión regular para validar y extraer el peso y la unidad
        String regex = "(\\d+(\\.\\d+)?)(\\s*(kg|kilogramos|g|gramos|mg|miligramos))";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(pesoStr);

        if (matcher.matches()) {
            double peso = Double.parseDouble(matcher.group(1)); // Captura el valor numérico
            String unidad = matcher.group(4); // Captura la unidad

            // Convertir a kilogramos según la unidad
            if (unidad.startsWith("kg") || unidad.startsWith("kilo")) {
                return peso; // Ya está en kilogramos
            } else if (unidad.startsWith("g") || unidad.startsWith("gramo")) {
                return peso / 1000; // Gramos a kilogramos
            } else if (unidad.startsWith("mg") || unidad.startsWith("mili")) {
                return peso / 1_000_000; // Miligramos a kilogramos
            } else {
                throw new NumberFormatException("Unidad desconocida en el peso.");
            }
        } else {
            throw new NumberFormatException("El peso debe incluir una cantidad y unidad. Ejemplo: '220 g', '2.5 kg'.");
        }
    }




    private JPanel createRegistroPanel() {
        // Crear el panel de registro
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear el JTextArea para mostrar los datos
        registroTextArea = new JTextArea();
        registroTextArea.setEditable(false);
        panel.add(new JScrollPane(registroTextArea), BorderLayout.CENTER);

        return panel;
    }





    public static void main(String[] args) {
        // Ejecutar la interfaz
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CourierApp();
            }
        });
    }
}
