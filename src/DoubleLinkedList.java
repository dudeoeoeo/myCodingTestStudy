public class DoubleLinkedList<T> {
    public Node<T> head = null;
    public Node<T> tail = null;

    public class Node<T> {
        T data;
        Node<T> prev = null;
        Node<T> next = null;

        public Node(T data) {
            this.data = data;
        }
    }

    public void addNode(T data) {
        if (this.head == null) {
            this.head = new Node<>(data);
            this.tail = this.head;
        } else  {
            Node<T> node = this.head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<T>(data);
            node.next.prev = node;
            this.tail = node.next;
        }
    }

    public void deleteAll() {
        if (this.head == null) {
            return;
        } else {
            Node<T> node = this.head;
            while (node.data != null) {
                node.data = null;
                if (node.next != null) {
                    Node<T> nodeNext = node.next;
                    node.next = null;
                    node = nodeNext;
                } else {
                    this.head = null;
                    break;
                }
            }
        }
    }

    public boolean insertToFront(T existedData, T addData) {
        // head 가 null 일 경우 바로 추가 후 종료
        if (this.head == null) {
            this.head = new Node<T>(addData);
            this.tail = this.head;
            return true;
        // 현재 head.data 가 existedData 일 경우 새로운 노드의 next 에 existedData 셋 후 현재 head 를 새로운 노드로 교체
        } else if (this.head.data == existedData) {
            Node<T> newHead = new Node<T>(addData);
            newHead.next = this.head;
            this.head = newHead;
            return true;
        // 현재 head 가 null 이 아니고 data 가 existedData 와 같지 않을 때 노드를 순회하며 데이터 찾기
        } else {
            Node<T> node = this.head;
            while (node != null) {
                if (node.data == existedData) {
                    Node<T> nodePrev = node.prev;

                    nodePrev.next = new Node<>(addData);
                    nodePrev.next.next = node;

                    nodePrev.next.prev = nodePrev;
                    node.prev = nodePrev.next;
                    return true;
                } else {
                    node = node.next;
                }
            }
            return false;
        }
    }

    public boolean insertRear(T addData) {
        if (this.head == null) {
            this.head = new Node<T>(addData);
            this.tail = this.head;
            return true;
        } else {
            Node<T> node = this.head;
            while (node != null) {
                if (node.next != null) {
                    node = node.next;
                } else {
                    Node<T> nodeLast = new Node<T>(addData);
                    node.next = nodeLast;
                    nodeLast.prev = node.next;

                    return true;
                }
            }
            return false;
        }
    }

    public void printAll() {
        if (this.head != null) {
            Node<T> node = this.head;
            System.out.printf(node.data + " ");
            while (node.next != null) {
                node = node.next;
                System.out.printf(node.data + " ");
            }
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);
        linkedList.printAll();
        System.out.println("");

        linkedList.insertToFront(4, 7);
        linkedList.printAll();
        System.out.println("");

        linkedList.insertToFront(2, 6);
        linkedList.insertToFront(1, 0);
        linkedList.printAll();
        System.out.println("");

        linkedList.insertRear(10);
        linkedList.insertRear(9);
        linkedList.insertRear(8);
        linkedList.printAll();
    }
}
