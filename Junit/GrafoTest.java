import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GrafoTest {
    private Grafo grafo;

    @Before
    public void setUp() {
        grafo = new Grafo();
    }

    @Test
    public void testAgregarNodo() {
        assertEquals(0, grafo.getTamanoGrafo());
        grafo.getIndice("CiudadA");
        assertEquals(1, grafo.getTamanoGrafo());
        assertEquals(0, grafo.getCiudadIndice("CiudadA"));
    }

    @Test
    public void testAgregarArista() {
        grafo.addArista("CiudadA", "CiudadB", 1, 2, 3, 4);
        
        int[][] matrizTemplado = grafo.getMatrizAdyacente(Grafo.Clima.Templado);
        int i = grafo.getCiudadIndice("CiudadA");
        int j = grafo.getCiudadIndice("CiudadB");
        
        assertEquals(1, matrizTemplado[i][j]);
    }

    @Test
    public void testEliminarArista() {
        grafo.addArista("CiudadA", "CiudadB", 1, 2, 3, 4);
        grafo.removeArista("CiudadA", "CiudadB");
        
        int[][] matrizTemplado = grafo.getMatrizAdyacente(Grafo.Clima.Templado);
        int i = grafo.getCiudadIndice("CiudadA");
        int j = grafo.getCiudadIndice("CiudadB");
        
        assertEquals(Grafo.INF, matrizTemplado[i][j]);
    }

    @Test
    public void testMatrizAdyacentePorClima() {
        grafo.addArista("CiudadA", "CiudadB", 1, 2, 3, 4);
        
        int i = grafo.getCiudadIndice("CiudadA");
        int j = grafo.getCiudadIndice("CiudadB");
        
        assertEquals(1, grafo.getMatrizAdyacente(Grafo.Clima.Templado)[i][j]);
        assertEquals(2, grafo.getMatrizAdyacente(Grafo.Clima.Lluvia)[i][j]);
        assertEquals(3, grafo.getMatrizAdyacente(Grafo.Clima.Nieve)[i][j]);
        assertEquals(4, grafo.getMatrizAdyacente(Grafo.Clima.Tormenta)[i][j]);
    }
}