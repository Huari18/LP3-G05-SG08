package abcd;

import javax.swing.*;
import java.awt.*;

public class AppTemperatura extends JFrame {

    private JTextField[] camposTemp;   // 7 campos: lunes a domingo
    private GraficoLineas panelGrafico;

    public AppTemperatura() {
        setTitle("Temperaturas Semanales");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // === PANEL SUPERIOR (ENTRADAS) ===
        JPanel panelEntrada = new JPanel(new GridLayout(2, 7));
        String[] dias = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};

        camposTemp = new JTextField[7];

        // Etiquetas
        for (String d : dias) {
            panelEntrada.add(new JLabel(d, SwingConstants.CENTER));
        }

        // Campos de texto
        for (int i = 0; i < 7; i++) {
            camposTemp[i] = new JTextField();
            panelEntrada.add(camposTemp[i]);
        }

        add(panelEntrada, BorderLayout.NORTH);

        // === PANEL CENTRAL (GRÁFICO) ===
        panelGrafico = new GraficoLineas();
        add(panelGrafico, BorderLayout.CENTER);

        // === BOTÓN ===
        JButton btnMostrar = new JButton("Mostrar Gráfico");
        btnMostrar.addActionListener(e -> actualizarGrafico());
        add(btnMostrar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void actualizarGrafico() {
        int[] temperaturas = new int[7];

        try {
            for (int i = 0; i < 7; i++) {
                temperaturas[i] = Integer.parseInt(camposTemp[i].getText());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa solo números válidos.");
            return;
        }

        panelGrafico.setTemperaturas(temperaturas);
        panelGrafico.repaint();
    }

    public static void main(String[] args) {
        new AppTemperatura();
    }
}

