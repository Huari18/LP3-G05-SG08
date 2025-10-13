package Guia7.Ejercicios_Propuestos.EJERCICIO1;

public class Personaje {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.alcance = alcance;
    }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAlcance() { return alcance; }

    public void setVida(int vida) { this.vida = vida; }
    public void setAtaque(int ataque) { this.ataque = ataque; }
    public void setDefensa(int defensa) { this.defensa = defensa; }
    public void setAlcance(int alcance) { this.alcance = alcance; }

    @Override
    public String toString() {
        return String.format("%-10s %d %d %d %d", nombre, vida, ataque, defensa, alcance);
    }

    public static Personaje fromString(String linea) {
        String[] partes = linea.split("\\s+");
        return new Personaje(
            partes[0],
            Integer.parseInt(partes[1]),
            Integer.parseInt(partes[2]),
            Integer.parseInt(partes[3]),
            Integer.parseInt(partes[4])
        );
    }
}
