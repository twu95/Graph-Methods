package graph;
import java.util.TreeMap;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import java.util.HashSet;
/* See restrictions in Graph.java. */

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Thomas Wu
 */
abstract class GraphObj extends Graph {

    /** Choose to store our data as a dictionary with
      * a graph arraylist of edges. */
    TreeMap<Integer, ArrayList<Edge>> _graph;

    /** A secondary storage such that it has the Vertex, which has
      * predecessors and Successors.... */
    TreeMap<Integer, Vertex> _graph2;

    /** The total number of distinct edges in me. */
    private int _edgeSize = 0;


    /** A new, empty Graph. */
    GraphObj() {
        _graph = new TreeMap<Integer, ArrayList<Edge>>();
        _graph2 = new TreeMap<Integer, Vertex>();
    }

    /** Class VERTEX containing predecessors, succesors, and its value. */
    class Vertex {
        /** The _VAL is the value of the Vertex. */
        private int _val;
        /** The arraylist _PRE of predecessors. */
        private ArrayList<Integer> _pre;
        /** The arraylist _SUCC of successors. */
        private ArrayList<Integer> _succ;

        /** Constructor, taking in the value INT VAL. */
        Vertex(int val) {
            _val = val;
            _pre = new ArrayList<Integer>();
            _succ = new ArrayList<Integer>();
        }

        /** Returns whether there is an edge from THIS to OTHER. */
        boolean hasEdgeTo(int other) {
            if (_succ.indexOf(other) != -1) {
                return true;
            }
            return false;
        }

        /** Returns how many predecessors. */
        int preSize() {
            return _pre.size();
        }

        /** Returns how many successors. */
        int succSize() {
            return _succ.size();
        }

        /** Adds a predecessor with value VAL. */
        void putPred(int val) {
            _pre.add(val);
        }

        /** Adds a successor with value VAL. */
        void putSucc(int val) {
            _succ.add(val);
        }

        /** Returns the BOOLEAN of wehtehr there is at least one
          * predecessor to this vertex. */
        boolean hasPred() {
            return !_pre.isEmpty();
        }

        /** Returns the BOOLEAN of whether there is at least one
          * successor to this vertex. */
        boolean hasSucc() {
            return !_succ.isEmpty();
        }

        /** Returns the predecessor at index INDEX. */
        int pred(int index) {
            return _pre.get(index);
        }

        /** Returns the successor at index INDX. */
        int succ(int indx) {
            return _succ.get(indx);
        }

        /** Returns the value of the vertex. */
        int getVal() {
            return _val;
        }

        /** Removes the predecessor with value VAL. */
        void removePred(int val) {
            int indx = _pre.indexOf(val);
            if (indx != -1) {
                _pre.remove(indx);
            }
        }
        /** Removes the successor with value VAL. */
        void removeSucc(int val) {
            int indx = _succ.indexOf(val);
            if (indx != -1) {
                _succ.remove(indx);
            }
        }
        /** Just returns the list of predecessors. */
        ArrayList<Integer> p() {
            return _pre;
        }

        /** Returns the list of successors. */
        ArrayList<Integer> s() {
            return _succ;
        }

    }

    /** The edge class. I made this first, and it's really kind of
      * redundant with the Vertex class, but it is used in some of
      * the methods. */
    class Edge {
        /** _V0 and _V1 are the values of the 2 vertexes that connect to. */
        private int _v0, _v1;
        /** Not sure if we need this yet, but the weight of the edge. */
        private int _weight;
        /** Constructor taking in INT V0, vertex0, INT V1, vertex 1, and
          * INT WEIGHT, the weight of the edge. */
        Edge(int v0, int v1) {
            _v0 = v0;
            _v1 = v1;
        }
        /** Returns BOOLEAN for whether there exists an edge between V0
          * and V1, vertices. */
        private boolean isBetween(int v0, int v1) {
            if (_v0 == v0 && _v1 == v1) {
                return true;
            }
            if (_v0 == v1 && _v1 == v0) {
                return true;
            }
            return false;
        }
        /** Returns an INT V0. */
        private int getV0() {
            return _v0;
        }
        /** Returns an INT V1. */
        private int getV1() {
            return _v1;
        }
    }

