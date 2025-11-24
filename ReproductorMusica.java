package abcdefg;

import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class ReproductorMusica extends JFrame {

    private Clip clip;
    private Long posicionPausa = 0L;
    private boolean cargado = false;

    public ReproductorMusica() {
        setTitle("Reproductor de Música");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton btnReproducir = new JButton("Reproducir");
        JButton btnPausar = new JButton("Pausar");
        JButton btnReanudar = new JButton("Reanudar");

        add(btnReproducir);
        add(btnPausar);
        add(btnReanudar);

        btnReproducir.addActionListener(e -> reproducir());
        btnPausar.addActionListener(e -> pausar());
        btnReanudar.addActionListener(e -> reanudar());

        setVisible(true);
    }

    private void cargarAudio() {
        try {
            File archivo = new File("musica.wav");  // Cambia el nombre si necesitas otro
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);

            clip = AudioSystem.getClip();
            clip.open(audioStream);

            cargado = true;

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el audio.");
            e.printStackTrace();
        }
    }

    private void reproducir() {
        if (!cargado) {
            cargarAudio();
        }
        clip.setFramePosition(0); // Desde el inicio
        clip.start();
    }

    private void pausar() {
        if (clip != null && clip.isRunning()) {
            posicionPausa = clip.getMicrosecondPosition();
            clip.stop();
        }
    }

    private void reanudar() {
        if (clip != null) {
            clip.setMicrosecondPosition(posicionPausa);
            clip.start();
        }
    }

    public static void main(String[] args) {
        new ReproductorMusica();
    }
}

