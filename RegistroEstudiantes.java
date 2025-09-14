import java.util.NoSuchElementException;

public class RegistroEstudiantes {
    private String[] estudiantes;
    private int contador;

    public RegistroEstudiantes(int capacidad) {
        estudiantes = new String[capacidad];
        contador = 0;
    }

    public void agregarEstudiante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante no puede ser nulo o vacío.");
        }
        if (contador >= estudiantes.length) {
            throw new IllegalArgumentException("No se pueden agregar más estudiantes, capacidad llena.");
        }
        estudiantes[contador++] = nombre;
    }

    public String buscarEstudiante(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (estudiantes[i].equalsIgnoreCase(nombre)) {
                return estudiantes[i];
            }
        }
        throw new NoSuchElementException("Estudiante no encontrado: " + nombre);
    }

    public static void main(String[] args) {
        RegistroEstudiantes registro = new RegistroEstudiantes(3);

        try {
            registro.agregarEstudiante("Ana");
            registro.agregarEstudiante("Carlos");
            registro.agregarEstudiante("Maria");

            System.out.println("Estudiante encontrado: " + registro.buscarEstudiante("Carlos"));

            registro.agregarEstudiante(""); // lanza IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        try {
            System.out.println("Estudiante encontrado: " + registro.buscarEstudiante("Pedro")); // lanza NoSuchElementException
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}
