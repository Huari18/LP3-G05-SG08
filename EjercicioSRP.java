class Empleado {
    private String nombre;
    private double salario;
    private String departamento;

    public Empleado(String nombre, double salario, String departamento) {
        this.nombre = nombre;
        this.salario = salario;
        this.departamento = departamento;
    }

    public String getNombre() { return nombre; }
    public double getSalario() { return salario; }
    public String getDepartamento() { return departamento; }
}

class CalculadoraPago {
    public double calcularPagoMensual(Empleado e) {
        return e.getSalario() / 12;
    }
}

public class EjercicioSRP {
    public static void main(String[] args) {
        Empleado emp = new Empleado("Juan", 12000, "TI");
        CalculadoraPago calc = new CalculadoraPago();
        System.out.println("Pago mensual de " + emp.getNombre() + ": " + calc.calcularPagoMensual(emp));
    }
}
