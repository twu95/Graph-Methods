package graph;
import java.util.Collections;
import java.util.LinkedList;


/* See restrictions in Graph.java. */

/** Implements a depth-first traversal of a graph.  Generally, the
 *  client will extend this class, overriding the visit and
 *  postVisit methods, as desired (by default, they do nothing).
 *  @author Thomas Wu
 */
public class DepthFirstTraversal extends Traversal {


    /** A depth-first Traversal of G, using FRINGE as the fringe. */
    protected DepthFirstTraversal(Graph G) {
        super(G, Collections.asLifoQueue(new LinkedList<Integer>()));
    }

    @Override
    protected boolean visit(int v) {
        return true;
    }

    @Override
    protected boolean postVisit(int v) {
        return true;
    }
    @Override
    protected boolean shouldPostVisit(int v) {
        return true;
    }
    @Override
    protected boolean reverseSuccessors(int v) {
        return true;
    }
}
