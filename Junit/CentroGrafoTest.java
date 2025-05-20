import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CentroGrafoTest {
    private Grafo grafo;
    private Floyd floyd;

    @Before
    public void setUp() {
        grafo = new Grafo();
        // Crear un grafo con centro conocido
        grafo.addArista("A", "B", 1, 1, 1, 1);
        grafo.addArista("B", "C", 1, 1, 1, 1);
        grafo.addArista("B", "D", 1, 1, 1, 1);
        grafo.addArista("C", "A", 1, 1, 1, 1);
        grafo.addArista("D", "A", 1, 1, 1, 1);
        
        int[][] matriz = grafo.getMatrizAdyacente(Grafo.Clima.Templado);
        floyd = new Floyd(matriz);
    }

    @Test
    public void testCentroGrafo() {
        int centro = CentroGrafo.BuscarCentroGrafo(floyd.getDistancia());
        assertEquals("B", grafo.getCiudad(centro));
    }

    @Test
    public void testGrafoDesconectado() {
        grafo = new Grafo();
        grafo.addArista("A", "B", 1, 1, 1, 1);
        grafo.addArista("C", "D", 1, 1, 1, 1);
        
        int[][] matriz = grafo.getMatrizAdyacente(Grafo.Clima.Templado);
        floyd = new Floyd(matriz);
        
        int centro = CentroGrafo.BuscarCentroGrafo(floyd.getDistancia());
        assertEquals(-1, centro);
    }
}