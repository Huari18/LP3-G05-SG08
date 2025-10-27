import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorEmpleados gestor = new GestorEmpleados();

        while (true) {
            System.out.println("\n=== MENÚ EMPLEADOS ===");
            System.out.println("1. Insertar empleado");
            System.out.println("2. Mostrar empleados");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Salario: ");
                double salario = sc.nextDouble();
                sc.nextLine();

                Empleado e = new Empleado(0, nombre, salario);
                gestor.insertarEmpleado(e);

            } else if (op == 2) {
                List<Empleado> lista = gestor.obtenerEmpleados();
                for (Empleado emp : lista) {
                    System.out.println(emp);
                }

            } else if (op == 3) {
                gestor.cerrarConexion();
                System.out.println("Saliendo...");
                break;
            }
        }
        sc.close();
    }
}
