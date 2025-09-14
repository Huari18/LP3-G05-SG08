import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class LeerEntrada {
    private Reader stream;

    public LeerEntrada(InputStream fuente) {
        stream = new InputStreamReader(fuente);
    }

    public char getChar() throws IOException {
        return (char) this.stream.read();
    }
}

class ExcepcionVocal extends Exception {
    public ExcepcionVocal(char c) {
        super("Se ingresó una vocal: " + c);
    }
}

class ExcepcionNumero extends Exception {
    public ExcepcionNumero(char c) {
        super("Se ingresó un número: " + c);
    }
}

class ExcepcionBlanco extends Exception {
    public ExcepcionBlanco() {
        super("Se ingresó un espacio en blanco.");
    }
}

class ExcepcionSalida extends Exception {
    public ExcepcionSalida() {
        super("Se ingresó el carácter de salida. Terminando programa...");
    }
}

public class ProcesarEntrada {
    private LeerEntrada entrada;
    private char ultimoCaracter;

    public ProcesarEntrada(InputStream fuente) {
        entrada = new LeerEntrada(fuente);
    }

    public void procesar() throws IOException,
                                   ExcepcionVocal,
                                   ExcepcionNumero,
                                   ExcepcionBlanco,
                                   ExcepcionSalida {
        ultimoCaracter = entrada.getChar();

        if ("aeiouAEIOU".indexOf(ultimoCaracter) != -1) {
            throw new ExcepcionVocal(ultimoCaracter);
        } else if (Character.isDigit(ultimoCaracter)) {
            throw new ExcepcionNumero(ultimoCaracter);
        } else if (ultimoCaracter == ' ') {
            throw new ExcepcionBlanco();
        } else if (ultimoCaracter == '.') {
            throw new ExcepcionSalida();
        } else {
            System.out.println("Carácter normal ingresado: " + ultimoCaracter);
        }
    }

    public static void main(String[] args) {
        ProcesarEntrada procesador = new ProcesarEntrada(System.in);

        while (true) {
            try {
                procesador.procesar();
            } catch (ExcepcionVocal e) {
                System.out.println(e.getMessage());
            } catch (ExcepcionNumero e) {
                System.out.println(e.getMessage());
            } catch (ExcepcionBlanco e) {
                System.out.println(e.getMessage());
            } catch (ExcepcionSalida e) {
                System.out.println(e.getMessage());
                break;
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
                break;
            }
        }
    }
}
