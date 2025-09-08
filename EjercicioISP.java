interface Imprimible {
    void imprimir();
}

interface Escaneable {
    void escanear();
}

class Impresora implements Imprimible {
    @Override
    public void imprimir() {
        System.out.println("Imprimiendo documento...");
    }
}

class ImpresoraMultifuncional implements Imprimible, Escaneable {
    @Override
    public void imprimir() {
        System.out.println("Imprimiendo en multifuncional...");
    }

    @Override
    public void escanear() {
        System.out.println("Escaneando documento...");
    }
}

public class EjercicioISP {
    public static void main(String[] args) {
        Imprimible impresora = new Impresora();
        impresora.imprimir();

        ImpresoraMultifuncional multifuncional = new ImpresoraMultifuncional();
        multifuncional.imprimir();
        multifuncional.escanear();
    }
}
