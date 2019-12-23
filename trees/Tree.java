package trees;

import java.util.LinkedList;
import java.util.List;

class Tree{
    Node root;
    private static long sum;

    Tree(){
        root = null;
    }
    Tree(int d){
        root = new Node(d);
    }
    Tree(Node node){
        root = node;
    }

    public void insert(int d){
        Node newNode = new Node(d);
        if(root==null)
            root = newNode;
        else {
            List<Node> q = new LinkedList<>();
            ((LinkedList<Node>) q).addLast(root);
            while(!q.isEmpty()){
                Node current = ((LinkedList<Node>) q).removeFirst();
                if(current.left==null) {
                    current.left = newNode;
                    break;
                }
                else if(current.right==null){
                    current.right = newNode;
                    break;
                }else{
                    ((LinkedList<Node>) q).addLast(current.left);
                    ((LinkedList<Node>) q).addLast(current.right);
                }
            }
        }
    }
    public long getTreeSum(Node root){
        sum=0;
        if(root!=null)
            getTreeSumUtil(root, 0);
        return sum;
    }
    private void getTreeSumUtil(Node root, long sumAbove){
        if(root.left==null && root.right==null)
            sum += root.data+sumAbove*10;
        else{
            if(root.left!=null)
                getTreeSumUtil(root.left, sumAbove*10+root.data);
            if(root.right!=null)
                getTreeSumUtil(root.right, sumAbove*10+root.data);
        }
    }
    public void inorder(){
        inorderUtil(root);
    }
    private void inorderUtil(Node node){
        if(node==null)
            return;
        inorderUtil(node.left);
        System.out.print(node.data+" ");
        inorderUtil(node.right);
    }
    public void levelOrder(){
        List<Node> queue = new LinkedList<>();
        ((LinkedList<Node>) queue).addLast(
                this.root);
        while(!queue.isEmpty()){
            Node node = ((LinkedList<Node>) queue).removeFirst();
            System.out.print(node.data+" ");
            if(node.left!=null)
                ((LinkedList<Node>) queue).addLast(node.left);
            if(node.right!=null)
                ((LinkedList<Node>) queue).addLast(node.right);
        }
        System.out.println();
    }
}

class BST extends  Tree{
    BST(){
        super();
    }
    BST(int d){
        super(d);
    }
    @Override
    public void insert(int d) {
        root = insertUtil(root, d);
    }
    private Node insertUtil(Node node, int d){
        if(node==null){
            node = new Node(d);
        }else{
            if(d>node.data) {
                node.right = insertUtil(node.right, d);
            }
            else {
                node.left = insertUtil(node.left, d);
            }
        }
        return node;
    }
    int LCA(int n1, int n2){
        int first = n1<=n2? n1:n2;
        int second = first^n1^n2;
        return LCAUtil(root, first, second);
    }
    private int LCAUtil(Node root, int n1, int n2){
        if(root!=null) {
            if (root.data == n1 || root.data == n2)
                return root.data;
            else if(root.data > n1 && root.data < n2)
                return root.data;
            else if ( root.data > n1 )
                return LCAUtil(root.left, n1, n2);
            else
                return LCAUtil(root.right, n1, n2);
        }
        return -1;
    }
}

class SpecialTree extends  Tree{
    SpecialTree(){
        super();
    }
    SpecialTree(int d){
        root = new NextRightNode(d);
    }
    private NextRightNode getNextRight(NextRightNode node){
        NextRightNode nextRight = node.nextRight;
        while(nextRight!=null){
            if(nextRight.left!=null)
                return (NextRightNode) nextRight.left;
            else if(nextRight.right!=null)
                return (NextRightNode) nextRight.right;
            nextRight = nextRight.nextRight;
        }
        return null;
    }
    public void connect() {
        NextRightNode current = (NextRightNode) root;
        while(current!=null) {
            NextRightNode toRight = current;

            while(toRight!=null) {
                NextRightNode nextRight = getNextRight(toRight);
                if (toRight.right != null) {
                    ((NextRightNode) toRight.right).nextRight = nextRight;
                    if (toRight.left != null) {
                        ((NextRightNode) toRight.left).nextRight = (NextRightNode) toRight.right;
                    }
                } else {
                    if (toRight.left != null) {
                        ((NextRightNode) toRight.left).nextRight = nextRight;
                        //current = (NextRightNode) current.left;
                    }
                }
                toRight = toRight.nextRight;
            }
            if(current.left!=null)
                current = (NextRightNode) current.left;
            else
                current = (NextRightNode) current.right;
        }
    }
    @Override
    public void levelOrder(){
        System.out.println("in level");
        NextRightNode node = (NextRightNode) this.root;
        while(node!=null){
            NextRightNode nextRightPtr = node;
            while(nextRightPtr!=null) {
                System.out.print(nextRightPtr.data+" ");
                nextRightPtr = nextRightPtr.nextRight;
            }
            if(node.left!=null)
                node = (NextRightNode) node.left;
            else node = (NextRightNode) node.right;
        }
    }
}

//For direct Node (without inheritance)
//private static Node getNextRight(Node node){
//    Node nextRight = node.nextRight;
//    while(nextRight!=null){
//        if(nextRight.left!=null)
//            return  nextRight.left;
//        else if(nextRight.right!=null)
//            return nextRight.right;
//        nextRight = nextRight.nextRight;
//    }
//    return null;
//}
//static void connect(Node root)
//{
//    Node current = root;
//    while(current!=null) {
//        Node toRight = current;
//
//        while(toRight!=null) {
//            Node nextRight = getNextRight(toRight);
//            if (toRight.right != null) {
//                toRight.right.nextRight = nextRight;
//                if (toRight.left != null) {
//                    toRight.left.nextRight = toRight.right;
//                }
//            } else {
//                if (toRight.left != null) {
//                    toRight.left.nextRight = nextRight;
//                }
//            }
//            toRight = toRight.nextRight;
//        }
//        if(current.left!=null)
//            current = current.left;
//        else
//            current = current.right;
//    }
//}