import java.sql.*;
import java.util.Scanner;

public class App {

    private static final String URL = "jdbc:sqlite:menu_practica.db";
    private static final String CLAVE_CORRECTA = "1234"; // puedes cambiarla
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false); // modo transacción
            crearTabla(conn);

            int opcion;
            do {
                mostrarMenu();
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1 -> ingresarDato(conn);
                    case 2 -> mostrarDatos(conn);
                    case 3 -> actualizarDato(conn);
                    case 4 -> borrarDato(conn);
                    case 5 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción inválida.");
                }
            } while (opcion != 5);

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1. Ingresar nuevo registro");
        System.out.println("2. Mostrar registros");
        System.out.println("3. Actualizar registro");
        System.out.println("4. Borrar registro");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void crearTabla(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS empleados (
                    id INTEGER PRIMARY KEY,
                    nombre TEXT NOT NULL,
                    puesto TEXT NOT NULL,
                    salario REAL NOT NULL,
                    edad INTEGER NOT NULL
                );
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla 'empleados' lista.");
        }
    }

    private static boolean confirmarOperacion(Connection conn) throws SQLException {
        System.out.print("Ingrese la clave para confirmar cambios: ");
        String clave = sc.nextLine();

        if (clave.equals(CLAVE_CORRECTA)) {
            conn.commit();
            System.out.println("Operación confirmada y guardada.");
            return true;
        } else {
            conn.rollback();
            System.out.println("Clave incorrecta. Cambios revertidos.");
            return false;
        }
    }

    private static void ingresarDato(Connection conn) throws SQLException {
        System.out.print("Ingrese ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese puesto: ");
        String puesto = sc.nextLine();
        System.out.print("Ingrese salario: ");
        double salario = sc.nextDouble();
        System.out.print("Ingrese edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        String sql = "INSERT INTO empleados (id, nombre, puesto, salario, edad) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, nombre);
            pstmt.setString(3, puesto);
            pstmt.setDouble(4, salario);
            pstmt.setInt(5, edad);
            pstmt.executeUpdate();
            System.out.println("➕ Registro insertado temporalmente.");

            confirmarOperacion(conn);
        }
    }

    private static void mostrarDatos(Connection conn) throws SQLException {
        String sql = "SELECT * FROM empleados";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nLista de empleados:");
            while (rs.next()) {
                System.out.printf("ID: %d | Nombre: %s | Puesto: %s | Salario: %.2f | Edad: %d%n",
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("puesto"),
                        rs.getDouble("salario"),
                        rs.getInt("edad"));
            }
        }
    }

    private static void actualizarDato(Connection conn) throws SQLException {
        System.out.print("Ingrese ID del empleado a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo salario: ");
        double nuevoSalario = sc.nextDouble();
        sc.nextLine();

        String sql = "UPDATE empleados SET salario = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, nuevoSalario);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println(" Registro actualizado temporalmente.");

            confirmarOperacion(conn);
        }
    }

    private static void borrarDato(Connection conn) throws SQLException {
        System.out.print("Ingrese ID del empleado a borrar: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM empleados WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println(" Registro borrado temporalmente.");

            confirmarOperacion(conn);
        }
    }
}
