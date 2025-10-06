package GUIA6.Ejercicio_3;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CombateController {
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private InventarioView vista;

    public CombateController(Jugador jugador, List<Enemigo> enemigos, InventarioView vista) {
        this.jugador = jugador;
        this.enemigos = enemigos;
        this.vista = vista;
    }

    public void iniciarCombate() {
        Scanner scanner= new Scanner(System.in);
        vista.mostrarMensaje("\n¡Comienza el combate! ");

        while (jugador.estaVivo() && hayEnemigosVivos()) {
            vista.mostrarMensaje("\n=== Turno del Jugador ===");
            vista.mostrarMensaje("1. Atacar");
            vista.mostrarMensaje("2. Usar ítem");
            vista.mostrarMensaje("3. Equipar ítem");
            vista.mostrarMensaje("4. Ver estado");

            System.out.print("Selecciona una acción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    Enemigo objetivo = seleccionarEnemigo();
                    if (objetivo != null) jugador.atacar(objetivo);
                    break;

                case "2":
                    System.out.print("Nombre del ítem a usar: ");
                    String nombreItem = scanner.nextLine();
                    jugador.usarItem(nombreItem);
                    break;

                case "3":
                    System.out.print("Nombre del ítem a equipar: ");
                    String itemEq = scanner.nextLine();
                    jugador.equiparItem(itemEq);
                    break;

                case "4":
                    vista.mostrarMensaje("Jugador: " + jugador.getNombre() + " | Salud: " + jugador.getSalud());
                    for (Enemigo e : enemigos) {
                        vista.mostrarMensaje("Enemigo: " + e.getNombre() + " | Salud: " + e.getSalud());
                    }
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida.");
            }

            enemigosAtacan();
        }

        if (jugador.estaVivo()) {
            vista.mostrarMensaje("\n¡Has ganado el combate!");
        } else {
            vista.mostrarMensaje("\nHas sido derrotado...");
        }
    }

    private boolean hayEnemigosVivos() {
        for (Enemigo e : enemigos) {
            if (e.estaVivo()) return true;
        }
        return false;
    }

    private Enemigo seleccionarEnemigo() {
        for (Enemigo e : enemigos) {
            if (e.estaVivo()) {
                return e; // ataca al primer enemigo vivo
            }
        }
        return null;
    }

    private void enemigosAtacan() {
        Random random = new Random();
        vista.mostrarMensaje("\n=== Turno de los Enemigos ===");
        for (Enemigo e : enemigos) {
            if (e.estaVivo() && random.nextBoolean()) {
                e.atacar(jugador);
            }
        }
    }
}
