import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FloydTest {
    private Grafo grafo;
    private Floyd floyd;

    @Before
    public void setUp() {
        grafo = new Grafo();
        // Crear un grafo simple para pruebas
        grafo.addArista("A", "B", 1, 1, 1, 1);
        grafo.addArista("B", "C", 1, 1, 1, 1);
        grafo.addArista("A", "C", 3, 3, 3, 3);
        
        int[][] matriz = grafo.getMatrizAdyacente(Grafo.Clima.Templado);
        floyd = new Floyd(matriz);
    }

    @Test
    public void testDistanciasMinimas() {
        int[][] distancias = floyd.getDistancia();
        int a = grafo.getCiudadIndice("A");
        int b = grafo.getCiudadIndice("B");
        int c = grafo.getCiudadIndice("C");
        
        assertEquals(0, distancias[a][a]);
        assertEquals(1, distancias[a][b]);
        assertEquals(2, distancias[a][c]); 
    }

    @Test
    public void testCaminoMasCorto() {
        int a = grafo.getCiudadIndice("A");
        int c = grafo.getCiudadIndice("C");
        
        int[] camino = floyd.getCamino(a, c);
        
        assertEquals(3, camino.length);
        assertEquals("A", grafo.getCiudad(camino[0]));
        assertEquals("B", grafo.getCiudad(camino[1]));
        assertEquals("C", grafo.getCiudad(camino[2]));
    }

    @Test
    public void testCaminoInexistente() {
        grafo = new Grafo();
        grafo.addArista("A", "B", 1, 1, 1, 1);
        grafo.addArista("C", "D", 1, 1, 1, 1);
        
        int[][] matriz = grafo.getMatrizAdyacente(Grafo.Clima.Templado);
        floyd = new Floyd(matriz);
        
        int a = grafo.getCiudadIndice("A");
        int d = grafo.getCiudadIndice("D");
        
        int[] camino = floyd.getCamino(a, d);
        assertEquals(0, camino.length);
    }
}