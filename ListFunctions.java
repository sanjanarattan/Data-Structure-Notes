/* Functions reverseList, duplicate, intersection, sort, middleNode, nthNode, loopDetect*/

import org.w3c.dom.Node;

public class ListFunctions {

    public interface List<T> {
        int size();
        T get(int pos) throws Exception;
        boolean add(T item);
        void add(int pos, T item) throws Exception;
        T remove(int pos) throws Exception;
    }

    public static class LinkedList<T> implements List<T> {
        private class Node<T> {
            T data;
            Node<T> next;

            public Node(T data) {
                this.data = data;
                next = null;
            }
        }

        int size;
        Node<T> head;

        public LinkedList() {
            size = 0;
            head = null;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public T get(int pos) throws Exception {
            if (pos < 0 || pos >= size) {
                throw new Exception("List index out of bounds");
            }
            Node<T> current = head;
            for (int i = 0; i < pos; i++) {
                current = current.next;
            }
            return current.data;
        }

        @Override
        public boolean add(T item) {
            if (head == null) {
                head = new Node<T>(item);
                ++size;
            } else {
                Node<T> current = head;
                while (current.next != null) {
                    current = current.next;
                }
                Node<T> node = new Node<T>(item);
                current.next = node;
                ++size;
            }
            return true;
        }

        @Override
        public void add(int pos, T item) throws Exception {
            if (pos < 0 || pos > size) {
                throw new Exception("List index out of bounds");
            }
            if (pos == 0) {
                Node<T> node = new Node<>(item);
                node.next = head;
                head = node;
                ++size;
            } else {
                if (head == null) {
                    throw new Exception("List is empty, cannot add at a non-zero index.");
                }
                Node<T> previous = head;
                for (int i = 0; i < pos - 1; i++) {
                    previous = previous.next;
                }
                Node<T> node = new Node<>(item);
                node.next = previous.next;
                previous.next = node;
                ++size;
            }
        }

        @Override
        public T remove(int pos) throws Exception {
            if (pos < 0 || pos >= size || size == 0) {
                throw new Exception("List index out of bounds");
            }
            if (pos == 0) {
                Node<T> node = head;
                head = head.next;
                --size;
                return node.data;
            }
            Node<T> previous = head;
            for (int i = 0; i < pos - 1; i++) {
                previous = previous.next;
            }
            Node<T> current = previous.next;
            previous.next = current.next;
            --size;
            return current.data;
        }

        public Node<T> reverseList() {
            Node<T> current = head;
            Node<T> previous = null;
            Node<T> next = null;
            while (current != null) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            head = previous;
            return head;
        }

        public Node<T> middleNode() {
            if (head == null) {
                return null; // The list is empty
            }
        
            Node<T> slowPtr = head;
            Node<T> fastPtr = head;
        
            while (fastPtr != null && fastPtr.next != null) {
                slowPtr = slowPtr.next;
                fastPtr = fastPtr.next.next;
            }
        
            return slowPtr;
        }
        

        public boolean duplicate(T item){
            Node<T> current = head;
            while (current != null) {
                if (current.data.equals(item)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        
        }
    }


    public static void main(String[] args) {
        LinkedList<Integer> myLinkedList = new LinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);

        System.out.println("Original List:");
        for (int i = 0; i < myLinkedList.size(); i++) {
            try {
                System.out.print(myLinkedList.get(i) + " ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        myLinkedList.reverseList();
        System.out.println("\nReversed List:");
        for (int i = 0; i < myLinkedList.size(); i++) {
            try {
                System.out.print(myLinkedList.get(i) + " ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     
        System.out.println(myLinkedList.duplicate(2));
        LinkedList.Node middleNode = myLinkedList.middleNode();
        System.out.println("Middle Node: " + middleNode.data);
        try {
            int getTest = myLinkedList.get(1);
            System.out.println("Element at index 1: " + getTest);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        
    }
}