package GUIA6.Ejercicio_3;

import java.util.Random;

public class Enemigo {
    private String nombre;
    private int salud;
    private int nivel;
    private String tipo;

    public Enemigo(String nombre, int salud, int nivel, String tipo) {
        this.nombre = nombre;
        this.salud = salud;
        this.nivel = nivel;
        this.tipo = tipo;
    }

    public void atacar(Jugador jugador) {
        Random random = new Random();
        int daño = 3 + random.nextInt(8); // entre 3 y 10
        System.out.println("👹 " + nombre + " (" + tipo + ") ataca causando " + daño + " de daño.");
        jugador.recibirDaño(daño);
    }

    public void recibirDaño(int daño) {
        salud -= daño;
        if (salud < 0) salud = 0;
        System.out.println("😵 " + nombre + " ha recibido " + daño + " de daño. Salud restante: " + salud);
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

    public String getTipo() {
        return tipo;
    }
}
