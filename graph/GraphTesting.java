package graph;

import org.junit.Test;
import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author Thomas Wu
 */
public class GraphTesting {

    @Test
    public void containsTest() {
        DirectedGraph g = new DirectedGraph();
        UndirectedGraph u = new UndirectedGraph();
        g.add();
        g.add();
        u.add();
        u.add();
        g.add(1, 2);
        assertTrue(g.contains(1, 2));
        assertFalse(g.contains(2, 1));
        u.add(1, 2);
        assertTrue(u.contains(1, 2));
        assertTrue(u.contains(2, 1));
    }

    @Test
    public void inOutDegreeTest() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add(2, 3);
        assertEquals(1, g.outDegree(2));
        assertEquals(0, g.outDegree(3));
        assertEquals(1, g.inDegree(3));
        assertEquals(0, g.inDegree(2));
        g.add(2, 3);
        g.add(2, 3);
        g.add(2, 3);
        assertEquals(1, g.outDegree(2));
        assertEquals(0, g.outDegree(3));
        assertEquals(1, g.inDegree(3));
        assertEquals(0, g.inDegree(2));
        g.add(1, 2);
        assertEquals(1, g.outDegree(1));
        assertEquals(1, g.inDegree(2));

        UndirectedGraph u = new UndirectedGraph();
        u.add();
        u.add();
        u.add();
        u.add(2, 3);
        assertEquals(1, u.outDegree(2));
        assertEquals(1, u.inDegree(3));
        assertEquals(1, u.inDegree(2));
        assertEquals(1, u.outDegree(3));
        u.add(2, 3);
        u.add(2, 3);
        u.add(3, 2);
        assertEquals(1, u.outDegree(2));
        assertEquals(1, u.inDegree(3));
        assertEquals(1, u.inDegree(2));
        assertEquals(1, u.outDegree(3));
    }
    @Test
    public void edgeID() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 3);
        g.add(3, 1);
        assertEquals(0, g.edgeId(2, 4));
        assertEquals(0, g.edgeId(1, 2));
        assertTrue(g.edgeId(1, 3) != g.edgeId(3, 1));
    }
    @Test
    public void removeDirected2() {
        System.out.println("Start of important");
        DirectedGraph u = new DirectedGraph();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add(1, 8);
        u.add(1, 1);
        u.add(1, 2);
        u.add(1, 3);
        u.add(1, 4);
        u.add(2, 5);
        u.add(2, 3);
        u.add(2, 6);
        u.add(2, 2);
        u.add(2, 1);
        u.add(3, 2);
        u.add(3, 7);
        u.add(3, 8);
        u.add(3, 1);
        u.add(4, 1);
        u.add(5, 2);
        u.add(6, 2);
        u.add(7, 3);
        u.add(7, 10);
        u.add(8, 3);
        u.add(8, 1);
        u.add(8, 9);
        u.add(8, 8);
        u.add(8, 10);
        u.add(9, 8);
        u.add(10, 8);
        u.add(10, 7);
        u.remove(2);
        assertEquals(4, u.inDegree(1));
        assertEquals(4, u.outDegree(1));

    }
    @Test
    public void removeUndirected2() {
        UndirectedGraph u = new UndirectedGraph();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add(1, 8);
        u.add(1, 1);
        u.add(1, 2);
        u.add(1, 3);
        u.add(1, 4);
        u.add(2, 5);
        u.add(2, 3);
        u.add(2, 6);
        u.add(2, 2);
        u.add(2, 1);
        u.add(3, 2);
        u.add(3, 7);
        u.add(3, 8);
        u.add(3, 1);
        u.add(4, 1);
        u.add(5, 2);
        u.add(6, 2);
        u.add(7, 3);
        u.add(7, 10);
        u.add(8, 3);
        u.add(8, 1);
        u.add(8, 9);
        u.add(8, 8);
        u.add(8, 10);
        u.add(9, 8);
        u.add(10, 8);
        u.add(10, 7);
        u.remove(2);
        assertEquals(4, u.inDegree(1));
        assertEquals(4, u.outDegree(1));

    }
    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void addRemoveGraph() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        assertEquals(1, g.vertexSize());
        assertEquals(1, g.maxVertex());
        g.add();
        assertEquals(2, g.vertexSize());
        g.add();
        assertEquals(3, g.maxVertex());
        g.remove(1);
        assertEquals(3, g.maxVertex());
        assertEquals(2, g.vertexSize());
        g.remove(3);
        assertEquals(2, g.maxVertex());
        assertEquals(1, g.vertexSize());
        g.add();
        g.add();
        g.add(1, 3);
        g.add(1, 2);
        g.add(2, 3);
        g.add(2, 1);
        g.add(3, 1);
        g.add(3, 2);
        assertEquals(2, g.inDegree(2));
        assertEquals(2, g.outDegree(2));
        assertEquals(2, g.inDegree(3));
        assertEquals(2, g.outDegree(2));
        g.remove(2);
        assertEquals(1, g.inDegree(1));
        assertEquals(1, g.inDegree(3));
        assertEquals(1, g.outDegree(1));
        assertEquals(1, g.outDegree(3));
        assertTrue(!g.contains(2, 1));
        assertFalse(g.contains(2, 3));
    }
    @Test
    public void removeUndirected() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(2, 1);
        g.add(2, 2);
        g.add(2, 3);
        g.add(2, 4);
        g.add(2, 5);
        g.add(2, 6);
        g.add(3, 5);
        g.add(1, 5);
        assertEquals(8, g.edgeSize());
        g.remove(1, 5);
        assertEquals(7, g.edgeSize());
        g.remove(2);
        assertEquals(1, g.edgeSize());
    }

    @Test
    public void removeDirected() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(2, 1);
        g.add(1, 2);
        g.add(3, 2);
        g.add(2, 3);
        g.add(2, 4);
        g.add(4, 2);
        g.add(2, 5);
        g.add(5, 2);
        g.add(6, 2);
        g.add(2, 6);
        g.add(3, 5);
        g.add(2, 2);
        g.add(1, 5);
        assertEquals(13, g.edgeSize());
        g.remove(1, 5);
        assertEquals(12, g.edgeSize());
        g.remove(2);
        assertEquals(1, g.edgeSize());
    }

    @Test
    public void edgeCaseStuff() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 2);
        g.add(2, 1);
        g.add(2, 1);
        g.add(1, 1);
        g.add(1, 1);
        g.add(1, 1);
        g.add(2, 3);
        assertEquals(4, g.edgeSize());
        assertEquals(2, g.inDegree(1));
        assertEquals(2, g.outDegree(1));
        assertEquals(1, g.inDegree(2));
        assertEquals(2, g.outDegree(2));
        g.remove(1);
        assertEquals(0, g.inDegree(1));
        assertEquals(0, g.outDegree(1));
        assertEquals(0, g.inDegree(2));
        assertEquals(1, g.outDegree(2));
        assertEquals(1, g.inDegree(3));
        assertEquals(0, g.outDegree(3));
        assertEquals(1, g.edgeSize());

        UndirectedGraph u = new UndirectedGraph();
        u.add();
        u.add();
        u.add();
        u.add(1, 2);
        u.add(2, 1);
        u.add(1, 1);
        assertEquals(2, u.edgeSize());
        assertEquals(2, u.inDegree(1));
        assertEquals(2, u.outDegree(1));
        assertEquals(1, u.inDegree(2));
        assertEquals(1, u.outDegree(2));
        u.add(1, 1);
        assertEquals(2, u.inDegree(1));
        assertEquals(2, u.outDegree(1));
        assertEquals(1, u.inDegree(2));
        assertEquals(1, u.outDegree(2));
        u.add(2, 3);
        u.add(1, 1);
        u.remove(1, 1);
        assertEquals(1, u.inDegree(1));
        assertEquals(1, u.outDegree(1));
        assertEquals(2, u.inDegree(2));
        assertEquals(2, u.outDegree(2));
        assertEquals(1, u.inDegree(3));
        assertEquals(1, u.outDegree(3));

    }

    @Test
    public void predSuccessors() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        assertEquals(2, g.successor(1, 0));
        assertEquals(1, g.predecessor(2, 0));
        g.add(2, 3);
        assertEquals(3, g.successor(2, 0));
        assertEquals(0, g.successor(2, 99));
        assertEquals(2, g.predecessor(3, 0));
        assertEquals(0, g.predecessor(3, 99));
        g.remove(2, 3);
        assertEquals(0, g.successor(2, 0));
        assertEquals(2, g.successor(1, 0));
        assertEquals(0, g.predecessor(3, 0));
        g.add(2, 3);
        assertEquals(3, g.successor(2, 0));
        assertEquals(2, g.predecessor(3, 0));
        g.add();
        g.add(2, 4);
        assertEquals(3, g.successor(2, 0));
        assertEquals(4, g.successor(2, 1));
        assertEquals(2, g.predecessor(4, 0));
        assertEquals(2, g.predecessor(3, 0));
        g.add(4, 2);
        assertEquals(2, g.successor(4, 0));
        assertEquals(2, g.predecessor(4, 0));
        assertEquals(4, g.successor(2, 1));
        assertEquals(3, g.successor(2, 0));
        g.add(2, 4);
        g.add(2, 4);
        g.add(2, 99);
        assertEquals(3, g.successor(2, 0));
        assertEquals(4, g.successor(2, 1));
        assertEquals(0, g.successor(2, 2));
        assertEquals(0, g.successor(2, 3));
        g.remove(2, 3);
        assertEquals(0, g.predecessor(3, 0));
        assertEquals(0, g.successor(3, 0));
        assertEquals(4, g.successor(2, 0));
        assertEquals(0, g.successor(2, 1));
        g.remove(2);
        assertEquals(0, g.successor(1, 0));
        assertEquals(0, g.successor(4, 0));
        assertEquals(0, g.predecessor(3, 0));
        assertEquals(0, g.predecessor(4, 0));
    }
    @Test public void testOthers() {
        DirectedGraph f = new DirectedGraph();
        f.add();
        f.add();
        f.add();
        f.add();
        f.add();
        f.add(2, 1);
        f.add(3, 1);
        f.add(4, 1);
        f.add(5, 1);
        assertEquals(0, f.predecessor(2, 0));

        UndirectedGraph h = new UndirectedGraph();
        h.add();
        h.add();
        h.add(1, 2);
        h.add(2, 1);
        h.add(1, 2);
        h.add(2, 1);
        assertEquals(1, h.predecessor(2, 0));
        assertEquals(1, h.successor(2, 0));
        assertEquals(2, h.predecessor(1, 0));
        assertEquals(2, h.successor(1, 0));
    }


}