    @Override
    public int vertexSize() {
        return _graph2.size();
    }


    @Override
    public int maxVertex() {
        if (_graph2.isEmpty()) {
            return 0;
        }
        return Collections.max(_graph2.keySet());
    }


    @Override
    public int edgeSize() {
        int edgecount = 0;
        if (_graph.isEmpty()) {
            return 0;
        }
        for (int[] i : edges()) {
            edgecount += 1;
        }
        return edgecount;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (!_graph2.containsKey(v)) {
            return 0;
        } else {
            return _graph2.get(v).s().size();
        }
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return _graph2.containsKey(u);
    }

    @Override
    public boolean contains(int u, int v) {
        if (_graph2.containsKey(u) && _graph2.containsKey(v)) {
            ArrayList<Integer> succ = _graph2.get(u).s();
            if (succ == null) {
                return false;
            }
            if (succ.contains(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int add() {
        int max = maxVertex();
        if (max == 0) {
            _graph.put(1, null);
            _graph2.put(1, new Vertex(1));
            return 1;
        }
        for (int i = 1; i <= max; i++) {
            if (!_graph2.containsKey(i)) {
                _graph.put(i, null);
                _graph2.put(i, new Vertex(i));
                return i;
            }
        }
        _graph.put(max + 1, null);
        _graph2.put(max + 1, new Vertex(max + 1));
        return max + 1;
    }

    @Override
    public int add(int u, int v) {

        if (!_graph2.containsKey(u) || !_graph2.containsKey(v)) {
            return u;
        }


        if (!isDirected() && (contains(u, v) && contains(v, u))) {
            return u;
        }

        if (u == v) {
            ArrayList<Edge> selfU = _graph.remove(u);
            if (selfU == null) {
                selfU = new ArrayList<Edge>();
            }
            selfU.add(new Edge(u, v));
            _graph.put(u, selfU);
        } else {
            ArrayList<Edge> currU = _graph.remove(u);
            ArrayList<Edge> currV = _graph.remove(v);
            if (currU == null) {
                currU = new ArrayList<Edge>();
            }
            if (currV == null) {
                currV = new ArrayList<Edge>();
            }
            currU.add(new Edge(u, v));
            currV.add(new Edge(u, v));
            _graph.put(u, currU);
            _graph.put(v, currV);
        }
        Vertex uu = _graph2.remove(u);
        if (uu.hasEdgeTo(v)) {
            _graph2.put(u, uu);
            return u;
        }

        Vertex vv = _graph2.remove(v);
        if (u == v) {
            uu.putSucc(u);
            uu.putPred(u);
            _graph2.put(u, uu);
            return u;
        }
        uu.putSucc(v);
        vv.putPred(u);
        if (!isDirected() && u != v) {
            vv.putSucc(u);
            uu.putPred(v);
        }
        _graph2.put(u, uu);
        _graph2.put(v, vv);
        return u;
    }

    @Override
    public void remove(int v) {
        if (_graph.containsKey(v)) {
            ArrayList<Edge> toDelete = _graph.remove(v);
            HashSet<Integer> toCheckForDelete = new HashSet<Integer>();
            if (toDelete == null) {
                _graph2.remove(v);
                return;
            }
            for (Edge e : toDelete) {
                toCheckForDelete.add(e.getV1());
                toCheckForDelete.add(e.getV0());
            }
            if (toCheckForDelete != null) {
                for (int toMoreDelete : toCheckForDelete) {
                    ArrayList<Edge> temp = _graph.remove(toMoreDelete);
                    if (temp == null) {
                        continue;
                    }
                    Iterator<Edge> iter = temp.iterator();
                    while (iter.hasNext()) {
                        Edge f = iter.next();
                        if (f.getV1() == v || f.getV0() == v) {
                            iter.remove();
                        }
                    }
                    _graph.put(toMoreDelete, temp);
                }
            }
            _graph.remove(v);
        }
        if (_graph2.containsKey(v)) {
            Vertex vv = _graph2.remove(v);
            if (vv.hasSucc()) {
                for (int succVal : vv.s()) {
                    Vertex succVert = _graph2.remove(succVal);
                    if (succVert != null) {
                        succVert.removePred(v);
                        _graph2.put(succVal, succVert);
                    }
                }
            }
            if (vv.hasPred()) {
                for (int j = 0; j < vv.preSize(); j++) {
                    int preVal = vv.pred(j);
                    Vertex predVert = _graph2.remove(preVal);
                    if (predVert != null) {
                        predVert.removeSucc(v);
                        _graph2.put(preVal, predVert);
                    }
                }
            }
            _graph2.remove(v);
        }
    }

    @Override
    public void remove(int u, int v) {
        if (contains(u, v)) {
            if (u == v) {
                ArrayList<Edge> selfU = _graph.remove(u);
                ArrayList<Edge> newSelfU = new ArrayList<Edge>();
                for (Edge d : selfU) {
                    if (!d.isBetween(u, v)) {
                        newSelfU.add(d);
                    }
                }
                _graph.put(u, newSelfU);
            } else {
                ArrayList<Edge> currU = _graph.remove(u);
                ArrayList<Edge> currV = _graph.remove(v);
                ArrayList<Edge> newU = new ArrayList<Edge>();
                ArrayList<Edge> newV = new ArrayList<Edge>();
                for (Edge e : currU) {
                    if (!e.isBetween(u, v)) {
                        newU.add(e);
                    }
                }
                if (currV != null) {
                    for (Edge f : currV) {
                        if (!f.isBetween(u, v)) {
                            newV.add(f);
                        }
                    }
                }
                _graph.put(u, newU);
                _graph.put(v, newV);
            }
            Vertex uu = _graph2.remove(u);
            if (u == v) {
                uu.removeSucc(u);
                uu.removePred(u);
                _graph2.put(u, uu);
            } else {
                Vertex vv = _graph2.remove(v);
                uu.removeSucc(v);
                vv.removePred(u);

                if (!isDirected()) {
                    vv.removeSucc(u);
                    uu.removePred(v);
                }
                _graph2.put(u, uu);
                _graph2.put(v, vv);
            }
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        Set<Integer> vertex1 = _graph2.keySet();
        return Iteration.iteration(vertex1.iterator());
    }

    @Override
    public int successor(int v, int k) {
        if (!_graph2.containsKey(v)) {
            return 0;
        } else {
            Vertex vv = _graph2.get(v);
            if (!vv.hasSucc() || k > vv.succSize() - 1)  {
                return 0;
            } else {
                return vv.succ(k);
            }

        }
    }
    @Override
    public abstract int predecessor(int v, int k);

    @Override
    public Iteration<Integer> successors(int v) {
        Vertex vert = _graph2.get(v);
        ArrayList<Integer> successors1;
        if (vert != null) {
            successors1 = vert.s();
        } else {
            successors1 = new ArrayList<Integer>();
        }
        return Iteration.iteration(successors1.iterator());
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {

        if (_graph2.isEmpty()) {
            return null;
        }
        List<int[]> storage = new ArrayList<int[]>();
        for (int v : vertices()) {
            ArrayList<Edge> edge1 = _graph.get(v);
            if (edge1 == null) {
                continue;
            }
            for (int i = 0; i < edge1.size(); i++) {
                int[] data = new int[2];
                data[0] = edge1.get(i).getV0();
                data[1] = edge1.get(i).getV1();
                boolean isRepeat = false;
                for (int j = 0; j < storage.size(); j++) {
                    int[] curr = storage.get(j);
                    if (data[0] == curr[0] && data[1] == curr[1]) {
                        isRepeat = true;
                    }
                }
                if (!isRepeat) {
                    storage.add(data);
                }
            }
        }
        return Iteration.iteration(storage.iterator());
    }

    @Override
    protected boolean mine(int v) {
        return contains(v);
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!_graph2.containsKey(v)) {
            throw new Error("This vertex not in graph.");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        if (!contains(u, v)) {
            return 0;
        }
        return (u + v) * (u + v + 1) + v;
    }

}
