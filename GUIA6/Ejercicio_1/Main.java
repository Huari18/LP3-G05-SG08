package GUIA6.Ejercicio_1;

public class Main {
    public static void main(String[] args) {
        CarritoModelo modelo = new CarritoModelo();
        CarritoVista vista = new CarritoVista();
        CarritoControlador controlador = new CarritoControlador(modelo, vista);
        controlador.iniciar();
    }
}
