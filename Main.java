import java.util.*;

// ==========================
// INTERFAZ
// ==========================
interface Identificable {
    String getId();
}

// ==========================
// CLASE ABSTRACTA (Herencia)
// ==========================
abstract class Persona implements Identificable {
    protected String id;
    protected String nombre;
    protected String email;

    protected static int totalPersonas = 0;

    public Persona(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        totalPersonas++;
    }

    public abstract void mostrarInfo(); // polimorfismo

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

    public static int getTotalPersonas() { return totalPersonas; }
}

// ==========================
// SUBCLASES: Estudiante y Profesor
// ==========================
class Estudiante extends Persona {
    private String codigo;
    private static int totalEstudiantes = 0;

    public Estudiante(String id, String nombre, String email, String codigo) {
        super(id, nombre, email);
        this.codigo = codigo;
        totalEstudiantes++;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Estudiante: " + nombre + " | Código: " + codigo);
    }

    public static int getTotalEstudiantes() { return totalEstudiantes; }
    public String getCodigo() { return codigo; }
}

class Profesor extends Persona {
    private String especialidad;
    private static int totalProfesores = 0;

    public Profesor(String id, String nombre, String email, String especialidad) {
        super(id, nombre, email);
        this.especialidad = especialidad;
        totalProfesores++;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Profesor: " + nombre + " | Esp: " + especialidad);
    }

    public static int getTotalProfesores() { return totalProfesores; }
    public String getEspecialidad() { return especialidad; }
}

// ==========================
// ENUM de categorías
// ==========================
enum Categoria {
    MATEMATICAS, PROGRAMACION, FISICA, QUIMICA
}

// ==========================
// CLASE Curso (agregación con profesor y estudiantes)
// ==========================
class Curso implements Identificable {
    public static final int MAX_ESTUDIANTES = 40;
    private static int secuencia = 1;
    private static int totalCursos = 0;

    private final String idCurso;
    private String nombre;
    private Categoria categoria;
    private Profesor profesor;
    private final List<Estudiante> estudiantes;

    public Curso(String nombre, Categoria categoria, Profesor profesor) {
        this.idCurso = "CUR-" + (secuencia++);
        this.nombre = nombre;
        this.categoria = categoria;
        this.profesor = profesor;
        this.estudiantes = new ArrayList<>();
        totalCursos++;
    }

    public boolean inscribirEstudiante(Estudiante e) {
        if (e == null) return false;
        if (estudiantes.size() >= MAX_ESTUDIANTES) return false;
        if (estudiantes.contains(e)) return false;
        estudiantes.add(e);
        return true;
    }

    public boolean retirarEstudiante(String idEst) {
        return estudiantes.removeIf(e -> e.getId().equals(idEst));
    }

    public void mostrarCurso() {
        System.out.println("[" + idCurso + "] " + nombre + " (" + categoria + ")");
        System.out.println("  Docente: " + (profesor != null ? profesor.getNombre() : "sin asignar"));
        System.out.println("  Matriculados: " + estudiantes.size() + "/" + MAX_ESTUDIANTES);
        for (Estudiante e : estudiantes) {
            e.mostrarInfo(); // polimorfismo
        }
    }

    public String getId() { return idCurso; }
    public String getNombre() { return nombre; }
    public Categoria getCategoria() { return categoria; }
    public Profesor getProfesor() { return profesor; }

    public static int getTotalCursos() { return totalCursos; }
}

// ==========================
// CLASE SistemaGestion
// ==========================
class SistemaGestion {
    private final Map<String, Estudiante> estudiantes = new HashMap<>();
    private final Map<String, Profesor> profesores = new HashMap<>();
    private final Map<String, Curso> cursos = new HashMap<>();

    public void registrarEstudiante(Estudiante e) { estudiantes.put(e.getId(), e); }
    public void registrarProfesor(Profesor p) { profesores.put(p.getId(), p); }
    public void registrarCurso(Curso c) { cursos.put(c.getId(), c); }

    public boolean inscribir(String idCurso, String idEst) {
        Curso c = cursos.get(idCurso);
        Estudiante e = estudiantes.get(idEst);
        if (c == null || e == null) return false;
        return c.inscribirEstudiante(e);
    }

    public boolean retirar(String idCurso, String idEst) {
        Curso c = cursos.get(idCurso);
        if (c == null) return false;
        return c.retirarEstudiante(idEst);
    }

    public void mostrarResumen() {
        System.out.println("Estudiantes: " + Estudiante.getTotalEstudiantes());
        System.out.println("Profesores:  " + Profesor.getTotalProfesores());
        System.out.println("Cursos:      " + Curso.getTotalCursos());
        System.out.println("Personas:    " + Persona.getTotalPersonas());
    }

    public void mostrarCursos() {
        for (Curso c : cursos.values()) {
            c.mostrarCurso();
            System.out.println("-----------------");
        }
    }
}

// ==========================
// MAIN
// ==========================
public class Main {
    public static void main(String[] args) {
        SistemaGestion sistema = new SistemaGestion();

        Profesor p1 = new Profesor("P1", "Carlos Pérez", "carlos@uni.edu", "Programación");
        Profesor p2 = new Profesor("P2", "Ana Torres", "ana@uni.edu", "Matemáticas");
        sistema.registrarProfesor(p1);
        sistema.registrarProfesor(p2);

        Curso poo = new Curso("POO en Java", Categoria.PROGRAMACION, p1);
        Curso algebra = new Curso("Álgebra", Categoria.MATEMATICAS, p2);
        sistema.registrarCurso(poo);
        sistema.registrarCurso(algebra);

        Estudiante e1 = new Estudiante("E1", "Juan", "juan@uni.edu", "2023001");
        Estudiante e2 = new Estudiante("E2", "María", "maria@uni.edu", "2023002");
        sistema.registrarEstudiante(e1);
        sistema.registrarEstudiante(e2);

        sistema.inscribir(poo.getId(), e1.getId());
        sistema.inscribir(poo.getId(), e2.getId());

        sistema.mostrarResumen();
        sistema.mostrarCursos();
    }
}
