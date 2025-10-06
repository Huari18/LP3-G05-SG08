package GUIA6.Ejercicio_1;
import java.util.List;
import java.util.Scanner;

public class CarritoVista {
    private Scanner scanner;

    public CarritoVista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE CARRITO DE COMPRAS ===");
        System.out.println("1. Agregar producto al catálogo");
        System.out.println("2. Listar productos disponibles");
        System.out.println("3. Agregar producto al carrito");
        System.out.println("4. Ver carrito");
        System.out.println("5. Eliminar producto del carrito");
        System.out.println("6. Aplicar descuento y calcular envío");
        System.out.println("7. Realizar compra");
        System.out.println("8. Ver historial de compras");
        System.out.println("9. Salir");
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

    public void mostrarProductos(String titulo, List<Producto> lista) {
        System.out.println("\n--- " + titulo + " ---");
        if (lista.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            for (Producto p : lista) {
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
