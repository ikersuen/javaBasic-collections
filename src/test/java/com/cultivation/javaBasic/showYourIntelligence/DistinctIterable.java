package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;


public class DistinctIterable<T> implements Iterable<T> {
    private Iterable<T> iterable;

    public DistinctIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return new DistinctIterator<>(iterable.iterator());
    }

    public List<T> toList() {
        ArrayList<T> result = new ArrayList<>();
        this.forEach(result::add);
        return result;
    }
}

class DistinctIterator<E> implements Iterator<E> {
    // TODO: Implement the class to pass the test. Note that you cannot put all items into memory or you will fail.
    // <--start
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private static final Object NULL = new Object();
    private final Iterator<E> iterator;
    Set<E> seenSoFar = new HashSet<>();
    private Object next = NULL;

    DistinctIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        if (this.next != NULL)
        {
            return true;
        }
        while (this.iterator.hasNext())
        {
            E temp = this.iterator.next();
            if (this.seenSoFar.add(temp))
            {
                this.next = temp;
                return true;
            }
        }
        return false;
    }

    @Override
    public E next() {
        if (this.next != NULL || this.hasNext())
        {
            Object temp = this.next;
            this.next = NULL;
            return (E) temp;
        }
        throw new NoSuchElementException();
    }
    // --end->
}