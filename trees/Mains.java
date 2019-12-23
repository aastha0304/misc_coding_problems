package trees;

import java.util.Scanner;

public class Mains {

    public static void main(String[] args){

        Tree t = new Tree();
        BST bst = new BST();
        Scanner s = new Scanner(System.in);
        FunWithBST funWithBST = new FunWithBST();
        FunWithTrees funWithTrees = new FunWithTrees();

        String line = s.nextLine();

//Testing default Tree class
//        t.insert(1);
//        t.insert(2);
//        t.insert(3);
//        t.insert(4);
//        t.insert(5);
//        t.inorder();
//        System.out.println();

//Testing BST class
//        bst.insert(1);
//        bst.insert(2);
//        bst.insert(3);
//        bst.insert(4);
//        bst.insert(5);
//        bst.inorder();
//        System.out.println();

//BST - merge 2 BSTs
//        String[] nm = line.split(" ");
//        short n = Short.parseShort(nm[0]);
//        short m = Short.parseShort(nm[1]);
//        line = s.nextLine();
//        String[] nElems = line.split(" ");
//        BST tree1=new BST();
//        for(int i=0;i<n;i++){
//            int data = Integer.parseInt(nElems[i]);
//            tree1.insert(data);
//        }
//        System.out.println("Tree1 inorder");
//        tree1.inorder();
//        System.out.println();
//        line = s.nextLine();
//        String[] mElems = line.split(" ");
//        BST tree2=new BST();
//        for(int i=0;i<m;i++){
//            int data = Integer.parseInt(mElems[i]);
//            tree2.insert(data);
//        }
//        System.out.println("Tree2 inorder");
//        tree2.inorder();
//        System.out.println();
//        funWithBST.merge(tree1, tree2);

//BST - get count of nodes in range
//        line = s.nextLine();
//        String[] elems = line.split(" ");
//        for(int i=0;i<elems.length;i++){
//            int data = Integer.parseInt(elems[i]);
//            bst.insert(data);
//        }
//        bst.inorder();
//        System.out.println();
//        line = s.nextLine();
//        short l = Short.parseShort(line.split(" ")[0]);
//        short h = Short.parseShort(line.split(" ")[1]);
//        System.out.println(funWithBST.getCountNodes(bst, l, h));

//BST - get kth smallest element
//        line = s.nextLine();
//        String[] elems = line.split(" ");
//        for(int i=0;i<elems.length;i++){
//            int data = Integer.parseInt(elems[i]);
//            bst.insert(data);
//        }
//        bst.inorder();
//        System.out.println();
//        short k=s.nextShort();
//        System.out.println(funWithBST.kthSmallest(bst, k));

//Trees - get sum root to leaf
//        Node root = new Node(10);
//        root.left = new Node(20);
//        root.right = new Node(30);
//        root.left.left = new Node(40);
//        root.left.right = new Node(60);
//        t = new Tree(root);
//        t.inorder();
//        System.out.println(t.getTreeSum(root));

//SpecialTree - connect next Right pointer
//        s.nextLine();
//        line = s.nextLine();
//        String[] elems = line.split(" ");
//        NextRightNode  subTree, branch;
//        SpecialTree tree = new SpecialTree();
//        for(int i=0;i<elems.length;i+=3){
//            int parent = Integer.parseInt(elems[i]);
//            int child = Integer.parseInt(elems[i+1]);
//            int side = elems[i+2].charAt(0);
//
//            subTree = new NextRightNode(parent);
//            branch = new NextRightNode(child);
//            if(side=='L') subTree.left=branch;
//            else if(side=='R') subTree.right=branch;
//            tree = (SpecialTree) funWithTrees.insert(tree, subTree);
//        }
//        tree.inorder();
//        System.out.println();
//        tree.connect();
//        tree.levelOrder();
//        System.out.println();

//BST - LCA
        line = s.nextLine();
        String[] elems = line.split(" ");
        for(int i=0;i<elems.length;i++){
            int data = Integer.parseInt(elems[i]);
            bst.insert(data);
        }
        bst.inorder();
        System.out.println();
        line = s.nextLine();
        short n1 = Short.parseShort(line.split(" ")[0]);
        short n2 = Short.parseShort(line.split(" ")[1]);
        System.out.println(bst.LCA(n1, n2));
    }

}
