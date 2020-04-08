class Node
{
    int data;
    Node next;
    Node(int d) {
        data = d;
        next = null;
    }
}
public class ReorderList {
    private Node reverse(Node head){
        Node prev = null, curr = head;
        while(curr!=null){
            Node spoink = curr.next;
            curr.next = prev;
            prev = curr;
            curr = spoink;
        }
        return prev;
    }
    Node reorderlist(Node head){
        Node slow = head, fast = slow.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node node1 = head;
        Node node2 = slow.next;
        slow.next = null;
        node2 = reverse(node2);
        head = new Node(-1);
        boolean node1turn = true;
        Node node = head;
        while(node1!=null && node2!=null){
            if(!node1turn) {
                node.next = node2;
                node2 = node2.next;
                node1turn = true;
            }else{
                node.next = node1;
                node1 = node1.next;
                node1turn = false;
            }
            node = node.next;
        }
        if(node1!=null){
            node.next = node1;
        }
        if(node2!=null) {
            node.next = node2;
        }
        return head.next;
    }
    public static void main(String[] args){
        ReorderList reorderList = new ReorderList();
        Node head = new Node(1);
        head.next = new Node(7);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
//        head.next.next.next.next = new Node(5);
//        head.next.next.next.next.next = new Node(6);
        Node newList = reorderList.reorderlist(head);
        Node node = newList;
        System.out.println();
//        while(node!=null){
//            System.out.print(node.data+" ");
//            node = node.next;
//        }
    }
}
