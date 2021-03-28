public class LinkedListDeque<T> {

    private class Node {
        Node prev;
        T item;
        Node next;
        Node(Node prev, T item, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node pos = sentinel.next;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(pos.item + "");
            pos = pos.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T value = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        return value;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T value = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        return value;
    }

    public  T get(int index) {
        if (index < size) {
            Node pos = sentinel.next;
            for (int i = 0; i < index; i++) {
                pos = pos.next;
            }
            return pos.item;
        }
        return null;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(0, index, sentinel.next);
    }

    private T getRecursive(int pos, int index, Node x){
        if (pos == index) return x.item;
        return getRecursive(pos+1, index, x.next);
    }

}

