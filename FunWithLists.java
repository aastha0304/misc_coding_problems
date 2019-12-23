package misc;

class Node<T> {
    T value;
    Node next;

    Node(T item) {
        this.value = item;
        this.next = null;
    }
}
class LList<T> {
    Node<T> head;
    Node<T> tail;
    LList() {
        head = null;
        tail = null;
    }
    public void printAlternates(){
        Node<T> ptr = head;
        while(ptr!=null){
            System.out.println(String.valueOf(ptr.value));
            //some null checks may be needed
            ptr=ptr.next.next;
        }
    }
    public boolean isEmpty(){
        if(head==null)
            return true;
        return false;
    }

    public void insert(T item){
        if(head==null) {
            head = new Node<>(item);
            tail=head;
        }else {
            tail.next =  new Node(item);
            tail = tail.next;
            tail.next = null;
        }
        display();
    }
    public void display(){
        Node<T> ptr = head;
        while(ptr!=null){
            System.out.print(String.valueOf(ptr.value)+" ");
            //some null checks may be needed
            ptr=ptr.next;
        }
        System.out.println();
    }
}



public class FunWithLists {
    public static void main(String[] args) {
        LList<String> list = new LList<>();
        list.insert("hello");
        list.insert("world");
        list.insert("from");
        list.insert("me");
        list.printAlternates();
    }
}
