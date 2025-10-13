package Guia7.Ejercicios_Propuestos.EJERCICIO3.Modelo;

public class Empleado {
    private int numero;
    private String nombre;
    private double sueldo;

    public Empleado(int numero, String nombre, double sueldo) {
        this.numero = numero;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-10.2f", numero, nombre, sueldo);
    }

    public static Empleado fromString(String linea) {
        String[] partes = linea.split(",");
        return new Empleado(
                Integer.parseInt(partes[0].trim()),
                partes[1].trim(),
                Double.parseDouble(partes[2].trim())
        );
    }

    public String toFileString() {
        return numero + "," + nombre + "," + sueldo;
    }
}
