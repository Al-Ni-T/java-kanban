package ru.yandex.manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private final CustomLinkedList<Integer> customLinkedList = new CustomLinkedList<>();
    private final Map<Integer, Node<Integer>> historyMap = new HashMap<>();

    public InMemoryHistoryManager() {
    }

    @Override
    public void addHistoryId(int taskId) {
        Node<Integer> historyNode = customLinkedList.addLast(taskId);

        if (historyMap.containsKey(taskId)) {
            removeNodeInCustomList(taskId);
        }
        historyMap.put(taskId, historyNode);
    }

    @Override
    public void removeNodeInCustomList(int taskId) {
        Node<Integer> nodeToRemove = historyMap.get(taskId);
        customLinkedList.removeNode(nodeToRemove);
    }

    @Override
    public List<Integer> getHistoryId() {
        return customLinkedList.getTasks();
    }

    public class CustomLinkedList<T> {
        public Node<T> head;
        public Node<T> tail;
        private int size = 0;

        public CustomLinkedList() {
            Node<T> node = new Node<>(null);
            this.head = node;
            this.tail = node;
        }

        public Node<T> addLast(T element) {

            if (isEmpty()) {
                Node<T> newNode = new Node<>(null, element, null);
                tail = newNode;
                head = newNode;
                size++;

                return newNode;
            } else {
                Node<T> newTail = new Node<>(tail, element, null);
                tail.next = newTail;
                tail = newTail;
                size++;

                return newTail;
            }
        }

        public void removeNode(Node<T> nodeToRemove) {

            if (nodeToRemove != null) {

                if (nodeToRemove == tail) {
                    nodeToRemove.prev.next = null;

                } else if (nodeToRemove == head) {
                    head.next.prev = null;

                    head = head.next;

                } else {
                    nodeToRemove.prev.next = nodeToRemove.next;
                    nodeToRemove.next.prev = nodeToRemove.prev;
                    nodeToRemove.next = null;
                    nodeToRemove.prev = null;
                }
            }
        }

        public List<T> getTasks() {
            List<T> toReturn = new LinkedList<>();
            if (this.isEmpty()) {
                return toReturn;
            }
            Node<T> current = head;

            while (current != null) {
                toReturn.add(current.element);
                current = current.next;
            }
            return toReturn;
        }

        private boolean isEmpty() {
            return size == 0;
        }
    }

    class Node<T> {
        public T element;

        public Node<T> next;
        public Node<T> prev;

        public Node(Node<T> prev, T element, Node<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public Node(T data) {
            this.element = data;
        }
    }
}
