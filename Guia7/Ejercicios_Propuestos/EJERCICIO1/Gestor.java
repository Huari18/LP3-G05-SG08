package Guia7.Ejercicios_Propuestos.EJERCICIO1;

import java.io.*;
import java.util.*;

public class Gestor {
    private List<Personaje> personajes;
    private final String archivo = "personajes.txt";

    public Gestor() {
        personajes = new ArrayList<>();
        cargarArchivo();
    }

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
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) return p;
        }
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
        } else {
            System.out.println("No se encontró el personaje.");
        }
    }

    public void eliminar(String nombre) {
        Personaje p = buscar(nombre);
        if (p != null) {
            personajes.remove(p);
            guardarArchivo();
            System.out.println("Personaje eliminado.");
        } else {
            System.out.println("No se encontró el personaje.");
        }
    }

    public void mostrar() {
        System.out.printf("%-10s %-5s %-7s %-8s %-8s%n", "Nombre", "Vida", "Ataque", "Defensa", "Alcance");
        for (Personaje p : personajes) {
            System.out.printf("%-10s %-5d %-7d %-8d %-8d%n", 
                p.getNombre(), p.getVida(), p.getAtaque(), p.getDefensa(), p.getAlcance());
        }
    }
}
