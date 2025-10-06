package GUIA6.Ejercicio_2;

import java.util.List;
import java.util.Scanner;

public class InventarioView {
    private Scanner scanner;

    public InventarioView() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE INVENTARIO ===");
        System.out.println("1. Agregar Ítem");
        System.out.println("2. Eliminar Ítem");
        System.out.println("3. Ver Inventario");
        System.out.println("4. Buscar Ítem");
        System.out.println("5. Usar Ítem");
        System.out.println("6. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    public String solicitarNombre() {
        System.out.print("Nombre del ítem: ");
        return scanner.nextLine();
    }

    public int solicitarCantidad() {
        System.out.print("Cantidad: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String solicitarTipo() {
        System.out.print("Tipo (Arma, Poción, etc.): ");
        return scanner.nextLine();
    }

    public String solicitarDescripcion() {
        System.out.print("Descripción: ");
        return scanner.nextLine();
    }

    public void mostrarInventario(List<Item> items) {
        System.out.println("\n--- INVENTARIO ACTUAL ---");
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Item i : items) {
                System.out.println(i);
            }
        }
    }

    public void mostrarDetalle(Item item) {
        if (item != null) {
            System.out.println("\n--- DETALLE DEL ÍTEM ---");
            System.out.println(item);
        } else {
            System.out.println("Ítem no encontrado.");
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
