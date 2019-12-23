package trees;

public class FunWithTrees {
    public Tree insert(Tree tree, Node node) {
        if(tree.root==null)
            tree.root = node;
        else
            tree.root = insertUtil(tree.root, node);
        return tree;
    }
    private Node insertUtil(Node root, Node newNode) {
        if(root!=null){
            if(root.data == newNode.data) {
                root.left = nonNullBetween(root.left, newNode.left);
                root.right = nonNullBetween(root.right, newNode.right);
            }else {
                insertUtil(root.left, newNode);
                insertUtil(root.right, newNode);
            }
        }
        return root;
    }
    private Node nonNullBetween(Node a, Node b){
        if(a==null)
            return b;
        return a;
    }
}
