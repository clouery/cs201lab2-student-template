
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

        List<Node<E>> sorted = new ArrayList<>();
        Node<E> current = head;
        while (current != null) {
            sorted.add(current);
            current = current.getNext();
        }
        // we put the nodes in sorted order
        sorted.sort((a,b) -> Integer.compare((Integer) a.getElement(), (Integer) b.getElement()));


        int leftPtr = 0;
        int rightPtr = sorted.size() - 1;

        while (leftPtr < rightPtr) {
            // we swap the largest and smallest ...
            Node<E> temp = sorted.get(leftPtr);
            sorted.set(leftPtr, sorted.get(rightPtr));
            sorted.set(rightPtr, temp);

            leftPtr++;
            rightPtr--;
        }

        // now we change the reference
        for(int i = 0 ; i < sorted.size() -1 ; i++) {
            Node<E> curNode = sorted.get(i);
            curNode.setNext(sorted.get(i+1));
        }

        // point the tail and head and the last node
        sorted.get(sorted.size() - 1).setNext(null);
        head = sorted.get(0);
        tail = sorted.get(sorted.size() - 1); 

    }
}
