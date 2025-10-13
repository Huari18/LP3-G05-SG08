package Guia7.Ejercicios_Propuestos.EJERCICIO2;

import java.io.*;
import java.util.*;

public class Gestor {
    private List<Personaje> personajes;
    private final String archivo = "personajes.txt";

    public Gestor() {
        personajes = new ArrayList<>();
        cargarArchivo();
        if (personajes.isEmpty()) cargarPersonajesAleatorios();
    }

    // Cargar desde archivo
    private void cargarArchivo() {
        personajes.clear();
        File file = new File(archivo);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null && !linea.trim().isEmpty()) {
                personajes.add(Personaje.fromString(linea));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Guardar en archivo
    private void guardarArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Cargar personajes predefinidos
    public void cargarPersonajesAleatorios() {
        personajes.add(new Personaje("Caballero", 4, 2, 4, 2));
        personajes.add(new Personaje("Guerrero", 2, 4, 2, 4));
        personajes.add(new Personaje("Arquero", 2, 4, 1, 8));
        guardarArchivo();
        System.out.println("Se cargaron personajes iniciales.");
    }

    public void agregar(Personaje p) {
        if (buscar(p.getNombre()) != null) {
            System.out.println("El personaje ya existe.");
            return;
        }
        personajes.add(p);
        guardarArchivo();
        System.out.println("Personaje agregado correctamente.");
    }

    public Personaje buscar(String nombre) {
        for (Personaje p : personajes)
            if (p.getNombre().equalsIgnoreCase(nombre))
                return p;
        return null;
    }

    public void modificar(String nombre, int vida, int ataque, int defensa, int alcance) {
        Personaje p = buscar(nombre);
        if (p != null) {
            p.setVida(vida);
            p.setAtaque(ataque);
            p.setDefensa(defensa);
            p.setAlcance(alcance);
            guardarArchivo();
            System.out.println("Personaje modificado.");
        } else System.out.println("No se encontró el personaje.");
    }

    // Actualizar atributo individual
    public void actualizarAtributo(String nombre, String atributo, int valor) {
        Personaje p = buscar(nombre);
        if (p == null) {
            System.out.println("No se encontró el personaje.");
            return;
        }
        switch (atributo.toLowerCase()) {
            case "vida" -> p.setVida(valor);
            case "ataque" -> p.setAtaque(valor);
            case "defensa" -> p.setDefensa(valor);
            case "alcance" -> p.setAlcance(valor);
            default -> { System.out.println("Atributo inválido."); return; }
        }
        guardarArchivo();
        System.out.println("Atributo actualizado.");
    }

    public void eliminar(String nombre) {
        Personaje p = buscar(nombre);
        if (p != null) {
            personajes.remove(p);
            guardarArchivo();
            System.out.println("Personaje eliminado.");
        } else System.out.println("No se encontró el personaje.");
    }

    // Filtrar personajes por atributo
    public void ordenarPor(String atributo) {
        Comparator<Personaje> cmp = switch (atributo.toLowerCase()) {
            case "vida" -> Comparator.comparingInt(Personaje::getVida);
            case "ataque" -> Comparator.comparingInt(Personaje::getAtaque);
            case "defensa" -> Comparator.comparingInt(Personaje::getDefensa);
            case "alcance" -> Comparator.comparingInt(Personaje::getAlcance);
            case "nivel" -> Comparator.comparingInt(Personaje::getNivel);
            default -> null;
        };
        if (cmp == null) {
            System.out.println("Atributo inválido para ordenar.");
            return;
        }
        personajes.sort(cmp.reversed());
        mostrar();
    }

    // Mostrar estadísticas
    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes.");
            return;
        }
        double promVida = personajes.stream().mapToInt(Personaje::getVida).average().orElse(0);
        double promAtaque = personajes.stream().mapToInt(Personaje::getAtaque).average().orElse(0);
        double promDefensa = personajes.stream().mapToInt(Personaje::getDefensa).average().orElse(0);
        double promAlcance = personajes.stream().mapToInt(Personaje::getAlcance).average().orElse(0);

        System.out.println("Total de personajes: " + personajes.size());
        System.out.printf("Promedio Vida: %.2f | Ataque: %.2f | Defensa: %.2f | Alcance: %.2f%n",
                promVida, promAtaque, promDefensa, promAlcance);
    }

    // Importar personajes desde otro archivo
    public void importarDesdeArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Personaje p = Personaje.fromString(linea);
                if (buscar(p.getNombre()) == null)
                    personajes.add(p);
            }
            guardarArchivo();
            System.out.println("Importación completada.");
        } catch (IOException e) {
            System.out.println("Error al importar: " + e.getMessage());
        }
    }

    // Subir de nivel
    public void subirNivel(String nombre) {
        Personaje p = buscar(nombre);
        if (p != null) {
            p.subirNivel();
            guardarArchivo();
            System.out.println("El personaje " + nombre + " subió a nivel " + p.getNivel());
        } else System.out.println("No se encontró el personaje.");
    }

    public void mostrar() {
        System.out.printf("%-10s %-5s %-7s %-8s %-8s %-6s%n", "Nombre", "Vida", "Ataque", "Defensa", "Alcance", "Nivel");
        for (Personaje p : personajes) {
            System.out.printf("%-10s %-5d %-7d %-8d %-8d %-6d%n",
                    p.getNombre(), p.getVida(), p.getAtaque(), p.getDefensa(), p.getAlcance(), p.getNivel());
        }
    }
}
