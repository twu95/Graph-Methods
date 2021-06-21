package graph;
import java.util.ArrayList;


/* See restrictions in Graph.java. */

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Thomas Wu
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        if (!_graph2.containsKey(v)) {
            return 0;
        } else {
            return _graph2.get(v).p().size();
        }
    }

    @Override
    public int predecessor(int v, int k) {
        if (!_graph2.containsKey(v)) {
            return 0;
        }
        Vertex vv = _graph2.get(v);
        if (!vv.hasPred() || k >= vv.preSize() || k < 0)  {
            return 0;
        } else {
            return vv.pred(k);
        }
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        Vertex vert = _graph2.get(v);
        ArrayList<Integer> predecessors1;
        if (vert != null) {
            predecessors1 = vert.p();
        } else {
            predecessors1 = new ArrayList<Integer>();
        }
        return Iteration.iteration(predecessors1.iterator());
    }

}
