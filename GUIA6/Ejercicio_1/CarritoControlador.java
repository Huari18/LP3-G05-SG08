package GUIA6.Ejercicio_1;

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
                case "1": // Agregar producto al catálogo
                    String nombre = vista.solicitarNombreProducto();
                    double precio = vista.solicitarPrecioProducto();
                    modelo.agregarProductoCatalogo(new Producto(nombre, precio));
                    vista.mostrarMensaje("Producto agregado al catálogo.");
                    break;

                case "2": // Listar productos
                    vista.mostrarProductos("Productos disponibles", modelo.obtenerCatalogo());
                    break;

                case "3": // Agregar al carrito
                    nombre = vista.solicitarNombreProducto();
                    if (modelo.agregarAlCarrito(nombre))
                        vista.mostrarMensaje("🛒 Producto agregado al carrito.");
                    else
                        vista.mostrarMensaje("El producto no existe en el catálogo.");
                    break;

                case "4": // Ver carrito
                    vista.mostrarProductos("Carrito de compras", modelo.obtenerCarrito());
                    vista.mostrarMensaje("Total actual: S/ " + modelo.calcularTotal());
                    break;

                case "5": // Eliminar producto
                    nombre = vista.solicitarNombreProducto();
                    modelo.eliminarDelCarrito(nombre);
                    vista.mostrarMensaje("Producto eliminado del carrito.");
                    break;

                case "6": // Descuento y envío
                    double total = modelo.calcularTotal();
                    double descuento = vista.solicitarDescuento();
                    double totalDescuento = modelo.aplicarDescuento(total, descuento);
                    double envio = modelo.calcularEnvio(totalDescuento);
                    vista.mostrarMensaje("Total con descuento: S/ " + totalDescuento);
                    vista.mostrarMensaje("Envío: S/ " + envio);
                    vista.mostrarMensaje("Total final: S/ " + (totalDescuento + envio));
                    break;

                case "7": // Realizar compra
                    modelo.realizarCompra();
                    vista.mostrarMensaje("Compra realizada con éxito. ¡Gracias!");
                    break;

                case "8": // Ver historial
                    vista.mostrarProductos("Historial de Compras", modelo.obtenerHistorial());
                    break;

                case "9": // Salir
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida, intenta nuevamente.");
            }

        } while (!opcion.equals("9"));

        vista.cerrarScanner();
    }
}
