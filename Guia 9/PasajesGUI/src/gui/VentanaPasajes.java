package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaPasajes extends JFrame {

    private JTextField tfNombre;
    private JTextField tfDocumento;
    private JTextField tfFecha;

    private JComboBox<String> cbOrigen;
    private JComboBox<String> cbDestino;

    private JCheckBox cbAudifonos;
    private JCheckBox cbManta;
    private JCheckBox cbRevistas;

    private JRadioButton rbPiso1;
    private JRadioButton rbPiso2;

    private JList<String> listCalidad;

    private JButton btnResumen;
    private JButton btnReiniciar;

    public VentanaPasajes() {

        super("Compra de Pasajes – Empresa de Transporte");

        setLayout(new BorderLayout());

        // =======================
        // PANEL PRINCIPAL 6x2
        // =======================
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(6, 2, 8, 8));

        // Nombre
        panelFormulario.add(new JLabel("Nombre:"));
        tfNombre = new JTextField();
        panelFormulario.add(tfNombre);

        // Documento
        panelFormulario.add(new JLabel("Documento (DNI):"));
        tfDocumento = new JTextField();
        panelFormulario.add(tfDocumento);

        // Fecha de viaje
        panelFormulario.add(new JLabel("Fecha de viaje:"));
        tfFecha = new JTextField();
        panelFormulario.add(tfFecha);

        // Origen
        panelFormulario.add(new JLabel("Origen:"));
        cbOrigen = new JComboBox<>(new String[]{"Lima", "Arequipa", "Cusco", "Puno", "Piura"});
        panelFormulario.add(cbOrigen);

        // Destino
        panelFormulario.add(new JLabel("Destino:"));
        cbDestino = new JComboBox<>(new String[]{"Lima", "Arequipa", "Cusco", "Puno", "Piura"});
        panelFormulario.add(cbDestino);

        // Calidad del servicio
        panelFormulario.add(new JLabel("Calidad del servicio:"));
        listCalidad = new JList<>(new String[]{"Económico", "Standard", "VIP"});
        listCalidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(listCalidad);
        panelFormulario.add(scroll);

        add(panelFormulario, BorderLayout.CENTER);

        // =======================
        // PANEL DERECHA (CHECKBOXES)
        // =======================
        JPanel panelServicios = new JPanel(new GridLayout(3, 1));
        panelServicios.setBorder(BorderFactory.createTitledBorder("Servicios opcionales"));

        cbAudifonos = new JCheckBox("Audífonos");
        cbManta = new JCheckBox("Manta");
        cbRevistas = new JCheckBox("Revistas");

        panelServicios.add(cbAudifonos);
        panelServicios.add(cbManta);
        panelServicios.add(cbRevistas);

        add(panelServicios, BorderLayout.EAST);

        // =======================
        // PANEL IZQUIERDA (RADIO BUTTONS)
        // =======================
        JPanel panelPiso = new JPanel(new GridLayout(2, 1));
        panelPiso.setBorder(BorderFactory.createTitledBorder("Piso del bus"));

        rbPiso1 = new JRadioButton("1er piso");
        rbPiso2 = new JRadioButton("2do piso");

        ButtonGroup grupoPiso = new ButtonGroup();
        grupoPiso.add(rbPiso1);
        grupoPiso.add(rbPiso2);

        panelPiso.add(rbPiso1);
        panelPiso.add(rbPiso2);

        add(panelPiso, BorderLayout.WEST);

        // =======================
        // PANEL SUR (BOTONES)
        // =======================
        JPanel panelBotones = new JPanel(new FlowLayout());

        btnResumen = new JButton("Mostrar Resumen");
        btnReiniciar = new JButton("Reiniciar Todo");

        panelBotones.add(btnResumen);
        panelBotones.add(btnReiniciar);

        add(panelBotones, BorderLayout.SOUTH);

        // =======================
        // EVENTO: BOTÓN RESUMEN
        // =======================
        btnResumen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = tfNombre.getText();
                String dni = tfDocumento.getText();
                String fecha = tfFecha.getText();
                String origen = cbOrigen.getSelectedItem().toString();
                String destino = cbDestino.getSelectedItem().toString();
                String calidad = listCalidad.getSelectedValue();

                String servicios = "";
                if (cbAudifonos.isSelected()) servicios += "Audífonos ";
                if (cbManta.isSelected()) servicios += "Manta ";
                if (cbRevistas.isSelected()) servicios += "Revistas ";

                String piso = rbPiso1.isSelected() ? "1er piso" : rbPiso2.isSelected() ? "2do piso" : "No seleccionado";

                JOptionPane.showMessageDialog(
                    null,
                    "=== RESUMEN DEL PASAJE ===\n\n" +
                    "Nombre: " + nombre + "\n" +
                    "DNI: " + dni + "\n" +
                    "Fecha de viaje: " + fecha + "\n" +
                    "Origen: " + origen + "\n" +
                    "Destino: " + destino + "\n" +
                    "Calidad: " + calidad + "\n" +
                    "Servicios: " + servicios + "\n" +
                    "Piso: " + piso,
                    "Resumen",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // =======================
        // EVENTO: BOTÓN REINICIAR
        // =======================
        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tfNombre.setText("");
                tfDocumento.setText("");
                tfFecha.setText("");

                cbOrigen.setSelectedIndex(0);
                cbDestino.setSelectedIndex(0);

                listCalidad.clearSelection();

                cbAudifonos.setSelected(false);
                cbManta.setSelected(false);
                cbRevistas.setSelected(false);

                rbPiso1.setSelected(false);
                rbPiso2.setSelected(false);
            }
        });

        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPasajes();
    }
}

