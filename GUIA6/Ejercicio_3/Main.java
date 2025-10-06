package GUIA6.Ejercicio_3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear jugador
        Jugador jugador = new Jugador("Auron", 100, 3);

        // Crear ítems
        Item espada = new Item("Espada de Hierro", 1, "Arma", "Un arma básica de acero.");
        Item pocion = new Item("Poción de Vida", 3, "Poción", "Restaura salud al usarla.");

        // Agregar ítems al jugador
        jugador.agregarItem(espada);
        jugador.agregarItem(pocion);
        jugador.equiparItem("Espada de Hierro");

        // Crear enemigos
        List<Enemigo> enemigos = new ArrayList<>();
        enemigos.add(new Enemigo("Goblin", 50, 1, "Orco"));
        enemigos.add(new Enemigo("Esqueleto", 60, 2, "No muerto"));

        // Vista y controlador de combate
        InventarioView vista = new InventarioView();
        CombateController combate = new CombateController(jugador, enemigos, vista);

        // Iniciar combate
        combate.iniciarCombate();
    }
}
