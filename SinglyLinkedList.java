
import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {

        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public SinglyLinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        }
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap() {
        if (size <= 1) {
            return;
        }

        List<Node<E>> original = new ArrayList<>();
        List<Node<E>> sorted = new ArrayList<>();
        Node<E> current = head;
        while (current != null) {
            original.add(current);
            sorted.add(current);
            current = current.getNext();
        }
        // we put the nodes in sorted order
        Collections.sort(sorted, (a,b) -> a.getElement().compareTo(b.getElement()));

        List<Node<E>> target = new ArrayList<>();
        for(int i = 0 ; i < sorted.size() ; i++) {
            // find the index of where the original node is now in sorted
            int k = sorted.indexOf(original.get(i));
            target.add(sorted.get(sorted.size() - k -1));
        }

        // now we change the reference
        for(int i = 0 ; i < sorted.size() -1 ; i++) {
            target.get(i).setNext(target.get(i+1));
        }

        // point the tail and head and the last node
        target.get(target.size() - 1).setNext(null);
        head = target.get(0);
        tail = target.get(target.size() - 1); 

    }
}
