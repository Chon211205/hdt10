public class CentroGrafo {
    /**Calcula el centro del grafo (nodo con menor excentricidad, maxima distancia minima a cualquier nodo) */
    /** Parametros
     * @param distancia matriz de distnacia minima entre todos los pares de nodos
     * @return devuelve el indice del nodo que representa el centro del grafo
     */
    public static int BuscarCentroGrafo(int[][] distancia) {
        /**Cantidad de nodos en el grafo */
        int n = distancia.length;
        /**Inicia la menor excentricidad como infinita */
        int ExcentricidadMin = Integer.MAX_VALUE;
        /**Nodo identificado como centro */
        int NodoCentral = -1;

        /**Itera sobre cada nodo para calcular la excentricidad */
        for (int i = 0; i < n; i++) {
            /**Excentricidad inicial */
            int Excentricidad = 0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    /**Agarra la distancia maxima o lejana */
                    Excentricidad = Math.max(Excentricidad, distancia[i][j]);
                }
            }
            /**Comprueba si se encontro la menor excentricidad */
            if (Excentricidad < ExcentricidadMin) {
                /**Actualiza la excentricidad menor */
                ExcentricidadMin = Excentricidad;
                /**Actualiza el nodo central */
                NodoCentral = i;
            }
        }
        return NodoCentral;
    }
}
