package CheckBox;

//Fig. 12.17: MarcoCasillaVerificacion.java
//Botones JCheckBox y eventos de elementos.

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class MarcoCasillaVerificacion extends JFrame
{
 private JTextField campoTexto;        // muestra el texto con tipos de letra cambiantes
 private JCheckBox negritaJCheckBox;   // seleccionar/deseleccionar negrita
 private JCheckBox cursivaJCheckBox;   // seleccionar/deseleccionar cursiva

 // Constructor: agrega los JCheckBox al JFrame
 public MarcoCasillaVerificacion()
 {
     super("Prueba de JCheckBox");
     setLayout(new FlowLayout());

     // establece JTextField y su tipo de letra
     campoTexto = new JTextField("Observe como cambia el estilo de tipo de letra", 20);
     campoTexto.setFont(new Font("Serif", Font.PLAIN, 14));
     add(campoTexto);

     // crea los JCheckBox
     negritaJCheckBox = new JCheckBox("Negrita");
     cursivaJCheckBox = new JCheckBox("Cursiva");

     add(negritaJCheckBox);
     add(cursivaJCheckBox);

     // registra componentes de escucha
     ManejadorCheckBox manejador = new ManejadorCheckBox();
     negritaJCheckBox.addItemListener(manejador);
     cursivaJCheckBox.addItemListener(manejador);
 }

 // clase interna privada para manejo de eventos ItemListener
 private class ManejadorCheckBox implements ItemListener
 {
     @Override
     public void itemStateChanged(ItemEvent evento)
     {
         Font tipoletra = null;

         // determina qué casillas están seleccionadas y crea el Font adecuado
         if (negritaJCheckBox.isSelected() && cursivaJCheckBox.isSelected())
             tipoletra = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
         else if (negritaJCheckBox.isSelected())
             tipoletra = new Font("Serif", Font.BOLD, 14);
         else if (cursivaJCheckBox.isSelected())
             tipoletra = new Font("Serif", Font.ITALIC, 14);
         else
             tipoletra = new Font("Serif", Font.PLAIN, 14);

         campoTexto.setFont(tipoletra);
     }
 }
} // fin de la clase MarcoCasillaVerificacion
