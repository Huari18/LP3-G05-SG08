package flow;


import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;

public class MarcoFlowLayout extends JFrame {

 private final JButton botonJButtonIzquierda; // botón para alinear a la izquierda
 private final JButton botonJButtonCentro;    // botón para alinear al centro
 private final JButton botonJButtonDerecha;   // botón para alinear a la derecha

 private final FlowLayout esquema;            // administrador de diseño
 private final Container contenedor;          // contenedor del JFrame

 // Constructor
 public MarcoFlowLayout() {

     super("FLowLayout: Sammir, Aaron, Franco");

     esquema = new FlowLayout();
     contenedor = getContentPane(); 
     setLayout(esquema);
     
     

     // ---------- BOTÓN IZQUIERDA ----------
     botonJButtonIzquierda = new JButton("Izquierda");
     add(botonJButtonIzquierda);

     botonJButtonIzquierda.addActionListener(
         new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evento) {
                 esquema.setAlignment(FlowLayout.LEFT);
                 esquema.layoutContainer(contenedor);
             }
         }
     );
     
     

     // ---------- BOTÓN CENTRO ----------
     botonJButtonCentro = new JButton("Centro");
     add(botonJButtonCentro);

     botonJButtonCentro.addActionListener(
         new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evento) {
                 esquema.setAlignment(FlowLayout.CENTER);
                 esquema.layoutContainer(contenedor);
             }
         }
     );
     
     

     // ---------- BOTÓN DERECHA ----------
     botonJButtonDerecha = new JButton("Derecha");
     add(botonJButtonDerecha);

     botonJButtonDerecha.addActionListener(
         new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evento) {
                 esquema.setAlignment(FlowLayout.RIGHT);
                 esquema.layoutContainer(contenedor);
             }
         }
     );
 }
}

