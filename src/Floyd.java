public class Floyd {
    /** Atributos */

    /**Matriz de distancia corta */
    private int[][] distancia;
    /**Matriz para reconstruir caminos */
    private int[][] next;
    /**Tamano del grafo */
    private int n;

    /**Constructor */

    /**@param adyacente matriz adyacente del grafo */
    public Floyd (int[][] adyacente) {
        this.n = adyacente.length;
        this.distancia = new int[n][n];
        this.next = new int[n][n];

        /**Inicializa la matriz */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                /**Copia la distancia desde la matriz adyacente */
                distancia[i][j] = adyacente[i][j];
                if (i != j && adyacente[i][j] < Integer.MAX_VALUE / 2) {
                    /**inicializa el nodo en el camino de i a j */
                    next[i][j] = j;
                } else {
                    /**No hay camino directo */
                    next[i][j] = -1;
                }
            }
        }

        /**Algoritmo Floyd-Warshall */
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    /**Trata de buscar el camino mas corto */
                    if (distancia[i][k] + distancia[k][j] < distancia[i][j]) {
                        /**Actualiza la distancia para que sea mas corto */
                        distancia[i][j] = distancia[i][k] + distancia[k][j];
                        /**Actualiza el nodo en el camino */
                        next[i][j] = next[i][k];              
                    }
                }
            }
        }
    }

    /**Metodos */

    /**Metodo para mostrar la distancia mas corta entre cada par de nodos */
    public int[][] getDistancia() {
        return distancia;
    }

    /**Metodo para que muestre el camino mas corto entre nodos com ouna lista */
    /** Parametros
     * @param origen indice de la ciudad de origen
     * @param destino indice de la ciudad de destino
     * @return Devuelve un arreglo de los caminos
     */
    public int[] getCamino(int origen, int destino) {
        /**Cuando no encuentra caminos entre el origen y el destino */
        if (next[origen][destino] == -1) return new int[0];

        java.util.List<Integer> Camino = new java.util.ArrayList<>();
        /**Agrega el nodo de origen al camino */
        Camino.add(origen);
        while (origen != destino) {
            /**Avanza al siguiente nodo */
            origen = next[origen][destino];
            /**Agrega el nodo al camino */
            Camino.add(origen);
        }
        /**Convierte la lista a un arreglo para luego mostrar el resultado */
        return Camino.stream().mapToInt(i -> i).toArray();
    }
}
