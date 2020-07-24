package orb.___outer_core.util.datastructure;

import orb.____inner_core.IC_Common;
import orb.____inner_core.util.datatstructure._Tree_3D;

import java.util.Iterator;
import java.util.List;

/**
 * Created by doekewartena on 8/18/15.
 */
// just an idea...
public class OC_VoxelTree <T, C extends OC_VoxelTree> implements _Tree_3D<T, C> {

    // just and idea, but we still would need to store children
    char childInfo;

    // this might be a horible way to structure a voxel tree anyway

    @Override
    public C backFind(float x, float y, float z) {
        return null;
    }

    @Override
    public C backFind(float x, float y, float z, float x2, float y2, float z2) {
        return null;
    }

    @Override
    public C forwardFind(float x, float y, float z) {
        return null;
    }

    @Override
    public C forwardFind(float x, float y, float z, float x2, float y2, float z2) {
        return null;
    }


    @Override
    public float x1() {
        return 0;
    }

    @Override
    public float y1() {
        return 0;
    }

    @Override
    public float x2() {
        return 0;
    }

    @Override
    public float y2() {
        return 0;
    }

    @Override
    public float z1() {
        return 0;
    }

    @Override
    public float z2() {
        return 0;
    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public C parent() {
        return null;
    }

    @Override
    public boolean hasChildren() {
        return false;
    }

    @Override
    public C[] children() {
        return null;
    }

    @Override
    public C insert(T t) {
        return null;
    }

    @Override
    public C remove(T t) {
        return null;
    }

    @Override
    public Iterator<C> iterator() {
        return null;
    }

    @Override
    public float getX2(T t) {
        return 0;
    }

    @Override
    public float getY2(T t) {
        return 0;
    }

    @Override
    public float getZ2(T t) {
        return 0;
    }

    @Override
    public C queryAll(List<T> dest) {
        return null;
    }

    @Override
    public T query(float tx, float ty, float tz) {
        return null;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, float tx, float ty, float tz) {
        return null;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, float tx1, float ty1, float tz1, float tx2, float ty2, float tz2) {
        return null;
    }

    @Override
    public C queryRadiusSq(List<T> containing, List<T> intersecting, float cx, float cy, float cz, float radiusSQ) {
        return null;
    }

    @Override
    public C queryMinX(IC_Common.BestMatch<T> bestMatch) {
        return null;
    }

    @Override
    public C queryMinY(IC_Common.BestMatch<T> bestMatch) {
        return null;
    }

    @Override
    public C queryMinZ(IC_Common.BestMatch<T> bestMatch) {
        return null;
    }

    @Override
    public C queryMaxX(IC_Common.BestMatch<T> bestMatch) {
        return null;
    }

    @Override
    public C queryMaxY(IC_Common.BestMatch<T> bestMatch) {
        return null;
    }

    @Override
    public C queryMaxZ(IC_Common.BestMatch<T> bestMatch) {
        return null;
    }

    @Override
    public C queryClosest(float x, float y, float z, IC_Common.BestMatch<T> bestMatch) {
        return null;
    }

    @Override
    public float getX(T t) {
        return 0;
    }

    @Override
    public float getY(T t) {
        return 0;
    }

    @Override
    public float getZ(T t) {
        return 0;
    }
    // todo...
}
