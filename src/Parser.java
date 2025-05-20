
import java.io.*;

public class Parser {

    /**Parametros
     * @param filename nombre del archivo
     * @param grafo objeto donde se agregan conexiones
     * @throws IOException para ver si hay error al leer codigo
     */

    public static void CargarGrafoArchivo (String filename, Grafo grafo) throws IOException {
        /**Lector del archivo */
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        /**Lee cada linea de archivo */
        while ((line = reader.readLine()) != null) {
            /**Divide la linea por espacios */
            String[] parts = line.trim().split(" ");
            /**Comprueba que tenga 6 partes */
            if (parts.length != 6) continue;

            /**Ciudad origen */
            String from = parts[0];
            /**Ciudad destino */
            String to = parts[1];
            /**Tiempo Templado */
            int Templado = Integer.parseInt(parts[2]);
            /**Tiempo Lluvida */
            int Lluvia = Integer.parseInt(parts[3]);
            /**Tiempo Nieve */
            int Nieve = Integer.parseInt(parts[4]);
            /**Tiempo Tormenta */
            int Tormenta = Integer.parseInt(parts[5]);

            grafo.addArista(from, to, Templado, Lluvia, Nieve, Tormenta);
        }
        reader.close();
    }
    
}
