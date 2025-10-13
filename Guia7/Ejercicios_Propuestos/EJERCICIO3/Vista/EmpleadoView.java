package Guia7.Ejercicios_Propuestos.EJERCICIO3.Vista;

import Guia7.Ejercicios_Propuestos.EJERCICIO3.Modelo.Empleado;
import Guia7.Ejercicios_Propuestos.EJERCICIO3.Controlador.EmpleadoController;
import java.util.Scanner;

public class EmpleadoView {
    private EmpleadoController controlador;
    private Scanner sc;

    public EmpleadoView() {
        controlador = new EmpleadoController();
        sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE EMPLEADOS ===");
            System.out.println("1. Listar empleados");
            System.out.println("2. Agregar empleado");
            System.out.println("3. Buscar empleado por número");
            System.out.println("4. Eliminar empleado por número");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> controlador.listarEmpleados();
                case 2 -> agregarEmpleado();
                case 3 -> buscarEmpleado();
                case 4 -> eliminarEmpleado();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregarEmpleado() {
        System.out.print("Número: ");
        int numero = leerEntero();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Sueldo: ");
        double sueldo = leerDouble();

        Empleado nuevo = new Empleado(numero, nombre, sueldo);
        controlador.agregarEmpleado(nuevo);
    }

    private void buscarEmpleado() {
        System.out.print("Ingrese el número del empleado: ");
        int numero = leerEntero();
        Empleado e = controlador.buscarEmpleado(numero);
        if (e != null)
            System.out.println("Empleado encontrado:\n" + e);
        else
            System.out.println("No se encontró el empleado.");
    }

    private void eliminarEmpleado() {
        System.out.print("Ingrese el número del empleado a eliminar: ");
        int numero = leerEntero();
        controlador.eliminarEmpleado(numero);
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Intente nuevamente: ");
            }
        }
    }

    private double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Intente nuevamente: ");
            }
        }
    }
}
