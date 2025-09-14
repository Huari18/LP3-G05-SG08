public class Numero {
    private double valor;

    public Numero(double valor) {
        setValor(valor);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo: " + valor);
        }
        this.valor = valor;
    }

    public static void main(String[] args) {
        try {
            Numero num1 = new Numero(10);
            System.out.println("Número inicial: " + num1.getValor());

            num1.setValor(25);
            System.out.println("Número actualizado: " + num1.getValor());

            num1.setValor(-5); // Aquí lanza la excepción

        } catch (IllegalArgumentException e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
    }
}
