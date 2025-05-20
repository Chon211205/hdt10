import java.util.*;

/**Clase de grafo dirigido con matriz adyacente */
public class Grafo {
    /**Atributos */

    /**Evita que tenga overflow al momento de realizar las sumas.*/
    private final int INF = Integer.MAX_VALUE / 2;
    /** Matriz tridimensional, donde la primera dimensión representa el vértice de origen. el segundo la arista y el tercero el vértice destino. */
    private int[][][] Weights;
    /** Mapa para asociar las ciudades con la matriz por medio de su indice. */
    private Map<String, Integer> CiudadIndice;
    /** Lista para asociar los indices con las ciudades. */
    private List<String> IndiceCiudad;
    /** Guarda el tamano del grafo. */
    private int tamanoGrafo;

    /** Enumeracion de los tipos de climas */
    public enum Clima{
        Templado, Lluvia, Nieve, Tormenta
    }

    /**Constructor */

    public Grafo() {
        this.CiudadIndice = new HashMap<>();
        this.IndiceCiudad = new ArrayList<>();
        this.Weights = new int[100][100][4]; /** 100 es el maximo de ciudades que se pueden tener y 4 climas posibles. */
        this.tamanoGrafo = 0;

        /** Ciclo for para inicializar la matriz adyacente como infinito. */
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                Arrays.fill(Weights[i][j], INF);
    }

    /**Metodos*/

    /**Agregar arista entre dos vertices (ciudades) */
    /**Parametros
     * @param from Ciudad de salida
     * @param to ciudad de llegada
     * @param Templado clima normal
     * @param Lluvia clima de lluvia
     * @param Nieve clima de nieve
     * @param Tormenta clima de tormenta
     */
    public void addArista (String from, String to, int Templado, int Lluvia, int Nieve, int Tormenta) {
        /**Asigna el indice a la ciudad de origen */
        int i = getIndice(from);
        /**Asigna el indice a la ciudad destino */
        int j = getIndice(to);
        /**Asignacion de tiempos*/
        Weights[i][j][0] = Templado;
        Weights[i][j][1] = Lluvia;
        Weights[i][j][2] = Nieve;
        Weights[i][j][3] = Tormenta;
    }

    /** Borrar arista entre dos vertices (ciudades) */
    /**Parametros
     * @param from Ciudad de salida
     * @param to ciudad de destino
     */
    public void removeArista (String from, String to) {
        if (CiudadIndice.containsKey(from) && CiudadIndice.containsKey(to)) {
            int i = CiudadIndice.get(from);
            int j = CiudadIndice.get(to);
            Arrays.fill(Weights[i][j], INF);
        }
    }

    /**Metodo para asignacion de indice a la ciudad */
    /** Parametros
     * @param Ciudad Nombre de la ciudad
     * @return indice de la ciudad
     */
    private int getIndice(String Ciudad){
        /**Evalua si la ciudad esta registrada o no para agregarlo al mapa y a la lista. Incrementando el tamano del grafo */
        if (!CiudadIndice.containsKey(Ciudad)) {
            CiudadIndice.put(Ciudad, tamanoGrafo);
            IndiceCiudad.add(Ciudad);
            tamanoGrafo++;
        }
        /**Devuelve el respectivo indice */
        return CiudadIndice.get(Ciudad);
    }

    /**Devuelve el indice de la ciudad segun el nombre */
    /**Parametros
     * @param ciudad Nombre de la ciudad
     * @return Indice o -1 si la ciudad no existe
     */
    public int getCiudadIndice(String ciudad) {
        return CiudadIndice.getOrDefault(ciudad, -1);
    }

    /**Metodo para obtener la matriz adyacente al clima especificado */
    /**Parametros
     * @param clima Tipo de clima
     * @return Matriz adyacente del tamano actual del grafo
     */
    public int [][] getMatrizAdyacente(Clima clima) {
        /**Crea una matriz */
        int[][] Matriz = new int[tamanoGrafo][tamanoGrafo];
        for (int i = 0; i < tamanoGrafo; i++) {
            for (int j = 0; j < tamanoGrafo; j++) {
                /**Copia los pesos del respectivo clima */
                Matriz[i][j] = Weights[i][j][clima.ordinal()]; 
            }
        }
        /**Devuelve la nueva matriz creada */
        return Matriz;
    }

    /**Devuele el nombre de la ciudad segun su indice */
    /**Parametros
     * @param indice Indice de la ciudad
     * @return Nombre de la ciudad
     */
    public String getCiudad(int indice) {
        return IndiceCiudad.get(indice);
    }

    /**Metodo para obtener el tamano del grafo (numero de ciudades) */
    public int getTamanoGrafo() {
        return tamanoGrafo;
    }
}
