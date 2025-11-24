import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class ReproductorSonido extends JFrame {

    public ReproductorSonido() {
        setTitle("Reproductor de Efectos de Sonido");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3));

        // Botones
        JButton btnAplausos = new JButton("Aplausos");
        JButton btnCampana = new JButton("Campana");
        JButton btnExplosion = new JButton("Explosión");

        // Acciones
        btnAplausos.addActionListener(e -> reproducirSonido("aplausos.wav"));
        btnCampana.addActionListener(e -> reproducirSonido("campana.wav"));
        btnExplosion.addActionListener(e -> reproducirSonido("explosion.wav"));

        // Añadir al panel
        add(btnAplausos);
        add(btnCampana);
        add(btnExplosion);

        setVisible(true);
    }

    private void reproducirSonido(String ruta) {
        try {
            File archivo = new File(ruta);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al intentar reproducir el sonido: " + ruta);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ReproductorSonido();
    }
}
