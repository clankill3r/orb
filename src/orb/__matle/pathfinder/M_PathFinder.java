package orb.__mantle.pathfinder;


import nl.doekewartena.orb.inner_core.util.function._GetDouble_TT;
import nl.doekewartena.orb.inner_core.util.function._GetIterator_T;

import java.util.ArrayList;
import java.util.Iterator;

import static nl.doekewartena.orb.inner_core.IC_Common.swapWithLastRemove;


/**
 * Created by doekewartena on 3/3/15.
 */

/*

-make FGHNode inner class?
-build something in for max attempts? This can always be hacked in by the user,
for example: by getIterator, count and after x counts return a iterator that always return false on hasNext() so the
open list gets emptied quite fast.


brainstorm:

+further optimise:
-give fghNode a index, store the indexes of the nodes we access (in? fixed array with count?) and reset those so the user doesn't have to reset.
-or give and id for each time we pathFind?



*/


public class M_PathFinder<T> {


    _FGHNodeProvider<T> fghNodeProvider;
    _GetIterator_T<T> getLinkIterator;
    _GetDouble_TT<T> getAdjacentCost;
    _GetDouble_TT<T> getEstimatedCost;

    ArrayList<M_FGHNode<T>> open = new ArrayList<>();


    public M_PathFinder(_FGHNodeProvider<T> fghNodeProvider, _GetIterator_T<T> getLinkIterator, _GetDouble_TT<T> getAdjacentCost, _GetDouble_TT<T> getEstimatedCost) {
        this.fghNodeProvider = fghNodeProvider;
        this.getLinkIterator = getLinkIterator;
        this.getAdjacentCost = getAdjacentCost;
        this.getEstimatedCost = getEstimatedCost;
    }

    // shouldn't a method like this be in a wrapper class (we need names for what I mean...)
    // (advanced class -> simple class extends advanced class)
    public void findPath(T start, T end, ArrayList<T> result) {

        ArrayList<T> startPositions = new ArrayList<T>();
        startPositions.add(start);

        findPath(startPositions, end, result);

    }


    // if we would make an advanced class and an simple extending one
    // then we better provide the openList instead of the startPositions
    public void findPath(ArrayList<T> startPositions, T end, ArrayList<T> result) {

        // We can have multiple start positions
        // it will start with the one closest to the end point
        // as soon as the current node gets further away then another start position
        // then it will continue with that one
        // (basically how pathfinding works...)


        for (T o : startPositions) {
            M_FGHNode<T> n = fghNodeProvider.getFGHNode(o);
            n.linkedObject = o;
            open.add(n);
        }

        M_FGHNode<T> endNode = fghNodeProvider.getFGHNode(end);
        endNode.linkedObject = end;

        boolean possible = false;

        while (open.size () > 0) {

            // find the node with the lowest f value
            // we loop instead of sort to gain speed
            double lowestF = Double.MAX_VALUE;
            int lowestFIndex = -1;

            for (int i = 0; i < open.size(); i++) {

                M_FGHNode<T> node = open.get(i);

                if (node.f < lowestF) {
                    lowestF = node.f;
                    lowestFIndex = i;
                }
            }


            M_FGHNode<T> currentNode = open.get(lowestFIndex);
            T current = currentNode.linkedObject;

            if (current == null) {
                System.out.println("[Error: linkedObject is null for FGHNode ]");
                break;
            }

            // prevent recursion
            currentNode.closed = true;

            swapWithLastRemove(open, lowestFIndex);


            if (currentNode == endNode) {
                possible = true;
                break;
            }


            Iterator<T> itr = getLinkIterator.getIterator(current);

            while (itr.hasNext()) {

                T neighbour = itr.next();

                M_FGHNode<T> neighbourNode = fghNodeProvider.getFGHNode(neighbour);
                neighbourNode.linkedObject = neighbour;

                if (!neighbourNode.closed) {

                    if (!neighbourNode.isInOpenList) {

                        open.add(neighbourNode);
                        neighbourNode.isInOpenList = true;

                        neighbourNode.parent = currentNode;

                        neighbourNode.g = currentNode.g + getAdjacentCost.val(current, neighbour);
                        neighbourNode.h = getEstimatedCost.val(neighbour, end);
                        neighbourNode.f = neighbourNode.g + neighbourNode.h;

                    }
                    else {

                        // we got here before but we might got here faster now
                        // so we need to check that

                        double cost = getAdjacentCost.val(current, neighbour);

                        if (neighbourNode.g > currentNode.g + cost) {

                            neighbourNode.parent = currentNode;

                            neighbourNode.g = currentNode.g + cost;
                            neighbourNode.h = getEstimatedCost.val(neighbour, end);
                            neighbourNode.f = neighbourNode.g + neighbourNode.h;
                        }


                    }
                }
            }

        }

        if(possible) {

            M_FGHNode<T> current = endNode;
            while (current != null) {
                result.add(current.linkedObject);
                current = current.parent;
            }
        }

        open.clear();
        fghNodeProvider.reset();

    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .



}


