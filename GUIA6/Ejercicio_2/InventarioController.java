package GUIA6.Ejercicio_2;
public class InventarioController {
    private InventarioModel modelo;
    private InventarioView vista;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();

            switch (opcion) {
                case "1": // Agregar ítem
                    String nombre = vista.solicitarNombre();
                    int cantidad = vista.solicitarCantidad();
                    String tipo = vista.solicitarTipo();
                    String descripcion = vista.solicitarDescripcion();
                    modelo.agregarItem(new Item(nombre, cantidad, tipo, descripcion));
                    vista.mostrarMensaje("✅ Ítem agregado correctamente.");
                    break;

                case "2": // Eliminar ítem
                    nombre = vista.solicitarNombre();
                    modelo.eliminarItem(nombre);
                    vista.mostrarMensaje("❌ Ítem eliminado (si existía).");
                    break;

                case "3": // Ver inventario
                    vista.mostrarInventario(modelo.obtenerItems());
                    break;

                case "4": // Buscar ítem
                    nombre = vista.solicitarNombre();
                    Item encontrado = modelo.buscarItem(nombre);
                    vista.mostrarDetalle(encontrado);
                    break;

                case "5": // Usar ítem
                    nombre = vista.solicitarNombre();
                    Item itemUsar = modelo.buscarItem(nombre);
                    if (itemUsar != null) {
                        itemUsar.usarItem();
                    } else {
                        vista.mostrarMensaje("⚠️ Ítem no encontrado.");
                    }
                    break;

                case "6": // Salir
                    vista.mostrarMensaje("👋 Saliendo del sistema...");
                    break;

                default:
                    vista.mostrarMensaje("⚠️ Opción no válida. Intenta nuevamente.");
            }
        } while (!opcion.equals("6"));

        vista.cerrarScanner();
    }
}
