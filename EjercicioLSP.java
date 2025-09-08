abstract class Vehiculo {
    public abstract void acelerar();
}

class Coche extends Vehiculo {
    @Override
    public void acelerar() {
        System.out.println("El coche acelera usando el motor");
    }
}

class Bicicleta extends Vehiculo {
    @Override
    public void acelerar() {
        System.out.println("La bicicleta acelera pedaleando");
    }
}

public class EjercicioLSP {
    public static void main(String[] args) {
        Vehiculo v1 = new Coche();
        Vehiculo v2 = new Bicicleta();
        
        v1.acelerar();
        v2.acelerar();
    }
}
