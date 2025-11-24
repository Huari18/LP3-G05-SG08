package abcd;

import javax.swing.*;
import java.awt.*;

public class GraficoLineas extends JPanel {

    private int[] temperaturas = {0,0,0,0,0,0,0};

    public void setTemperaturas(int[] t) {
        this.temperaturas = t;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Conversión a Graphics2D (para antialiasing)
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        int margen = 50;
        int espacio = (ancho - 2 * margen) / 6;  // 7 puntos → 6 espacios

        // Ejes
        g2.drawLine(margen, alto - margen, ancho - margen, alto - margen); // eje X
        g2.drawLine(margen, alto - margen, margen, margen);                // eje Y

        // Buscar máximo para escala
        int max = 1;
        for (int t : temperaturas) {
            if (t > max) max = t;
        }

        // Dibujar puntos y líneas
        int[] x = new int[7];
        int[] y = new int[7];

        for (int i = 0; i < 7; i++) {
            x[i] = margen + i * espacio;
            y[i] = alto - margen - (temperaturas[i] * (alto - 2 * margen) / max);
        }

        // Líneas conectadas
        g2.setColor(Color.BLUE);
        for (int i = 0; i < 6; i++) {
            g2.drawLine(x[i], y[i], x[i+1], y[i+1]);
        }

        // Puntos
        g2.setColor(Color.RED);
        for (int i = 0; i < 7; i++) {
            g2.fillOval(x[i] - 5, y[i] - 5, 10, 10);
        }

        // Etiquetas de los puntos
        String[] dias = {"Lun","Mar","Mié","Jue","Vie","Sáb","Dom"};
        g2.setColor(Color.BLACK);
        for (int i = 0; i < 7; i++) {
            g2.drawString(dias[i], x[i] - 10, alto - margen + 15);
            g2.drawString(temperaturas[i] + "°", x[i] - 10, y[i] - 10);
        }
    }
}

