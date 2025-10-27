import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorEmpleados {
    private Connection conn;

    public GestorEmpleados() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:empleados.db");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS empleados (id INTEGER PRIMARY KEY, nombre TEXT, salario REAL)");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public void insertarEmpleado(Empleado e) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO empleados(nombre, salario) VALUES (?, ?)");
            ps.setString(1, e.getNombre());
            ps.setDouble(2, e.getSalario());
            ps.executeUpdate();
            System.out.println("Empleado insertado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al insertar empleado: " + ex.getMessage());
        }
    }

    public List<Empleado> obtenerEmpleados() {
        List<Empleado> lista = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM empleados");
            while (rs.next()) {
                lista.add(new Empleado(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("salario")));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener empleados: " + e.getMessage());
        }
        return lista;
    }

    public void cerrarConexion() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
