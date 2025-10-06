package GUIA6;

public class CarritoControlador {
    private CarritoModelo modelo;
    private CarritoVista vista;

    public CarritoControlador(CarritoModelo modelo, CarritoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();

            switch (opcion) {
                case "1": // Agregar producto
                    String nombre = vista.solicitarNombreProducto();
                    double precio = vista.solicitarPrecioProducto();
                    modelo.agregarProducto(new Producto(nombre, precio));
                    vista.mostrarMensaje("Producto agregado correctamente.");
                    break;

                case "2": // Ver carrito
                    vista.mostrarProductos(modelo.obtenerProductos());
                    vista.mostrarMensaje("Total: S/ " + modelo.calcularTotal());
                    break;

                case "3": // Eliminar producto
                    nombre = vista.solicitarNombreProducto();
                    modelo.eliminarProducto(nombre);
                    vista.mostrarMensaje("Producto eliminado (si existía).");
                    break;

                case "4": // Descuento y envío
                    double total = modelo.calcularTotal();
                    double descuento = vista.solicitarDescuento();
                    double totalConDescuento = modelo.aplicarDescuento(total, descuento);
                    double envio = modelo.calcularEnvio(totalConDescuento);
                    vista.mostrarMensaje("Total con descuento: S/ " + totalConDescuento);
                    vista.mostrarMensaje("Costo de envío: S/ " + envio);
                    vista.mostrarMensaje("Total final: S/ " + (totalConDescuento + envio));
                    break;

                case "5": // Realizar compra
                    modelo.realizarCompra();
                    vista.mostrarMensaje("Compra realizada con éxito. ¡Gracias!");
                    break;

                case "6": // Historial
                    vista.mostrarProductos(modelo.getHistorialCompras());
                    break;

                case "7": // Salir
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida, intenta nuevamente.");
            }

        } while (!opcion.equals("7"));

        vista.cerrarScanner();
    }
}
