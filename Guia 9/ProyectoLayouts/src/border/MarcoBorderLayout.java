package border;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;

public class MarcoBorderLayout extends JFrame implements ActionListener {

    private final JButton botones[]; 
    private static final String nombres[] = {
        "Ocultar Norte", 
        "Ocultar Sur",
        "Ocultar Este", 
        "Ocultar Oeste", 
        "Ocultar Centro"
    };

    private final BorderLayout esquema;

    // Constructor
    public MarcoBorderLayout() {
        super("BorderLayout - Integrantes: Sammir, Aaron, Franco");

        esquema = new BorderLayout(5, 5); 
        setLayout(esquema);
        botones = new JButton[nombres.length];

        // Crear botones y añadir listeners
        for (int i = 0; i < nombres.length; i++) {
            botones[i] = new JButton(nombres[i]);
            botones[i].addActionListener(this);
        }

        // Agregar cada botón a su región
        add(botones[0], BorderLayout.NORTH);
        add(botones[1], BorderLayout.SOUTH);
        add(botones[2], BorderLayout.EAST);
        add(botones[3], BorderLayout.WEST);
        add(botones[4], BorderLayout.CENTER);
    }

    // Manejo de eventos
    @Override
    public void actionPerformed(ActionEvent e) {

        for (JButton boton : botones) {
            if (e.getSource() == boton)
                boton.setVisible(false);  // ocultar el que se presiona
            else
                boton.setVisible(true);   // mostrar los demás
        }

        esquema.layoutContainer(getContentPane()); 
    }
}

