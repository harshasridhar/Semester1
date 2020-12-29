package prog2.utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by harshams on 28/12/2020
 *
 * @author Harsha M S
 */
public class Stack<T> implements Iterable<T> {

    private ArrayList<T> elements;
    private Integer elementCount;

    public Stack() {
        this.elements = new ArrayList<>();
        this.elementCount = 0;
    }

    /**
     * This function returns length
     *
     * @return int This returns the length of the stack
     */
    public Integer length() {
        //This function returns the length of elements in the stack
        return elementCount;
    }

    public T peek() {
        if (elementCount == 0)
            return null;
        return elements.get(elementCount - 1);
    }

    public T pop() {
        if (elementCount == 0)
            return null;
        T t = elements.get(elementCount - 1);
        elements.remove(elementCount - 1);
        elementCount--;
        return t;
    }

    public void push(T t) {
        elements.add(elementCount, t);
        elementCount++;
    }

    public void printElements() {
        for (T t : elements) {
            System.out.println(t);
        }
    }

    //    @Override
    public Iterator<T> iterator() {
        return elements.listIterator();
    }

    @Override
    public String toString() {
        return "Stack{" +
                "elements=" + elements +
                ", length=" + elementCount +
                '}';
    }
}
