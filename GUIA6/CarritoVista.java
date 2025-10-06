package GUIA6;

import java.util.List;
import java.util.Scanner;

public class CarritoVista {
    private Scanner scanner;

    public CarritoVista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n--- MENÚ DEL CARRITO DE COMPRAS ---");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Ver Carrito");
        System.out.println("3. Eliminar Producto");
        System.out.println("4. Aplicar Descuento y Calcular Envío");
        System.out.println("5. Realizar Compra");
        System.out.println("6. Ver Historial de Compras");
        System.out.println("7. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    public String solicitarNombreProducto() {
        System.out.print("Nombre del producto: ");
        return scanner.nextLine();
    }

    public double solicitarPrecioProducto() {
        System.out.print("Precio del producto: ");
        return Double.parseDouble(scanner.nextLine());
    }

    public double solicitarDescuento() {
        System.out.print("Porcentaje de descuento: ");
        return Double.parseDouble(scanner.nextLine());
    }

    public void mostrarProductos(List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el carrito.");
        } else {
            System.out.println("\nProductos:");
            for (Producto p : productos) {
                System.out.println(" - " + p);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
