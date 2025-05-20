import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        /**Instancia del scanner */
        Scanner scanner = new Scanner(System.in);
        /**Instancia de la clase Grafo */
        Grafo grafo = new Grafo();

        /**Lee el archivo .txt con la clase parser */
        try {
            /**Carga los datos del archivo .txt */
            Parser.CargarGrafoArchivo("logistica.txt", grafo);
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo, " + e.getMessage());
            return;
        }

        /**Establece el clima por defecto como Templado */
        Grafo.Clima ClimaEstablecido = Grafo.Clima.Templado;
        /**Ejecuta algoritmo Floyd con el clima establecido */
        Floyd floyd = new Floyd(grafo.getMatrizAdyacente(ClimaEstablecido));

        /**Interfaz del usuario */

        String x = "no";
        while (x.equalsIgnoreCase("no")) {
            System.out.println("---Grafos entre ciudades---");
            System.out.println("1. Verificar el camino mas corto entre dos ciudades");
            System.out.println("2. Consultar el centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Salir");
            System.out.println("Ingrese la opcion que desee: ");

            int Op = scanner.nextInt();
            scanner.nextLine();

            switch (Op) {
                /**Verificar si hay caminos cortos entre dos ciudades */
                case 1:
                    System.out.println("Ingrese la ciudad de origen: ");
                    String Origen = scanner.next();
                    System.out.println("Ingrese la ciudad de destino: ");
                    String Destino = scanner.next();

                    int i = grafo.getCiudadIndice(Origen);
                    int j = grafo.getCiudadIndice(Destino);

                    if (i == -1 || j == -1) {
                        System.out.println("Mas de una ciudad ingresada no existe");

                        System.out.println("Desea salir del programa? ");
                        x = scanner.next();
                        break;
                    }

                    /**Obtiene el camino mas corto */
                    int[] Ruta = floyd.getCamino(i, j);
                    if (Ruta.length == 0) {
                        System.out.println("No hay caminos entre las ciudades");
                    } else {
                        System.out.println("El camino mas corto es: ");
                        for (int idx = 0; idx < Ruta.length; idx++) {
                            System.out.println(grafo.getCiudad(Ruta[idx]));
                            if (idx < Ruta.length - 1) System.out.println(" ->");
                        }
                        System.out.println("Tiempo de vuelo: " + floyd.getDistancia()[i][j] + " horas");
                    }
                    
                    System.out.println("Desea salir del programa? ");
                    x = scanner.next();
                    break;

                /**Consultar el centro del grafo */
                case 2:
                    int Centro = CentroGrafo.BuscarCentroGrafo(floyd.getDistancia());
                    if (Centro ==-1) {
                        System.out.println("No se puede determinar el centro del grafo, no hay conexion");
                    } else {
                        System.out.println ("El centro del grafo es: " + grafo.getCiudad(Centro));                  
                    }

                    System.out.println("Desea salir del programa? ");
                    x = scanner.next();
                    break;
                
                /**Modificar grafo */
                case 3:
                    /**Menu de modificaciones del grafo */
                    System.out.println("---Modificaciones del grafo---");
                    System.out.println("1. Interrupccion del trafico");
                    System.out.println("2. Crear nueva conexion");
                    System.out.println("3. Cambio del clima");
                    System.out.println("Ingrese la opcion que desee: ");
                    int Op2 = scanner.nextInt();

                    switch (Op2) {
                        /**Interrupcion del trafico */
                        case 1:
                            System.out.println("Ingrese la ciudad de origen: ");
                            String from = scanner.next();
                            System.out.println("Ingrese la ciudad de destino: ");
                            String to = scanner.next();
                            /** Elimina la arista entre vertices (ruta) */
                            grafo.removeArista(from, to);
                            break;
                        
                        /**Crear nueva conexion */
                        case 2:
                            /**Ingreso de los datos de la nueva ruta */
                            System.out.println("Ingrese la ciudad de origen: ");
                            from = scanner.next();
                            System.out.println("Ingrese la ciudad de destino: ");
                            to = scanner.next();
                            System.out.println("Clima Templado: ");
                            int C1 = scanner.nextInt();
                            System.out.println("Clima Lluvia: ");
                            int C2 = scanner.nextInt();
                            System.out.println("Clima Nieve: ");
                            int C3 = scanner.nextInt();
                            System.out.println("Clima Tormenta: ");
                            int C4 = scanner.nextInt();

                            /**Agrega la nueva ruta en base a los nuevos datos */
                            grafo.addArista(from, to, C1, C2, C3, C4);
                            break;
                        
                        /**Cambio del clima */
                        case 3:
                            System.out.println("Seleccione el clima a cambiar (Templado, LLuvia, Nieve, Tormenta): ");
                            String Clima = scanner.nextLine().toLowerCase();
                            switch (Clima) {
                                case "Templado": 
                                    ClimaEstablecido = Grafo.Clima.Templado; 
                                    break;
                                case "Lluvia":
                                    ClimaEstablecido = Grafo.Clima.Lluvia;
                                    break;
                                case "Nieve":
                                    ClimaEstablecido = Grafo.Clima.Nieve;
                                    break;
                                case "Tormenta":
                                    ClimaEstablecido = Grafo.Clima.Tormenta;
                                    break;

                                default:
                                    System.out.println("Clima no encontrado");
                            }
                            break;
                        default:
                            System.out.println("Opcion no valida");         
                    }
                    floyd = new Floyd(grafo.getMatrizAdyacente(ClimaEstablecido));

                    System.out.println("Desea salir del programa? ");
                    x = scanner.next();
                    break;

                case 4:
                    System.out.println("Desea salir del programa? ");
                    x = scanner.next();
                    break;
                    
                
            default:
                    System.out.println("No existe la opcion");
            }
        }    
    }
}
