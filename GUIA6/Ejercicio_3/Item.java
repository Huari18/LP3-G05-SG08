package GUIA6.Ejercicio_3;

public class Item {
    private String nombre;
    private int cantidad;
    private String tipo; // Arma, Pocion, etc.
    private String descripcion;

    public Item(String nombre, int cantidad, String tipo, String descripcion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Simula el uso del ítem
    public void usarItem() {
        if (cantidad > 0) {
            cantidad--;
            System.out.println("🔹 Has usado el ítem: " + nombre + ". Cantidad restante: " + cantidad);
        } else {
            System.out.println("⚠️ No quedan unidades de este ítem.");
        }
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Cantidad: " + cantidad + " | Tipo: " + tipo + " | Descripción: " + descripcion;
    }
}
