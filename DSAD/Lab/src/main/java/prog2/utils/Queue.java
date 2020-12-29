package prog2.utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by harshams on 28/12/2020
 */
public class Queue<T> implements Iterable<T> {

    private ArrayList<T> elements;
    private Integer elementCount;

    public Queue() {
        this.elements = new ArrayList<>();
        this.elementCount = 0;
    }

    public Integer length() {
        return elementCount;
    }

    public Boolean isEmpty() {
        return elementCount == 0;
    }

    public void enqueue(T t) {
        elements.add(t);
        elementCount++;
    }

    public T dequeue() {
        if (elementCount == 0)
            return null;
        T t = elements.get(0);
        elements.remove(0);
        elementCount--;
        return t;
    }


    //    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public String toString() {
        return "Queue{" +
                "elements=" + elements +
                ", size=" + elementCount +
                '}';
    }
}
