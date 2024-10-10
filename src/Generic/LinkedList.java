package Generic;

class ListNode<T> {
    T data;
    ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList<T> {

    private int size;
    private ListNode<T> head;
    private ListNode<T> tail;

    public LinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = head;
    }

    // TODO: Create and initialize the head to null in the constructor
    public void add(T data) {
        if(isEmpty()){
            head = new ListNode<>(data);
            tail = head;
        }else {
            ListNode<T> newNode = new ListNode<>(data);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void display() {

        ListNode<T> temp = head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}