package GUIA6.Ejercicio_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jugador {
    private String nombre;
    private int salud;
    private int nivel;
    private List<Item> inventario;
    private Item itemEquipado;

    public Jugador(String nombre, int salud, int nivel) {
        this.nombre = nombre;
        this.salud = salud;
        this.nivel = nivel;
        this.inventario = new ArrayList<>();
    }

    public void agregarItem(Item item) {
        inventario.add(item);
    }

    public void equiparItem(String nombreItem) {
        for (Item i : inventario) {
            if (i.getNombre().equalsIgnoreCase(nombreItem)) {
                itemEquipado = i;
                System.out.println("🗡️ " + nombre + " ha equipado: " + i.getNombre());
                return;
            }
        }
        System.out.println("⚠️ Ítem no encontrado en el inventario.");
    }

    public void atacar(Enemigo enemigo) {
        if (itemEquipado == null) {
            System.out.println("⚠️ No tienes ningún ítem equipado para atacar.");
            return;
        }

        Random random = new Random();
        int dañoBase = 5 + random.nextInt(6); // daño entre 5 y 10
        int dañoTotal = dañoBase + (nivel * 2);
        System.out.println(nombre + " ataca a " + enemigo.getNombre() +
                           " con " + itemEquipado.getNombre() + " causando " + dañoTotal + " de daño.");
        enemigo.recibirDaño(dañoTotal);
    }

    public void recibirDaño(int daño) {
        salud -= daño;
        if (salud < 0) salud = 0;
        System.out.println(nombre + " ha recibido " + daño + " de daño. Salud restante: " + salud);
    }

    public void usarItem(String nombreItem) {
        for (Item i : inventario) {
            if (i.getNombre().equalsIgnoreCase(nombreItem)) {
                i.usarItem();
                System.out.println("Has usado: " + i.getNombre());
                return;
            }
        }
        System.out.println("No tienes ese ítem.");
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getNivel() {
        return nivel;
    }
}
