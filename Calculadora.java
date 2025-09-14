class DivisionPorCeroException extends Exception {
    public DivisionPorCeroException() {
        super("Error: No se puede dividir entre cero.");
    }
}

public class Calculadora {
    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) throws DivisionPorCeroException {
        if (b == 0) {
            throw new DivisionPorCeroException();
        }
        return a / b;
    }

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        try {
            double suma = calc.sumar(10, 5);
            System.out.println("Suma: " + suma);

            double resta = calc.restar(10, 5);
            System.out.println("Resta: " + resta);

            double multiplicacion = calc.multiplicar(10, 5);
            System.out.println("Multiplicación: " + multiplicacion);

            double division = calc.dividir(10, 0); // aquí lanza excepción
            System.out.println("División: " + division);

        } catch (DivisionPorCeroException e) {
            System.out.println("Excepción personalizada: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción de argumento ilegal: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Excepción aritmética: " + e.getMessage());
        }
    }
}
