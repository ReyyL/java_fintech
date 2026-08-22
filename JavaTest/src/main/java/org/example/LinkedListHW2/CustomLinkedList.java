package org.example.LinkedListHW2;

import java.util.Collection;

public class CustomLinkedList<T> {

    Node<T> head = null;
    Node<T> tail = null;

    int size = 0;

    static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    public void add(T element) {
        var newElement = new Node<>(element);
        size++;

        if (this.head == null) {
            head = newElement;
            tail = head;
        } else {
            tail.next = newElement;
            tail = tail.next;
        }
    }

    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index: " + index + ", when size: " + size);
        }
        var currentNode = head;

        for (var i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.value;
    }

    public int size() {
        return size;
    }

    public boolean contains(T element) {
        var currentNode = head;

        for (var i = 0; i < size; i++) {
            if (java.util.Objects.equals(currentNode.value, element)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public void addAll(Collection<T> list) {
        for (T e: list) {
            add(e);
        }
    }
}
