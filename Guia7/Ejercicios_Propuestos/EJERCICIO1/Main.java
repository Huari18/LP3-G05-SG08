package Guia7.Ejercicios_Propuestos.EJERCICIO1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== GESTOR DE PERSONAJES ===");
            System.out.println("1. Mostrar personajes");
            System.out.println("2. Agregar personaje");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Eliminar personaje");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> gestor.mostrar();
                case 2 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Vida: ");
                    int vida = sc.nextInt();
                    System.out.print("Ataque: ");
                    int ataque = sc.nextInt();
                    System.out.print("Defensa: ");
                    int defensa = sc.nextInt();
                    System.out.print("Alcance: ");
                    int alcance = sc.nextInt();
                    gestor.agregar(new Personaje(nombre, vida, ataque, defensa, alcance));
                }
                case 3 -> {
                    System.out.print("Nombre del personaje a modificar: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nueva vida: ");
                    int vida = sc.nextInt();
                    System.out.print("Nuevo ataque: ");
                    int ataque = sc.nextInt();
                    System.out.print("Nueva defensa: ");
                    int defensa = sc.nextInt();
                    System.out.print("Nuevo alcance: ");
                    int alcance = sc.nextInt();
                    gestor.modificar(nombre, vida, ataque, defensa, alcance);
                }
                case 4 -> {
                    System.out.print("Nombre del personaje a eliminar: ");
                    String nombre = sc.nextLine();
                    gestor.eliminar(nombre);
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
