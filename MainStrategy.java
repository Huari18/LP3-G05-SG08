package franquito;
import java.util.*;

//=============== Producto ===============
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

//=============== Strategy ===============
interface EstrategiaDescuento {
 double calcular(List<Producto> productos);
}

//=============== Estrategia 1: Sin Descuento ===============
class SinDescuento implements EstrategiaDescuento {
 @Override
 public double calcular(List<Producto> productos) {
     return productos.stream().mapToDouble(Producto::getPrecio).sum();
 }
}

//=============== Estrategia 2: Descuento Fijo 10% ===============
class DescuentoFijo implements EstrategiaDescuento {
 @Override
 public double calcular(List<Producto> productos) {
     double total = productos.stream().mapToDouble(Producto::getPrecio).sum();
     return total * 0.90;
 }
}

//=============== Estrategia 3: 30% si hay 2 iguales ===============
class DescuentoPorcentual implements EstrategiaDescuento {
 @Override
 public double calcular(List<Producto> productos) {
     double total = productos.stream().mapToDouble(Producto::getPrecio).sum();

     Map<String, Integer> contador = new HashMap<>();
     for (Producto p : productos) {
         contador.put(p.getNombre(), contador.getOrDefault(p.getNombre(), 0) + 1);
     }

     for (Producto p : productos) {
         if (contador.get(p.getNombre()) >= 2) {
             total -= p.getPrecio() * 0.30;
             break;
         }
     }
     return total;
 }
}

//=============== Estrategia 4: Acumulado (50% al más barato entre 3)===============
class DescuentoPorcentualAcumulado implements EstrategiaDescuento {
 @Override
 public double calcular(List<Producto> productos) {
     double total = productos.stream().mapToDouble(Producto::getPrecio).sum();

     if (productos.size() >= 3) {
         double menor = productos.stream().mapToDouble(Producto::getPrecio).min().getAsDouble();
         total -= menor * 0.50;
     }
     return total;
 }
}

//=============== Calculadora ===============
class CalculadoraDePrecios {
 private EstrategiaDescuento estrategia;

 public void setEstrategia(EstrategiaDescuento e) {
     estrategia = e;
 }

 public double calcular(List<Producto> productos) {
     return estrategia.calcular(productos);
 }
}

//=============== MAIN ===============
public class MainStrategy {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     List<Producto> carrito = new ArrayList<>();

     carrito.add(new Producto("Mouse", 50));
     carrito.add(new Producto("Mouse", 50));
     carrito.add(new Producto("Teclado", 100));

     CalculadoraDePrecios calc = new CalculadoraDePrecios();

     System.out.println("\n=== MENÚ DE DESCUENTOS ===");
     System.out.println("1. Sin descuento");
     System.out.println("2. Descuento fijo (10%)");
     System.out.println("3. Descuento porcentual (30% por 2 productos iguales)");
     System.out.println("4. Descuento acumulado (3 productos → 50% al más barato)");
     System.out.print("Seleccione una opción: ");
     int op = sc.nextInt();

     switch (op) {
         case 1 -> calc.setEstrategia(new SinDescuento());
         case 2 -> calc.setEstrategia(new DescuentoFijo());
         case 3 -> calc.setEstrategia(new DescuentoPorcentual());
         case 4 -> calc.setEstrategia(new DescuentoPorcentualAcumulado());
         default -> {
             System.out.println("Opción inválida");
             return;
         }
     }

     double resultado = calc.calcular(carrito);
     System.out.println("\nTotal a pagar: " + resultado);
 }
}

