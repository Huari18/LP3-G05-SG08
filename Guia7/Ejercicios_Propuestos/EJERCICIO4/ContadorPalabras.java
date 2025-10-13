package Guia7.Ejercicios_Propuestos.EJERCICIO4;

import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;

public class ContadorPalabras {

    public static void main(String[] args) {
        File archivo = seleccionarArchivo();

        if (archivo == null) {
            System.out.println("No se seleccionó ningún archivo. Saliendo...");
            return;
        }

        procesarArchivo(archivo);
    }

    // Método para seleccionar archivo con JFileChooser
    private static File seleccionarArchivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccione un archivo de texto (.txt)");
        int opcion = chooser.showOpenDialog(null);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            if (archivo.exists() && archivo.isFile()) {
                return archivo;
            } else {
                System.out.println("El archivo no existe o no es válido.");
            }
        }
        return null;
    }

    // Procesa el archivo seleccionado
    private static void procesarArchivo(File archivo) {
        int totalLineas = 0;
        int totalPalabras = 0;
        int totalCaracteres = 0;
        Map<String, Integer> frecuenciaPalabras = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                totalLineas++;

                // Contar caracteres (sin el salto de línea)
                totalCaracteres += linea.length();

                // Separar palabras por cualquier caracter no letra o dígito
                StringBuilder palabra = new StringBuilder();
                for (char c : linea.toCharArray()) {
                    if (Character.isLetterOrDigit(c)) {
                        palabra.append(Character.toLowerCase(c));
                    } else if (palabra.length() > 0) {
                        String p = palabra.toString();
                        totalPalabras++;
                        frecuenciaPalabras.put(p, frecuenciaPalabras.getOrDefault(p, 0) + 1);
                        palabra.setLength(0);
                    }
                }
                // Última palabra de la línea
                if (palabra.length() > 0) {
                    String p = palabra.toString();
                    totalPalabras++;
                    frecuenciaPalabras.put(p, frecuenciaPalabras.getOrDefault(p, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Calcular promedio
        double promedioPalabras = totalLineas > 0 ? (double) totalPalabras / totalLineas : 0;

        // Mostrar resultados
        System.out.println("\n=== RESULTADOS DEL ARCHIVO ===");
        System.out.println("Archivo: " + archivo.getName());
        System.out.println("Total de líneas: " + totalLineas);
        System.out.println("Total de palabras: " + totalPalabras);
        System.out.println("Total de caracteres: " + totalCaracteres);
        System.out.printf("Promedio de palabras por línea: %.2f%n", promedioPalabras);

        // Palabras más frecuentes
        System.out.println("\nPalabras más frecuentes:");
        mostrarPalabrasFrecuentes(frecuenciaPalabras, 5);
    }

    // Muestra las N palabras más frecuentes
    private static void mostrarPalabrasFrecuentes(Map<String, Integer> frecuencia, int n) {
        frecuencia.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .forEach(e -> System.out.printf("%-15s : %d%n", e.getKey(), e.getValue()));
    }
}
