public class ArrayDeque <T> {
    T[] items;
    int size;
    int nextFirst;

    public ArrayDeque() {
        items = (T []) new Object[8];
        nextFirst = 4;
        size = 0;
    }

    public void addFirst(T item) {
        resizing();
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1) % items.length;
        size++;
    }

    public void addLast(T item) {
        resizing();
        size++;
        items[(size + nextFirst) % items.length] = item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 1; i <= size; i++) {
            System.out.print(items[(i + nextFirst) % items.length] + " ");
        }
    }

    public T removeFirst() {
        T returnItem = items[(nextFirst + 1) % items.length];
        items[(nextFirst + 1) % items.length] = null;
        size--;
        nextFirst = (nextFirst + 1) % items.length;
        resizing();
        return returnItem;
    }

    public T removeLast() {
        T returnItem = items[(size + nextFirst) % items.length];
        items[(size + nextFirst) % items.length] = null;
        size--;
        resizing();
        return returnItem;
    }

    public T get(int index) {
        if (index < size) return items[(index + 1 + nextFirst) % items.length];
        return null;
    }

    private void resizing() {
        if (size == items.length) {
            T[] a = (T []) new Object[size*2];
            System.arraycopy(items, nextFirst + 1, a, size/2, size - nextFirst);
            System.arraycopy(items, 0, a, size/2 + size - nextFirst, nextFirst);
            nextFirst = size/2 - 1;
            items = a;
        }
        else if (size < items.length && size > 8) {
            T[] a = (T []) new Object[size*2];
            System.arraycopy(items, nextFirst + 1, a, size/2, size - nextFirst);
            if (nextFirst + size > items.length)
                System.arraycopy(items, 0, a, size/2 + size - nextFirst, nextFirst);
            nextFirst = size/2 - 1;
            items = a;
        }
    }
}