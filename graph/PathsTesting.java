package graph;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.TreeMap;


public class PathsTesting {
    class VideoGraphPaths extends SimpleShortestPaths {
        /** THe key can be the edge ID, and the value will be the weight! */
        private TreeMap<Integer, Double> _w;
        private ArrayList<Integer> _order;


        public VideoGraphPaths(Graph g, int source, int dest) {
            super(g, source, dest);
            _order = new ArrayList<Integer>();
            _w = new TreeMap<Integer, Double>();
        }

        @Override
        public double getWeight(int u, int v) {
            _order.add(u);
            if (!_G.contains(v) || !_G.contains(u) || !_G.contains(u, v)) {
                return Double.POSITIVE_INFINITY;
            }
            if (!_w.containsKey(_G.edgeId(u, v))) {
                return 0.0;
            }
            return _w.get(_G.edgeId(u, v));
        }

        public void setWeight(int u, int v, double weight) {
            _w.put(_G.edgeId(u, v), weight);
        }

        public boolean checkContainment(int v) {
            return _order.contains(v);
        }

        @Override
        protected double estimatedDistance(int v) {
            if (v == 4) {
                return 102.0;
            }
            if (v == 2) {
                return 4.0;
            }
            if (v == 5) {
                return 5.1;
            }
            if (v == 6) {
                return 40.0;
            }
            if (v == 8) {
                return 100000;
            }
            int dst = getDest();
            if (dst == 0 || v == dst) {
                return 0;
            }
            if (_G.contains(v, dst)) {
                return _w.get(_G.edgeId(v, dst));
            }
            return 0.0;
        }
    }

    @Test
    public void testWeights() {
        Graph videoGraph = new DirectedGraph();
        videoGraph.add();
        videoGraph.add();
        videoGraph.add();
        videoGraph.add();
        videoGraph.add();
        videoGraph.add();
        videoGraph.add();
        videoGraph.add();
        videoGraph.add();
        videoGraph.add(4, 2);
        videoGraph.add(4, 5);
        videoGraph.add(4, 3);
        videoGraph.add(2, 3);
        videoGraph.add(5, 6);
        videoGraph.add(5, 3);
        videoGraph.add(6, 7);
        videoGraph.add(5, 8);
        videoGraph.add(8, 9);
        VideoGraphPaths vgp = new VideoGraphPaths(videoGraph, 4, 3);
        vgp.setWeight(4, 2, 12.2);
        vgp.setWeight(4, 5, 11.2);
        vgp.setWeight(4, 3, 102.0);
        vgp.setWeight(2, 3, 6.5);
        vgp.setWeight(5, 6, 30.0);
        vgp.setWeight(5, 3, 9.1);
        vgp.setWeight(6, 7, 1.0);
        vgp.setWeight(5, 8, 1.0);
        vgp.setPaths();
        List<Integer> fourThree = asList(4, 2, 3);
        List<Integer> list = vgp.pathTo(3);
        assertEquals(4, vgp.getSource());
        assertEquals(3, vgp.getDest());
        assertEquals(3, list.size());
        assertEquals(4, vgp.getPredecessor(5));
        assertEquals(fourThree, list);
        assertTrue(vgp.checkContainment(5));
        assertEquals(0, vgp.getPredecessor(7));
        assertTrue(vgp.getPredecessor(6) != 0);
        assertEquals(0, vgp.getPredecessor(9));
    }

}
