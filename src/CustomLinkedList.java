public class CustomLinkedList {

    class SingleLinkedList<T> {

        public Node<T> head = null;

        public class Node<T> {
            T data;
            Node<T> next = null;

            public Node(T data) {
                this.data = data;
            }
        }

        public void addNode(T data) {
            if (this.head == null) {
                head = new Node<T>(data);
            } else {
                Node<T> node = this.head;
                while (node.next != null) {
                    node = node.next;
                }
                node.next = new Node<T>(data);
            }
        }

        public boolean deleteNode(T delData) {
            if (this.head == null) {
                return false;
            } else {
                Node<T> node = this.head;
                if (node.data == delData) {
                    this.head = this.head.next;
                    return true;
                } else {
                    while (node.next != null) {
                        if (node.next.data == delData) {
                            node.next = node.next.next;
                            return true;
                        }
                        node = node.next;
                    }
                    return false;
                }
            }
        }

        public Node<T> search(T data) {
            if (this.head == null) {
                return null;
            } else {
                Node<T> node = this.head;
                while (node != null) {
                    if (node.data == data) {
                        return node;
                    } else {
                        node = node.next;
                    }
                }
                return null;
            }
        }

        public void printAll() {
            if (head != null) {
                Node<T> node = this.head;
                System.out.println(node.data);
                while (node.next != null) {
                    node = node.next;
                    System.out.println(node.data);
                }
            }
        }
    }
}
