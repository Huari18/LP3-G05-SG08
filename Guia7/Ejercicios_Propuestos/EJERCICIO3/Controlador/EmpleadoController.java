package Guia7.Ejercicios_Propuestos.EJERCICIO3.Controlador;

import Guia7.Ejercicios_Propuestos.EJERCICIO3.Modelo.Empleado;
import java.io.*;
import java.util.*;

public class EmpleadoController {
    private List<Empleado> empleados;
    private final String archivo = "empleados.txt";

    public EmpleadoController() {
        empleados = new ArrayList<>();
        leerEmpleados();
    }

    // Leer todos los empleados desde el archivo
    public void leerEmpleados() {
        empleados.clear();
        File file = new File(archivo);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    empleados.add(Empleado.fromString(linea));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Guardar todos los empleados en el archivo
    private void guardarArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Empleado emp : empleados) {
                bw.write(emp.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Listar empleados
    public void listarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        System.out.printf("%-10s %-20s %-10s%n", "Número", "Nombre", "Sueldo");
        for (Empleado emp : empleados) {
            System.out.println(emp);
        }
    }

    // Agregar un nuevo empleado
    public void agregarEmpleado(Empleado nuevo) {
        if (buscarEmpleado(nuevo.getNumero()) != null) {
            System.out.println("Ya existe un empleado con ese número.");
            return;
        }
        empleados.add(nuevo);
        guardarArchivo();
        System.out.println("Empleado agregado correctamente.");
    }

    // Buscar empleado por número
    public Empleado buscarEmpleado(int numero) {
        for (Empleado e : empleados) {
            if (e.getNumero() == numero) return e;
        }
        return null;
    }

    // Eliminar empleado por número
    public void eliminarEmpleado(int numero) {
        Empleado e = buscarEmpleado(numero);
        if (e != null) {
            empleados.remove(e);
            guardarArchivo();
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("No se encontró un empleado con ese número.");
        }
    }
}
