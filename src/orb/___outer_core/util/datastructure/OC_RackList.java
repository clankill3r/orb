package orb.___outer_core.util.datastructure;

import static orb.____inner_core.IC_Math.*;

import java.util.*;

/**
 * Created by doekewartena on 8/4/15.
 */


/*
Now we have chancing indexes, in some way this makes sense.
But what about inserting between 2? Test!!

For example, insert a line in a line.


---------------

option to shift with parameters:

shift(spacing);

for example:

shift(1);

(where n is null)
[x][n][x][n][x][n][x][n][x][n]
[x][n][x][n][x][n][x][n][x][n]
[x][n][x][n][x][n][x][n][x][n]

probably bad...
-----------



It would be nice if we could disable values instead of setting them to null
Cause now once we removed all and we would add again then it could still be a nightmare in
memory land!

Can we extend our RackList to provide the extra functionality?


?

boolean[] active;


 */

// todo, mod count in some methods!


// it would be nice to implement List or something
public class OC_RackList<E> extends AbstractList<E> implements List<E> { // RandomAccess, Cloneable, java.io.Serializable

    public boolean debug;

    public static final int DEFAULT_CAPACITY = 16;

    // default
    public final int RACK_CAPACITY;

    // do we want the racks by node or store them in an array?
    // lets first go with this
    Rack first;
    Rack last;


    int size;


    public OC_RackList() {
        this(DEFAULT_CAPACITY);
    }

    public OC_RackList(int rackCapacity) {
        this.RACK_CAPACITY = rackCapacity;
        first = new Rack();
        last = first;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .






    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }





    @Override
    // add to the last rack, creates new rack if needed
    public boolean add(E e) {

        if (!last.add(e)) {
            addBucket();
            last.add(e);
        }
        size++;
        return true;
    }

    public void addBucket() {
        Rack newRack = new Rack();
        last.next = newRack;
        newRack.previous = last;
        last = newRack;
    }



