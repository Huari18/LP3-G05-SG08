package GUIA6.Ejercicio_1;
import java.util.ArrayList;
import java.util.List;

public class CarritoModelo {
    private List<Producto> catalogo;
    private List<Producto> carrito;
    private List<Producto> historial;

    public CarritoModelo() {
        catalogo = new ArrayList<>();
        carrito = new ArrayList<>();
        historial = new ArrayList<>();
    }

    // Agregar producto al catálogo
    public void agregarProductoCatalogo(Producto producto) {
        catalogo.add(producto);
    }

    // Listar productos del catálogo
    public List<Producto> obtenerCatalogo() {
        return catalogo;
    }

    // Agregar producto al carrito
    public boolean agregarAlCarrito(String nombre) {
        for (Producto p : catalogo) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                carrito.add(p);
                return true;
            }
        }
        return false;
    }

    // Eliminar producto del carrito
    public void eliminarDelCarrito(String nombre) {
        carrito.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
    }

    // Obtener lista del carrito
    public List<Producto> obtenerCarrito() {
        return carrito;
    }

    // Calcular total
    public double calcularTotal() {
        double total = 0;
        for (Producto p : carrito) {
            total += p.getPrecio();
        }
        return total;
    }

    // Aplicar descuento
    public double aplicarDescuento(double total, double porcentaje) {
        return total - (total * porcentaje / 100);
    }

    // Calcular envío
    public double calcularEnvio(double total) {
        return total >= 100 ? 0 : 10;
    }

    // Realizar compra
    public void realizarCompra() {
        historial.addAll(carrito);
        carrito.clear();
    }

    // Ver historial
    public List<Producto> obtenerHistorial() {
        return historial;
    }
}
