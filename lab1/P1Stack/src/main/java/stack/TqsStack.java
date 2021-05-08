package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TqsStack<T> {

    private List<T> stack;

    public TqsStack(int i) {
        this.stack = new ArrayList<T>();
    }

    public void push(T element) {
        this.stack.add(element);
    }

    public T pop() {
        try {
            //remove o elemento
            return this.stack.remove(this.stack.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    public T peek() {
        if (this.stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.stack.get(this.stack.size() - 1);
    }


    public int size() {
        return this.stack.size();
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();

    }

    public void clear() {
        this.stack.clear();
    }


}
