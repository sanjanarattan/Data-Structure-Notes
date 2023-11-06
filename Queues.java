public class Queues {
    public interface Queue<T> {
        boolean isEmpty();
        void enqueue(T item);
        T dequeue() throws Exception;
        T peek() throws Exception;
        int size();
    }

    public static class ArrayQueue<T> implements Queue<T> {
        private T[] queueArray;
        private int front;
        private int rear;
        private int capacity;

        public ArrayQueue(int capacity) {
            this.capacity = capacity;
            this.queueArray = (T[]) new Object[capacity];
            this.front = this.rear = -1;
        }

        @Override
        public boolean isEmpty() {
            return front == -1;
        }

        @Override
        public void enqueue(T item) {
            if (front == -1) {
                front = rear = 0;
                queueArray[rear] = item;
            } else if ((rear + 1) % capacity == front) {
                grow_array();
                rear = (rear + 1) % capacity;
                queueArray[rear] = item;
            } else {
                rear = (rear + 1) % capacity;
                queueArray[rear] = item;
            }
        }

        @Override
        public T dequeue() throws Exception {
            if (isEmpty()) {
                throw new Exception("Queue is empty");
            } else if (front == rear) {
                T item = queueArray[front];
                front = rear = -1;
                return item;
            } else {
                T item = queueArray[front];
                front = (front + 1) % capacity;
                return item;
            }
        }

        @Override
        public T peek() throws Exception {
            if (isEmpty()) {
                throw new Exception("Queue is empty");
            } else {
                return queueArray[front];
            }
        }

        @Override
        public int size() {
            if (isEmpty()) {
                return 0;
            }
            if (front <= rear) {
                return rear - front + 1;
            } else {
                return capacity - front + rear + 1;
            }
        }

        private void grow_array() {
            int newCapacity = capacity * 2;
            T[] newArray = (T[]) new Object[newCapacity];
            if (front <= rear) {
                System.arraycopy(queueArray, front, newArray, 0, size());
            } else {
                System.arraycopy(queueArray, front, newArray, 0, capacity - front);
                System.arraycopy(queueArray, 0, newArray, capacity - front, rear + 1);
            }
            queueArray = newArray;
            capacity = newCapacity;
            front = 0;
            rear = size() - 1;
        }
    }

    public static class LinkedListQueue<T> implements Queue<T> {
        private Node<T> front;
        private Node<T> rear;
        private int size;

        private class Node<T> {
            T data;
            Node<T> next;

            public Node(T data) {
                this.data = data;
                this.next = null;
            }
        }

        public LinkedListQueue() {
            front = rear = null;
            size = 0;
        }

        @Override
        public boolean isEmpty() {
            return front == null;
        }

        @Override
        public void enqueue(T item) {
            Node<T> newNode = new Node<>(item);
            if (isEmpty()) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }

        @Override
        public T dequeue() throws Exception {
            if (isEmpty()) {
                throw new Exception("Queue is empty");
            }
            T item = front.data;
            front = front.next;
            size--;
            if (front == null) {
                rear = null;
            }
            return item;
        }

        @Override
        public T peek() throws Exception {
            if (isEmpty()) {
                throw new Exception("Queue is empty");
            }
            return front.data;
        }

        @Override
        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> arrayQueue = new ArrayQueue<>(5);
        for (int i = 1; i <= 5; i++) {
            arrayQueue.enqueue(i);
        }

        while (!arrayQueue.isEmpty()) {
            try {
                System.out.println("Array Queue - Dequeue: " + arrayQueue.dequeue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Queue<String> linkedListQueue = new LinkedListQueue<>();

        linkedListQueue.enqueue("X");
        linkedListQueue.enqueue("Y");
        linkedListQueue.enqueue("Z");

        while (!linkedListQueue.isEmpty()) {
            try {
                System.out.println("Linked List Queue - Dequeue: " + linkedListQueue.dequeue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
