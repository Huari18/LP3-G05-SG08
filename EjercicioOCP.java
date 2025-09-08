interface Dibujable {
    void dibujar();
}

class Circulo implements Dibujable {
    public void dibujar() {
        System.out.println("Dibujando un círculo");
    }
}

class Rectangulo implements Dibujable {
    public void dibujar() {
        System.out.println("Dibujando un rectángulo");
    }
}

class Triangulo implements Dibujable {
    public void dibujar() {
        System.out.println("Dibujando un triángulo");
    }
}

public class EjercicioOCP {
    public static void main(String[] args) {
        Dibujable[] formas = {new Circulo(), new Rectangulo(), new Triangulo()};
        for (Dibujable f : formas) {
            f.dibujar();
        }
    }
}
