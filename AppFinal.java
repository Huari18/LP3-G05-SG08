package aaa;

import java.util.*;

//================================================================
//OBSERVER
//================================================================
interface Observer {
 void recibirNotificacion(String mensaje);
}

interface Sujeto {
 void suscribir(Observer o);
 void desuscribir(Observer o);
 void notificar(String mensaje);
}

class Usuario implements Observer {
 private String nombre;

 public Usuario(String nombre) {
     this.nombre = nombre;
 }

 @Override
 public void recibirNotificacion(String mensaje) {
     System.out.println("📩 " + nombre + " recibió: " + mensaje);
 }
}

class SistemaNotificaciones implements Sujeto {
 private List<Observer> usuarios = new ArrayList<>();

 @Override
 public void suscribir(Observer o) { usuarios.add(o); }

 @Override
 public void desuscribir(Observer o) { usuarios.remove(o); }

 @Override
 public void notificar(String mensaje) {
     for (Observer u : usuarios) {
         u.recibirNotificacion(mensaje);
     }
 }
}

//================================================================
//STRATEGY (Descuentos)
//================================================================
class Producto {
 private String nombre;
 private double precio;

 public Producto(String nombre, double precio) {
     this.nombre = nombre;
     this.precio = precio;
 }

 public String getNombre() { return nombre; }
 public double getPrecio() { return precio; }
}

interface EstrategiaDescuento {
 double aplicarDescuento(List<Producto> productos);
}

class SinDescuento implements EstrategiaDescuento {
 public double aplicarDescuento(List<Producto> productos) {
     return productos.stream().mapToDouble(Producto::getPrecio).sum();
 }
}

class Descuento10 implements EstrategiaDescuento {
 public double aplicarDescuento(List<Producto> productos) {
     double total = productos.stream().mapToDouble(Producto::getPrecio).sum();
     return total * 0.90;
 }
}

class Descuento50ProductoMasBarato implements EstrategiaDescuento {
 public double aplicarDescuento(List<Producto> productos) {
     double total = productos.stream().mapToDouble(Producto::getPrecio).sum();

     if (productos.size() >= 3) {
         double menor = productos.stream().mapToDouble(Producto::getPrecio).min().getAsDouble();
         total -= menor * 0.50;
     }
     return total;
 }
}

class CalculadoraPrecios {
 private EstrategiaDescuento estrategia;

 public void setEstrategia(EstrategiaDescuento e) { estrategia = e; }

 public double calcular(List<Producto> productos) {
     return estrategia.aplicarDescuento(productos);
 }
}

//================================================================
//COMMAND
//================================================================
interface Command {
 void ejecutar();
 void deshacer();
}

class Carrito {
 private List<Producto> productos = new ArrayList<>();

 public void agregarProducto(Producto p) { productos.add(p); }
 public void quitarProducto(Producto p) { productos.remove(p); }
 public List<Producto> getProductos() { return productos; }

 public void mostrarCarrito() {
     System.out.println("\n🛒 Carrito actual:");
     for (Producto p : productos) {
         System.out.println(" - " + p.getNombre() + " ($" + p.getPrecio() + ")");
     }
 }
}

class AgregarProductoCommand implements Command {
 private Carrito carrito;
 private Producto producto;

 public AgregarProductoCommand(Carrito c, Producto p) {
     carrito = c;
     producto = p;
 }

 public void ejecutar() {
     carrito.agregarProducto(producto);
     System.out.println("✔ Producto agregado: " + producto.getNombre());
 }

 public void deshacer() {
     carrito.quitarProducto(producto);
     System.out.println("↩ Se deshizo agregar: " + producto.getNombre());
 }
}

class QuitarProductoCommand implements Command {
 private Carrito carrito;
 private Producto producto;

 public QuitarProductoCommand(Carrito c, Producto p) {
     carrito = c;
     producto = p;
 }

 public void ejecutar() {
     carrito.quitarProducto(producto);
     System.out.println("🗑 Producto quitado: " + producto.getNombre());
 }

 public void deshacer() {
     carrito.agregarProducto(producto);
     System.out.println("↩ Se deshizo quitar: " + producto.getNombre());
 }
}

class PagarCommand implements Command {
 private Carrito carrito;
 private CalculadoraPrecios calculadora;

 public PagarCommand(Carrito c, CalculadoraPrecios calc) {
     carrito = c;
     calculadora = calc;
 }

 public void ejecutar() {
     double total = calculadora.calcular(carrito.getProductos());
     System.out.println("\n💵 Total a pagar: $" + total);
 }

 public void deshacer() {
     System.out.println("⚠ No se puede deshacer un pago.");
 }
}

//================================================================
//APLICACIÓN FINAL
//================================================================
public class AppFinal {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);

     // SISTEMA DE NOTIFICACIONES
     SistemaNotificaciones sistema = new SistemaNotificaciones();
     Usuario franco = new Usuario("Franco");
     sistema.suscribir(franco);

     sistema.notificar("🎉 Bienvenido a la Tienda Gamer");

     // CARRITO
     Carrito carrito = new Carrito();
     CalculadoraPrecios calc = new CalculadoraPrecios();

     List<Command> historial = new ArrayList<>();

     // PRODUCTOS DISPONIBLES
     Producto mouse = new Producto("Mouse Gamer", 50);
     Producto teclado = new Producto("Teclado Mecánico", 120);
     Producto audifonos = new Producto("Audífonos RGB", 80);

     // MENÚ
     int opcion = 0;

     do {
         System.out.println("\n========= MENÚ =========");
         System.out.println("1. Agregar Mouse ($50)");
         System.out.println("2. Agregar Teclado ($120)");
         System.out.println("3. Agregar Audífonos ($80)");
         System.out.println("4. Quitar Mouse");
         System.out.println("5. Ver carrito");
         System.out.println("6. Elegir descuento");
         System.out.println("7. Pagar");
         System.out.println("8. Deshacer última acción");
         System.out.println("0. Salir");
         System.out.print("Seleccione: ");
         opcion = sc.nextInt();

         Command comando = null;

         switch (opcion) {
             case 1 -> comando = new AgregarProductoCommand(carrito, mouse);
             case 2 -> comando = new AgregarProductoCommand(carrito, teclado);
             case 3 -> comando = new AgregarProductoCommand(carrito, audifonos);
             case 4 -> comando = new QuitarProductoCommand(carrito, mouse);
             case 5 -> carrito.mostrarCarrito();
             case 6 -> {
                 System.out.println("\nDescuentos:");
                 System.out.println("1. Sin descuento");
                 System.out.println("2. 10%");
                 System.out.println("3. 50% al producto más barato (si hay 3)");
                 int d = sc.nextInt();

                 switch (d) {
                     case 1 -> calc.setEstrategia(new SinDescuento());
                     case 2 -> calc.setEstrategia(new Descuento10());
                     case 3 -> calc.setEstrategia(new Descuento50ProductoMasBarato());
                 }

                 sistema.notificar("🛍 Nueva estrategia de descuento activada.");
             }
             case 7 -> {
                 comando = new PagarCommand(carrito, calc);
             }
             case 8 -> {
                 if (!historial.isEmpty()) {
                     historial.remove(historial.size() - 1).deshacer();
                 } else {
                     System.out.println("No hay acciones para deshacer.");
                 }
             }
         }

         if (comando != null) {
             comando.ejecutar();
             historial.add(comando);
         }

     } while (opcion != 0);

     System.out.println("👋 Gracias por usar la aplicación.");
 }
}

