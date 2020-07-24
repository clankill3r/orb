package nl.doekewartena.orb.outer_core.util.datastructure;

import nl.doekewartena.orb.inner_core.IC_Common;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree_3D;

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
    public C backFind(double x, double y, double z) {
        return null;
    }

    @Override
    public C backFind(double x, double y, double z, double x2, double y2, double z2) {
        return null;
    }

    @Override
    public C forwardFind(double x, double y, double z) {
        return null;
    }

    @Override
    public C forwardFind(double x, double y, double z, double x2, double y2, double z2) {
        return null;
    }


    @Override
    public double x1() {
        return 0;
    }

    @Override
    public double y1() {
        return 0;
    }

    @Override
    public double x2() {
        return 0;
    }

    @Override
    public double y2() {
        return 0;
    }

    @Override
    public double z1() {
        return 0;
    }

    @Override
    public double z2() {
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
    public double getX2(T t) {
        return 0;
    }

    @Override
    public double getY2(T t) {
        return 0;
    }

    @Override
    public double getZ2(T t) {
        return 0;
    }

    @Override
    public C queryAll(List<T> dest) {
        return null;
    }

    @Override
    public T query(double tx, double ty, double tz) {
        return null;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, double tx, double ty, double tz) {
        return null;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, double tx1, double ty1, double tz1, double tx2, double ty2, double tz2) {
        return null;
    }

    @Override
    public C queryRadiusSq(List<T> containing, List<T> intersecting, double cx, double cy, double cz, double radiusSQ) {
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
    public C queryClosest(double x, double y, double z, IC_Common.BestMatch<T> bestMatch) {
        return null;
    }

    @Override
    public double getX(T t) {
        return 0;
    }

    @Override
    public double getY(T t) {
        return 0;
    }

    @Override
    public double getZ(T t) {
        return 0;
    }
    // todo...
}