    // this might be a good reason to provide direct access to racks...
    // however, that will mess up counting...
    // if we make a better iterator that supports remove then we don't
    // need to override this method
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            // do we need this? since the rack also does this
            throw new NullPointerException();
        }
        else {

            for (Rack current = first; current != null; current = current.next) {

                if (current.remove(o)) {
                    size--;
                    return true;
                }
            }

        }
        return false;
    }



    // we keep the racks for now
    // todo!!! this means that after clearing when we add
    // it gets added to the last rack which we probably don't want
    @Override
    public void clear() {
        for (Rack current = first; current != null; current = current.next) {
            current.clear();
        }
        size = 0;
    }

    // todo can we optimise this by making some kind of index map?
    @Override
    public E get(int index) {

        // todo, rangeCheck, for now a simple one:
        if (index > size || index < 0) throw new IndexOutOfBoundsException();

        int index_for_rack = index;

        for (Rack current = first; current != null; current = current.next) {

            if (index_for_rack < current.size) {
                return current.get(index_for_rack);
            }
            else {
                index_for_rack -= current.size;
            }
        }
        return null;
    }

    @Override
    public E set(int index, E element) {

        if (index > size || index < 0) throw new IndexOutOfBoundsException();

        int index_for_rack = index;

        for (Rack current = first; current != null; current = current.next) {

            if (index_for_rack < current.size) {

                return current.set(index_for_rack, element);
            }
            else {
                index_for_rack -= current.size;
            }
        }

        return null;
    }

    @Override
    public void add(int index, E element) {
        System.out.println("todo");
        // todo
    }


    public void debug() {

        for (Rack current = first; current != null; current = current.next) {
            System.out.println(Arrays.toString(current.data));
        }

    }


    @Override
    public E remove(int index) {

        rangeCheck(index);

        modCount++;

        E oldValue = null;

        int j = index;
        for (Rack current = first; current != null; current = current.next) {
            if (j < current.size) {
                oldValue = current.get(j);
                current.remove(j); // make remove return object instead of boolean?
                size--;
                break;
            }
            j -= current.size;
        }

        return oldValue;
    }


    /**
     * Checks if the given index is in range.  If not, throws an appropriate
     * runtime exception.  This method does *not* check if the index is
     * negative: It is always used immediately prior to an array access,
     * which throws an ArrayIndexOutOfBoundsException if index is negative.
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * A version of rangeCheck used by add and addAll.
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }


    // AbstractList
//    @Override
//    public int indexOf(Object o) {
//        return 0;
//    }

    // AbstractList
//    @Override
//    public int lastIndexOf(Object o) {
//        return 0;
//    }

// ?
//    @Override
//    public ListIterator<E> listIterator() {
//        return null;
//    }
//
//    @Override
//    public ListIterator<E> listIterator(int index) {
//        return null;
//    }
//
//    @Override
//    public List<E> subList(int fromIndex, int toIndex) {
//        return null;
//    }


    public Iterator<E> iterator() {
        return new Itr();
    }


    private class Itr implements Iterator<E> {

        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
//            Object[] elementData = ArrayList.this.elementData;
//            if (i >= elementData.length)
//                throw new ConcurrentModificationException();
            cursor = i + 1;
            lastRet = i;
            // now return corrected i
            int j = i;
            for (Rack current = first; current != null; current = current.next) {
                if (j < current.size) return current.get(j);
                j -= current.size;
            }
            System.out.println("bug in OC_RackList next()!!!");
            return null;
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                //ArrayList.this.remove(lastRet);
                OC_RackList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }


    // todo!
    private class ListItr extends Itr implements ListIterator<E> {

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }


    /** ==============================================================================================================*/



    class Rack {

        Rack previous;
        Rack next;

        E[] data;

        int size;

        // this allows for direct insertion
        int highestUsedIndex = -1;



        Rack() {
            data = newArray(RACK_CAPACITY);
        }

        // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

        boolean add(E e) {

            if (size == RACK_CAPACITY) {
                return false;
            }
            if (highestUsedIndex == RACK_CAPACITY -1) {
                // we need to re arrange so all elements
                // tightly fit again without nulls in between
                // stack();
                // better, push to the left and quit as soon as we have room
                // maybe a ugly way to set highestUsedIndex but its convenient



                // we should test with how much we move
                // now we move a lot more then needed
                // but this could come in handy
                int max = max(1, (RACK_CAPACITY - size)/2);
                moveNullsToEnd(data, max);

                int index = data.length;
                while (--index != -1 && data[index] == null);
                highestUsedIndex = index;

            }
            data[++highestUsedIndex] = e;
            size++;

            return true;
        }




        // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


        // todo, make static and drop in other class
        // http://stackoverflow.com/q/31834786
        // returns index of last element that is not null
        // define range to operate on?
        public void moveNullsToEnd(Object[] arr, int max) {

            int offset = arr.length;
            int nullCount = 0;

            while(nullCount < max) {
                if(arr[--offset] == null)
                    nullCount++;
                if(offset == 0 && nullCount < max)
                    //throw new IllegalStateException("Not enough nulls");
                    break;
            }
            int target = offset;

            while(offset < arr.length) {
                if(arr[offset] != null)
                    arr[target++]=arr[offset++];
                else
                    offset++;
            }

            Arrays.fill(arr, target, offset, null);

            // give problems with all being null
            //return target-1;
        }



        // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

        // todo, make static and drop in other class
        public void moveNullsToEnd(Object[] arr) {

            int nullCount = 0;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    nullCount++;
                } else if (nullCount != 0) {
                    arr[i-nullCount] = arr[i];
                }
            }
            // set the last elements to null
            if (nullCount != 0) {
                for (int i = arr.length - nullCount; i < arr.length; i++) {
                    arr[i] = null;
                }
            }
        }



        // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

        // todo, range check etc.
        public E remove(int index) {

            E oldValue = null;

            if (highestUsedIndex == size-1) { // packed tight!
                oldValue = data[index];
                data[index] = null;
                size--;
            }
            else { // can we make a method to get the index we want?
                int c = 0;
                for (int i = 0; i < RACK_CAPACITY; i++) {
                    if (data[i] != null) {
                        if (c++ == index) {
                            oldValue = data[i];
                            data[index] = null;
                            size--;
                            break;
                        }
                    }
                }
            }

            return oldValue;
        }



        // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

        boolean remove(Object o) {

            if (o == null) {
                throw new NullPointerException();
            }
            else {

                int highestUsedIndexBeforeRemove = -1;

                for (int i = 0; i < data.length; i++) {

                    if (o.equals(data[i])) {
                        data[i] = null;
                        size--;


                        if (i == highestUsedIndex) {
//                            if (i > 0)
//                                highestUsedIndex = highestUsedIndexBeforeRemove;
//                            else
//                                highestUsedIndex = -1;
                            highestUsedIndex = highestUsedIndexBeforeRemove;
                        }
                        return true;
                    }
                    else if (data[i] != null) {
                        highestUsedIndexBeforeRemove = i;
                    }
                }
            }
            return false;
        }


        // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

        void clear() {
            for (int i = 0; i < data.length; i++) {
                data[i] = null;
            }
            size = 0;
            highestUsedIndex = -1;
        }

        // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

        public E get(int index) {

            if (highestUsedIndex == size-1) { // packed tight!
                return data[index];
            }
            else {
                int c = 0;
                for (int i = 0; i < RACK_CAPACITY; i++) {
                    if (data[i] != null) {
                        if (c++ == index) return data[i];
                    }
                }
            }
            return null;
        }

        // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

        public E set(int index, E element) {

            if (highestUsedIndex == size-1) { // packed tight
                E old = data[index];
                data[index] = element;
                return old;
            }
            else {
                int c = 0;
                for (int i = 0; i < RACK_CAPACITY; i++) {
                    if (data[i] != null) {
                        if (c++ == index) {
                            E old = data[i];
                            data[i] = element;
                            return old;
                        }
                    }
                }
            }
            return null;

        }

        // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .




        // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .



    }

    @SafeVarargs
    static <E> E[] newArray(int length, E... array)
    {
        return Arrays.copyOf(array, length);
    }

    /** ==============================================================================================================*/


}
