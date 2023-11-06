public class Stacks {
    public interface Stack<T> {
        boolean isEmpty();
        void push(T item);
        T pop() throws Exception;
        T peek() throws Exception;
        int size();
    }

    public static class ArrayStack<T> implements Stack<T> {
        private T[] stackArray;
        private int top;
        private int capacity;
    
        public ArrayStack(int capacity) {
            this.capacity = capacity;
            this.stackArray = (T[]) new Object[capacity];
            this.top = -1;
        }
    
        @Override
        public boolean isEmpty() {
            return top == -1;
        }
    
        @Override
        public void push(T item) {
            if (top == capacity - 1) {
                grow_array();
            }
            stackArray[++top] = item;
        }
    
        @Override
        public T pop() throws Exception {
            if (!isEmpty()) {
                T item = stackArray[top];
                stackArray[top--] = null;
                return item;
            } else {
                throw new Exception("Stack is empty");
            }
        }
    
        @Override
        public T peek() throws Exception {
            if (!isEmpty()) {
                return stackArray[top];
            } else {
                throw new Exception("Stack is empty");
            }
        }
    
        @Override
        public int size() {
            return top + 1;
        }
    
        private void grow_array() {
            int newCapacity = capacity * 2;
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(stackArray, 0, newArray, 0, capacity);
            stackArray = newArray;
            capacity = newCapacity;
        }
    }
    

    public static class LinkedListStack<T> implements Stack<T> {
        private Node<T> top;
        private int size;

        private class Node<T> {
            T data;
            Node<T> next;

            public Node(T data) {
                this.data = data;
                this.next = null;
            }
        }

        public LinkedListStack() {
            top = null;
            size = 0;
        }

        @Override
        public boolean isEmpty() {
            return top == null;
        }

        @Override
        public void push(T item) {
            Node<T> newNode = new Node<>(item);
            newNode.next = top;
            top = newNode;
            size++;
        }

        @Override
        public T pop() throws Exception {
            if (!isEmpty()) {
                T item = top.data;
                top = top.next;
                size--;
                return item;
            } else {
                throw new Exception("Stack is empty");
            }
        }

        @Override
        public T peek() throws Exception {
            if (!isEmpty()) {
                return top.data;
            } else {
                throw new Exception("Stack is empty");
            }
        }

        @Override
        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> arrayStack = new ArrayStack<>(5);
        for (int i = 1; i <= 5; i++) {
            arrayStack.push(i);
        }
        while (!arrayStack.isEmpty()) {
            try {
                System.out.println("Array Stack - Pop: " + arrayStack.pop());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Stack<String> linkedListStack = new LinkedListStack<>();

        linkedListStack.push("A");
        linkedListStack.push("B");
        linkedListStack.push("C");

        while (!linkedListStack.isEmpty()) {
            try {
                System.out.println("Linked List Stack - Pop: " + linkedListStack.pop());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
