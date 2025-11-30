package franco;

import java.util.ArrayList;
import java.util.List;

// =============== OBSERVER ===============
interface Observer {
    void recibirNotificacion(Notificacion notificacion);
}

// =============== SUJETO (Subject) ===============
interface Sujeto {
    void suscribir(Observer o);
    void desuscribir(Observer o);
    void notificar(Notificacion notificacion);
}

// =============== NOTIFICACION ===============
class Notificacion {
    private String titulo;
    private String mensaje;

    public Notificacion(String titulo, String mensaje) {
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    public String getTitulo() { return titulo; }
    public String getMensaje() { return mensaje; }
}

// =============== USUARIO (Observer Concreto) ===============
class Usuario implements Observer {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void recibirNotificacion(Notificacion n) {
        System.out.println("📩 " + nombre + " recibió notificación: "
                + n.getTitulo() + " - " + n.getMensaje());
    }
}

// =============== SISTEMA DE NOTIFICACIONES (Subject Concreto) ===============
class SistemaNotificaciones implements Sujeto {
    private List<Observer> usuarios = new ArrayList<>();

    @Override
    public void suscribir(Observer o) {
        usuarios.add(o);
    }

    @Override
    public void desuscribir(Observer o) {
        usuarios.remove(o);
    }

    @Override
    public void notificar(Notificacion notificacion) {
        for (Observer usuario : usuarios) {
            usuario.recibirNotificacion(notificacion);
        }
    }
}

// =============== MAIN ===============
public class MainObserver {
    public static void main(String[] args) {
        SistemaNotificaciones sistema = new SistemaNotificaciones();

        Usuario u1 = new Usuario("Franco");
        Usuario u2 = new Usuario("Maria");
        Usuario u3 = new Usuario("Carlos");

        sistema.suscribir(u1);
        sistema.suscribir(u2);

        sistema.notificar(new Notificacion(
                "🔥 Oferta Especial",
                "50% de descuento en laptops gamer."
        ));

        System.out.println("\nCarlos se suscribe al sistema.");
        sistema.suscribir(u3);

        sistema.notificar(new Notificacion(
                "🆕 Nuevo Producto",
                "Teclado mecánico RGB recién llegado."
        ));

        System.out.println("\nMaría se desuscribe.");
        sistema.desuscribir(u2);

        sistema.notificar(new Notificacion(
                "⚡ Promoción Flash",
                "Mouse gamer con 30% de descuento por 2 horas."
        ));
    }
}
