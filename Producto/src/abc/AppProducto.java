package abc;

import javax.swing.*;
import java.awt.*;
public class AppProducto {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Gestión de Producto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 350);
        frame.setLayout(new FlowLayout());

        //Modelo
        Producto producto = new Producto();

        // Componentes
        JTextField txtNombre = new JTextField(15);
        JTextField txtPrecio = new JTextField(15);
        JTextField txtStock = new JTextField(15);
        JTextField txtCategoria = new JTextField(15);

        JButton btnActualizar = new JButton("Actualizar Producto");

        JLabel lblResultado = new JLabel("Info del producto aparecerá aquí");

        // Acción del botón
        btnActualizar.addActionListener(e -> {
            try {
                // Binding manual Vista → Modelo
                producto.setNombre(txtNombre.getText());
                producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
                producto.setCantidadStock(Integer.parseInt(txtStock.getText()));
                producto.setCategoria(txtCategoria.getText());

                // Mostrar modelo en la vista
                lblResultado.setText(producto.toString());

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Error: Precio debe ser número decimal y Stock debe ser entero.",
                        "Datos inválidos",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Agregar componentes
        frame.add(new JLabel("Nombre:"));
        frame.add(txtNombre);

        frame.add(new JLabel("Precio:"));
        frame.add(txtPrecio);

        frame.add(new JLabel("Cantidad Stock:"));
        frame.add(txtStock);

        frame.add(new JLabel("Categoría:"));
        frame.add(txtCategoria);

        frame.add(btnActualizar);
        frame.add(lblResultado);

        frame.setVisible(true);
    }
}
