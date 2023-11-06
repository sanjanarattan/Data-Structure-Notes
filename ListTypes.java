public class L {

    public interface List<T> {
        int size();
        T get(int pos) throws Exception;
        boolean add(T item);
        void add(int pos, T item) throws Exception;
        T remove(int pos) throws Exception;
    }

    public static class ArrayList<T> implements List<T> {
        protected int size;
        protected T[] arr;

        public ArrayList() {
            arr = (T[]) new Object[size];
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public T get(int pos) throws Exception {
            if (pos < 0 || pos >= size) {
                throw new Exception("Index out of bounds");
            }
            return arr[pos];
        }

        @Override
        public boolean add(T item) {
            if (size == arr.length) {
                grow_array();
            }
            arr[size++] = item;
            return true;
        }

        private void grow_array() {
            T[] new_arr = (T[]) new Object[arr.length * 3 / 2 + 1];
            for (int i = 0; i < arr.length; i++) {
                new_arr[i] = arr[i];
            }
            arr = new_arr;
        }

        @Override
        public void add(int pos, T item) throws Exception {
            if (pos < 0 || pos > size) {
                throw new Exception("Illegal position");
            }
            if (size == arr.length) {
                grow_array();
            }
            for (int i = size; i > pos; i--) {
                arr[i] = arr[i - 1];
            }
            arr[pos] = item;
            size++;
        }

        @Override
        public T remove(int pos) throws Exception {
            if (pos < 0 || pos >= size) {
                throw new Exception("Illegal position");
            }
            T temp = arr[pos];
            for (int i = pos; i < size - 1; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
            return temp;
        }
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
    }

    public static class DoublyLinkedList<T> implements List<T> {
    private class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            prev = null;
            next = null;
        }
    }

    int size;
    Node<T> head;
    Node<T> tail;

    public DoublyLinkedList() {
        size = 0;
        head = null;
        tail = null;
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
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        ++size;
        return true;
    }

    @Override
    public void add(int pos, T item) throws Exception {
        if (pos < 0 || pos > size) {
            throw new Exception("List index out of bounds");
        }
        if (pos == size) {
            add(item);
        } else if (pos == 0) {
            Node<T> newNode = new Node<>(item);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            ++size;
        } else {
            Node<T> current = head;
            for (int i = 0; i < pos - 1; i++) {
                current = current.next;
            }
            Node<T> newNode = new Node<>(item);
            newNode.prev = current;
            newNode.next = current.next;
            current.next.prev = newNode;
            current.next = newNode;
            ++size;
        }
    }

    @Override
    public T remove(int pos) throws Exception {
        if (pos < 0 || pos >= size || size == 0) {
            throw new Exception("List index out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
        --size;
        return current.data;
    }
}
public static class DummyNodeLinkedList<T> implements List<T> {
    private class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            next = null;
        }
    }

    int size;
    Node<T> dummy;

    public DummyNodeLinkedList() {
        size = 0;
        dummy = new Node<>(null);
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
        Node<T> current = dummy.next;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public boolean add(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = dummy.next;
        dummy.next = newNode;
        ++size;
        return true;
    }

    @Override
    public void add(int pos, T item) throws Exception {
        if (pos < 0 || pos > size) {
            throw new Exception("List index out of bounds");
        }
        Node<T> current = dummy;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        Node<T> newNode = new Node<>(item);
        newNode.next = current.next;
        current.next = newNode;
        ++size;
    }

    @Override
    public T remove(int pos) throws Exception {
        if (pos < 0 || pos >= size || size == 0) {
            throw new Exception("List index out of bounds");
        }
        Node<T> current = dummy;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        Node<T> removed = current.next;
        current.next = removed.next;
        --size;
        return removed.data;
    }
}

    
public static class CircularLinkedList<T> implements List<T> {
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

    public CircularLinkedList() {
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
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
            head.next = head; 
        } else {
            newNode.next = head.next;
            head.next = newNode;
        }
        ++size;
        return true;
    }

    @Override
    public void add(int pos, T item) throws Exception {
        if (pos < 0 || pos > size) {
            throw new Exception("List index out of bounds");
        }
        if (pos == size) {
            add(item);
        } else if (pos == 0) {
            Node<T> newNode = new Node<>(item);
            newNode.next = head.next;
            head.next = newNode;
            ++size;
        } else {
            Node<T> current = head;
            for (int i = 0; i < pos; i++) {
                current = current.next;
            }
            Node<T> newNode = new Node<>(item);
            newNode.next = current.next;
            current.next = newNode;
            ++size;
        }
    }

    @Override
    public T remove(int pos) throws Exception {
        if (pos < 0 || pos >= size || size == 0) {
            throw new Exception("List index out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        Node<T> removed = current.next;
        current.next = removed.next;
        if (pos == 0) {
            head = current;
        }
        --size;
        return removed.data;
    }
}

    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);

        System.out.println("ArrayList Size: " + myList.size());

        List<Integer> myLinkedList = new LinkedList<>();
        myLinkedList.add(1); 
        myLinkedList.add(2); 
        System.out.println("LinkedList Size: " + myLinkedList.size());

        List<Integer> myDoublyLinkedList = new DoublyLinkedList<>();
        myDoublyLinkedList.add(1);
        myDoublyLinkedList.add(2);
        myDoublyLinkedList.add(3);
        System.out.println("DoublyLinkedList Size: " + myDoublyLinkedList.size());

        List<Integer> myDummyNodeLinkedList = new DummyNodeLinkedList<>();
        myDummyNodeLinkedList.add(1);
        myDummyNodeLinkedList.add(2);
        System.out.println("DummyNodeLinkedList Size: " + myDummyNodeLinkedList.size());

        List<Integer> myCircularLinkedList = new CircularLinkedList<>();
        myCircularLinkedList.add(1);
        myCircularLinkedList.add(2);
        myCircularLinkedList.add(3);
        System.out.println("CircularLinkedList Size: " + myCircularLinkedList.size());
    }
}
