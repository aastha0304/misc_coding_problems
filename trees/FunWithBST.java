package trees;

import java.util.LinkedList;

public class FunWithBST {
    static int count;
    public int kthSmallest(BST tree, int k) {
        count = 0;
        return kthSmallestUtil(tree.root, k);
    }
    private int kthSmallestUtil(Node root, int k){
        if(root==null)
            return -1;

        int leftSide = kthSmallestUtil(root.left, k);
        if(leftSide!=-1)
            return leftSide;
        count+=1;
        if(count==k)
            return root.data;
        int rightSide = kthSmallestUtil(root.right, k);
        if(rightSide!=-1)
            return rightSide;
        return -1;
    }
    public int getCountNodes(BST tree, int low, int high) {
        return getCountNodesUtil(tree.root, low, high);
    }
    private int getCountNodesUtil(Node root, int low, int high){
        if(root==null)
            return 0;
        else if(high<root.data)
            return getCountNodesUtil(root.left, low, high);
        else if(low>root.data)
            return getCountNodesUtil(root.right, low, high);
        else{
            int fromLeft = getCountNodesUtil(root.left, low, root.data);
            int fromRight = getCountNodesUtil(root.right, root.data, high);
            return 1+fromLeft+fromRight;
        }
    }
    private void fillUpInorderReverse(LinkedList<Node> stack, Node node){
        if(node==null)
            return;
        else{
            fillUpInorderReverse(stack, node.right);
            stack.add(node);
            fillUpInorderReverse(stack, node.left);
        }
    }
    public void merge(BST tree1, BST tree2){
        LinkedList<Node> stack1 = new LinkedList<>();
        LinkedList<Node> stack2 = new LinkedList<>();
        fillUpInorderReverse(stack1, tree1.root);
        fillUpInorderReverse(stack2, tree2.root);
        while(!stack1.isEmpty()&&!stack2.isEmpty()){
            Node node1 = stack1.peekLast();
            Node node2 = stack2.peekLast();
            if(node1.data>node2.data) {
                System.out.print(node2.data+" ");
                stack2.removeLast();
            }
            else{
                System.out.print(node1.data+" ");
                stack1.removeLast();
            }
        }
        while(!stack1.isEmpty()){
            System.out.print(stack1.removeLast().data+" ");
        }
        while(!stack2.isEmpty()){
            System.out.print(stack2.removeLast().data+" ");
        }
    }
    Node n1, n2;
    public BST correctBST(BST tree){
        int low = -1000;
        int high = 1000;
        correctBSTUtil(tree.root,low,high);

        n1 = null;
        n2 = null;
        if(n1!=null) {
            System.out.println("2 incorrect found alright n1: "+n1.data+" n2: "+n2.data);
            int temp = n1.data;
            n1.data = n2.data;
            n2.data = temp;
        }
        return tree;
    }
    private void correctBSTUtil(Node root, int low, int high){
        if( (n1==null || n2==null) && root!=null){
            if (!(root.data >= low && root.data < high)) {
                System.out.println(root.data + " is wrong");
                if(n1==null)
                    n1=root;
                else
                    n2=root;
            }
            correctBSTUtil(root.left, low, root.data);
            correctBSTUtil(root.right, root.data, high);
        }
    }
}
