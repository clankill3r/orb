package nl.doekewartena.orb.mantle.pathfinder;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by doekewartena on 5/21/15.
 */

// this is quite an optimised implementation!
// since it allows to reuse created nodes for the next path finding
public class M_DefaultFGHNodeProvider<T> implements _FGHNodeProvider<T> {


    HashMap<T, M_FGHNode> nodeDataBase = new HashMap<T, M_FGHNode>();
    ArrayList<M_FGHNode> freeNodes = new ArrayList<M_FGHNode>();

    @Override
    public M_FGHNode<T> getFGHNode(T node) {

        M_FGHNode<T> fghNode = nodeDataBase.get(node);

        if (fghNode == null) {

            if (freeNodes.size() > 0)
                fghNode = freeNodes.remove(freeNodes.size()-1);
            else
                fghNode = new M_FGHNode<T>();

            nodeDataBase.put(node, fghNode);
        }

        return fghNode;
    }

    @Override
    public void reset() {
        for (M_FGHNode n : nodeDataBase.values()) {
            n.reset();
            freeNodes.add(n);
        }
        nodeDataBase.clear();
    }
}
