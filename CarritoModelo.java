package GUIA6;

import java.util.ArrayList;
import java.util.List;

public class CarritoModelo {
    private List<Producto> productos;
    private List<Producto> historialCompras;

    public CarritoModelo() {
        productos = new ArrayList<>();
        historialCompras = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(String nombre) {
        productos.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
    }

    public List<Producto> obtenerProductos() {
        return productos;
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }

    public double aplicarDescuento(double total, double porcentaje) {
        return total - (total * porcentaje / 100);
    }

    public double calcularEnvio(double total) {
        return total >= 100 ? 0 : 10; // envío gratis si supera S/100
    }

    public void realizarCompra() {
        historialCompras.addAll(productos);
        productos.clear();
    }

    public List<Producto> getHistorialCompras() {
        return historialCompras;
    }
}
